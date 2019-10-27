package utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
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

    public static Document parseDocument(String inputXML) {
        try {
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = df.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(inputXML)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String write(Document document) {
        ByteArrayOutputStream outputStream;
        try {
            TransformerFactory tfFactory = TransformerFactory.newInstance();
            Transformer tf = tfFactory.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            outputStream = new ByteArrayOutputStream();
            tf.transform(new DOMSource(document), new StreamResult(outputStream));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(outputStream.toByteArray());
    }

}
