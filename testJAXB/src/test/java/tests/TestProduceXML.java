package tests;

import my.app.jaxb.gen.Address;
import my.app.jaxb.gen.Person;
import my.app.jaxb.gen.Persons;
import org.junit.Assert;
import org.junit.Test;
import utils.Utils;

import java.math.BigDecimal;
import java.util.Calendar;

public class TestProduceXML {

    @Test
    public void testProduceSinglePerson() {

        Person person = new Person();
        {// build person

            person.setId(1);
            person.setName("ion");
            person.setSalary(BigDecimal.valueOf(123.4));

            // build person date of birth
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2000);
            instance.set(Calendar.MONTH, 1);
            instance.set(Calendar.DAY_OF_MONTH, 2);
            person.setDateOfBirth(instance);

        }

        Persons persons = new Persons();
        persons.getPerson().add(person);

        String actualXML = Utils.write(persons);
        String expectedXML = Utils.readFile("expectedSinglePerson.xml");

        actualXML = actualXML.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals(expectedXML.trim(), actualXML.trim());

    }

    @Test
    public void testProduceSinglePersonWithAddress() {

        Person person = new Person();
        {// build person

            person.setId(1);
            person.setName("ion");
            person.setSalary(BigDecimal.valueOf(123.4));

            // build person date of birth
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2000);
            instance.set(Calendar.MONTH, 1);
            instance.set(Calendar.DAY_OF_MONTH, 2);
            person.setDateOfBirth(instance);

        }

        Address address = new Address();
        {// build address
            address.setCountry("RO");
            address.setState("state");
            address.setCity("city");
            address.setStreet("street");
            address.setNumber("number");
            address.setZip(123);
        }
        person.setAddress(address);

        Persons persons = new Persons();
        persons.getPerson().add(person);

        String actualXML = Utils.write(persons);
        String expectedXML = Utils.readFile("expectedPersonWithAddress.xml");

        actualXML = actualXML.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals(expectedXML.trim(), actualXML.trim());

    }

    @Test
    public void testProduceMultiplePersons() {

        Person person1 = new Person();
        {// build person

            person1.setId(1);
            person1.setName("ion");
            person1.setSalary(BigDecimal.valueOf(123.4));

            // build person date of birth
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2000);
            instance.set(Calendar.MONTH, 1);
            instance.set(Calendar.DAY_OF_MONTH, 2);
            person1.setDateOfBirth(instance);

        }

        Address address1 = new Address();
        {// build address
            address1.setCountry("RO");
            address1.setState("state1");
            address1.setCity("city1");
            address1.setStreet("street1");
            address1.setNumber("number1");
            address1.setZip(123);
        }
        person1.setAddress(address1);

        Person person2 = new Person();
        {// build person

            person2.setId(2);
            person2.setName("gheorghe");
            person2.setSalary(BigDecimal.valueOf(456.7));

            // build person date of birth
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2001);
            instance.set(Calendar.MONTH, 2);
            instance.set(Calendar.DAY_OF_MONTH, 3);
            person1.setDateOfBirth(instance);

        }

        Address address2 = new Address();
        {// build address
            address2.setCountry("US");
            address2.setState("state2");
            address2.setCity("city2");
            address2.setStreet("street2");
            address2.setNumber("number2");
            address2.setZip(456);
        }
        person2.setAddress(address2);

        Person person3 = new Person();
        {// build person

            person3.setId(3);
            person3.setName("vasile");
            person3.setSalary(BigDecimal.valueOf(789));

            // build person date of birth
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2002);
            instance.set(Calendar.MONTH, 3);
            instance.set(Calendar.DAY_OF_MONTH, 4);
            person1.setDateOfBirth(instance);

        }

        Persons persons = new Persons();
        persons.getPerson().add(person1);
        persons.getPerson().add(person2);
        persons.getPerson().add(person3);

        String actualXML = Utils.write(persons);
        String expectedXML = Utils.readFile("expectedMultiplePersons.xml");

        actualXML = actualXML.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        Assert.assertEquals(expectedXML.trim(), actualXML.trim());

    }

}
