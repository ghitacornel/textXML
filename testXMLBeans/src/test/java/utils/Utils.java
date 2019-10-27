package utils;

import org.apache.xmlbeans.XmlOptions;
import org.openuri.easypo.PurchaseOrderDocument;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

final public class Utils {

    private Utils() {
    }

    public static String readFile(String fileName) {
        try {
            Path resourceDirectory = Paths.get("src", "test", "resources");
            String absolutePath = resourceDirectory.toFile().getAbsolutePath();
            byte[] encoded = Files.readAllBytes(Paths.get(absolutePath, fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String write(PurchaseOrderDocument document) {
        ByteArrayOutputStream outputStream;
        try {
            outputStream = new ByteArrayOutputStream();
            XmlOptions xmlOptions = new XmlOptions();
            xmlOptions.setSavePrettyPrint();
            xmlOptions.setSavePrettyPrintIndent(0);
            xmlOptions.setSavePrettyPrintOffset(0);
            document.save(outputStream, xmlOptions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(outputStream.toByteArray());
    }

}
