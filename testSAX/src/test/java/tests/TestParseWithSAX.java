package tests;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Paths;

public class TestParseWithSAX {

    @Test
    public void testParse() throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        DefaultHandler handler = new MyDefaultHandler();
        parser.parse(Paths.get("src", "test", "resources", "input.xml").toFile(), handler);

    }

    private static class MyDefaultHandler extends DefaultHandler {

        boolean bfname = false;
        boolean blname = false;
        boolean bnname = false;
        boolean bsalary = false;

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            System.out.println("Start Element :" + qName);
            if (qName.equalsIgnoreCase("FIRSTNAME")) {
                bfname = true;
            }
            if (qName.equalsIgnoreCase("LASTNAME")) {
                blname = true;
            }
            if (qName.equalsIgnoreCase("NICKNAME")) {
                bnname = true;
            }
            if (qName.equalsIgnoreCase("SALARY")) {
                bsalary = true;
            }
        }

        public void endElement(String uri, String localName, String qName) {
            System.out.println("End Element :" + qName);
        }

        public void characters(char[] ch, int start, int length) {
            if (bfname) {
                System.out.println("First Name : " + new String(ch, start, length));
                bfname = false;
            }
            if (blname) {
                System.out.println("Last Name : " + new String(ch, start, length));
                blname = false;
            }
            if (bnname) {
                System.out.println("Nick Name : " + new String(ch, start, length));
                bnname = false;
            }
            if (bsalary) {
                System.out.println("Salary : " + new String(ch, start, length));
                bsalary = false;
            }
        }

    }
}
