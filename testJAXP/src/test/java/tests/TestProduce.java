package tests;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;
import utils.Utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TestProduce {

    @Test
    public void testProduce() throws Exception {

        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = df.newDocumentBuilder();
        Document document = builder.newDocument();

        // create dummy data
        // no need to validate against a schema
        // total disregard of any XSD
        Element element;
        {
            element = document.createElement("dummyTag");
            element.setAttribute("dummyTagAttribute", "dummyTagAttributeValue");
            Element subElement1 = document.createElement("dummySubTag1");
            subElement1.setAttribute("dummySubTagAttribute1", "dummySubTagAttributeValue1");
            Element subElement2 = document.createElement("dummySubTag2");
            subElement1.setAttribute("dummySubTagAttribute2", "dummySubTagAttributeValue2");
            element.appendChild(subElement1);
            element.appendChild(subElement2);
        }
        document.appendChild(element);

        // write it down
        String actualXML = Utils.write(document);
        String expectedXML = Utils.readFile("output_dummy.xml");

        Assert.assertThat(actualXML, CompareMatcher.isSimilarTo(expectedXML).ignoreWhitespace());

    }
}
