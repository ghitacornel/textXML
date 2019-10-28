package tests;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestInvalidJSON {

    @Test
    public void testValid() {

        JSONObject jsonSchema = new JSONObject(new JSONTokener(TestInvalidJSON.class.getResourceAsStream("/product_schema.json")));
        JSONObject jsonSubject = new JSONObject(new JSONTokener(TestInvalidJSON.class.getResourceAsStream("/product_valid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    @Test(expected = ValidationException.class)
    public void testInvalid() {

        JSONObject jsonSchema = new JSONObject(new JSONTokener(TestInvalidJSON.class.getResourceAsStream("/product_schema.json")));
        JSONObject jsonSubject = new JSONObject(new JSONTokener(TestInvalidJSON.class.getResourceAsStream("/product_invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        try {
            schema.validate(jsonSubject);
        } catch (ValidationException e) {

            Assert.assertEquals(2, e.getViolationCount());
            Assert.assertTrue(e.getAllMessages().containsAll(Arrays.asList("#/price: 0.0 is not higher than 0", "#: required key [name] not found")));

            throw e;
        }
    }

}
