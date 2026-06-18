package com.odeal.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * multipart/form-data istek gövdesi.
 *
 * <p>Düz veri (metin alanları + dosya parçaları) tutar; sabit bir boundary ile gövde
 * baytlarını üretir. Bayt dizisi yeniden gönderilebilir olduğundan retry-güvenlidir.
 * Dosya yükleyen endpoint'ler için kullanılır.</p>
 */
public class MultipartBody {

    private final String boundary = "----OdealBoundary" + UUID.randomUUID().toString().replace("-", "");
    private final List<String[]> fields = new ArrayList<>();   // [name, value]
    private final List<Object[]> files = new ArrayList<>();    // [name, fileName, byte[], contentType]

    /** Metin form alanı ekler. */
    public MultipartBody addField(String name, String value) {
        fields.add(new String[]{name, value});
        return this;
    }

    /** Dosya parçası ekler (varsayılan içerik tipi application/octet-stream). */
    public MultipartBody addFile(String name, String fileName, byte[] content) {
        return addFile(name, fileName, content, "application/octet-stream");
    }

    /** Dosya parçası ekler. */
    public MultipartBody addFile(String name, String fileName, byte[] content, String contentType) {
        files.add(new Object[]{name, fileName, content, contentType});
        return this;
    }

    /** Content-Type header değeri (boundary dahil). */
    public String getContentType() {
        return "multipart/form-data; boundary=" + boundary;
    }

    /** Gövde baytlarını üretir. {@link #getContentType()} ile aynı boundary kullanılır. */
    public byte[] toBytes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] dash = ("--" + boundary).getBytes(StandardCharsets.UTF_8);
            byte[] crlf = "\r\n".getBytes(StandardCharsets.UTF_8);
            for (String[] f : fields) {
                out.write(dash); out.write(crlf);
                out.write(("Content-Disposition: form-data; name=\"" + f[0] + "\"").getBytes(StandardCharsets.UTF_8));
                out.write(crlf); out.write(crlf);
                out.write(f[1].getBytes(StandardCharsets.UTF_8)); out.write(crlf);
            }
            for (Object[] file : files) {
                out.write(dash); out.write(crlf);
                out.write(("Content-Disposition: form-data; name=\"" + file[0] + "\"; filename=\"" + file[1] + "\"")
                        .getBytes(StandardCharsets.UTF_8));
                out.write(crlf);
                out.write(("Content-Type: " + file[3]).getBytes(StandardCharsets.UTF_8));
                out.write(crlf); out.write(crlf);
                out.write((byte[]) file[2]); out.write(crlf);
            }
            out.write(dash); out.write("--".getBytes(StandardCharsets.UTF_8)); out.write(crlf);
        } catch (IOException e) {
            throw new RuntimeException("Multipart gövdesi üretilemedi", e);
        }
        return out.toByteArray();
    }
}
