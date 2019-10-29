package tests;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import utils.Utils;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.StringReader;
import java.nio.file.Paths;

public class TestValidateWithXSD {

    @Test
    public void testManualValidationWithValidXML() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(Paths.get("src", "test", "resources", "input_students.xsd").toFile());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(Paths.get("src", "test", "resources", "input_students.xml").toFile()));
        } catch (Exception e) {
            Assert.fail("validation failure");
        }
    }

    @Test(expected = SAXParseException.class)
    public void testManualValidationWithInvalidXML() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(Paths.get("src", "test", "resources", "input_students.xsd").toFile());
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(Paths.get("src", "test", "resources", "input_students_bad.xml").toFile()));
    }

    @Test(expected = SAXParseException.class)
    public void testAutomaticValidationWithInvalidXML() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // for validation
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(true);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(Paths.get("src", "test", "resources", "input_students.xsd").toFile());
        documentBuilderFactory.setSchema(schema);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // for this test specify an error handler which throws exceptions
        documentBuilder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;
            }
        });

        // try to parse and expect exception
        documentBuilder.parse(new InputSource(new StringReader(Utils.readFile("input_students_bad.xml"))));

    }

}
