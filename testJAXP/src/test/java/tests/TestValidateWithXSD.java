package tests;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlunit.matchers.CompareMatcher;
import utils.Utils;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.nio.file.Paths;

public class TestValidateWithXSD {

    @Test
    public void validate_XML_With_XSD() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(Paths.get("src", "test", "resources", "input_students.xsd").toFile());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(Paths.get("src", "test", "resources", "input_students.xml").toFile()));
        } catch (Exception e) {
            Assert.fail("validation failure");
        }
    }

}
