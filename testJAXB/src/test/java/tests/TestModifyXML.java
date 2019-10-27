package tests;

import my.app.jaxb.gen.Person;
import my.app.jaxb.gen.Persons;
import org.junit.Assert;
import org.junit.Test;
import utils.Utils;

import java.math.BigDecimal;

public class TestModifyXML {

    @Test
    public void modifyXML() {

        // READ
        String inputXML = Utils.readFile("inputMultiplePersons.xml");
        Persons persons = Utils.read(inputXML);

        // ALTER
        {// complex business here :D

            Person person_0 = persons.getPerson().get(0);
            person_0.setSalary(person_0.getSalary().multiply(new BigDecimal(2)));

            Person person_1 = persons.getPerson().get(1);
            person_1.setName("alex");

            Person person_2 = persons.getPerson().get(2);
            person_2.setAddress(person_0.getAddress());
            person_0.setAddress(null);

        }

        // WRITE AND VERIFY
        String actualXML = Utils.write(persons);
        String expectedXML = Utils.readFile("outputMultiplePersons.xml");

        actualXML = actualXML.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals(expectedXML.trim(), actualXML.trim());

    }

}
