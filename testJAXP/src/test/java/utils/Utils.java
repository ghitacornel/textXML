package utils;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            // for validation
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(true);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(Paths.get("src", "test", "resources", "input_students.xsd").toFile());
            documentBuilderFactory.setSchema(schema);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // for validation
            documentBuilder.setErrorHandler(new DefaultHandler());

            return documentBuilder.parse(new InputSource(new StringReader(inputXML)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String write(Document document) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(outputStream));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(outputStream.toByteArray());
    }

}
