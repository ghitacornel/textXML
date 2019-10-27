package utils;

import my.app.jaxb.gen.Persons;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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

    public static Persons read(String xmlString) {
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            return (Persons) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String write(Persons model) {
        ByteArrayOutputStream outputStream;
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            outputStream = new ByteArrayOutputStream();
            mar.marshal(model, outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new String(outputStream.toByteArray());
    }

}
