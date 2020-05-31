package tests;

import model.Person;
import org.junit.Assert;
import org.junit.Test;
import utils.Utils;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJSerializeDeserializeJSON {

    @Test
    public void testSerialize() {

        Person person = new Person(1, "Jhon", "jhon@test.com", 20, LocalDate.of(2019, 9, 7), BigDecimal.valueOf(1000));

        JsonbConfig jsonbConfig = new JsonbConfig().withNullValues(true).withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);

        String actualJSON = jsonb.toJson(person);
        String expectedJSON = Utils.readFile("expected_person.json");

        Assert.assertEquals(expectedJSON, actualJSON);

    }

    @Test
    public void testDeserialize() {

        JsonbConfig jsonbConfig = new JsonbConfig().withFormatting(true).withNullValues(true).withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);

        Person person1 = new Person(1, "one", "one@test.com", 10, LocalDate.of(2019, 7, 9), BigDecimal.valueOf(1000));
        Person person2 = new Person(2, "two", "two@test.com", 20, LocalDate.of(2019, 7, 9), BigDecimal.valueOf(2000));
        List<Person> personsListInitial = Arrays.asList(person1, person2);

        String actualJSON = jsonb.toJson(personsListInitial);
        String expectedJSON = Utils.readFile("input_persons.json");

        actualJSON = actualJSON.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals(expectedJSON, actualJSON);

        List<Person> personListFinal = jsonb.fromJson(expectedJSON, new ArrayList<Person>() {
        }.getClass().getGenericSuperclass());

        Assert.assertEquals(personsListInitial, personListFinal);

    }

}
