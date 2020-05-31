package tests;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlunit.matchers.CompareMatcher;
import utils.Utils;

public class TestJAXP {

    @Test
    public void parseValidateAndChangeXML() {
        String inputXML = Utils.readFile("input_students.xml");
        Document document = Utils.parseDocument(inputXML);
        NodeList studentNodesList = document.getElementsByTagName("student");

        // parse and validate something
        Assert.assertEquals(2, studentNodesList.getLength());
        Node item_0 = studentNodesList.item(0);
        Assert.assertEquals("001", item_0.getAttributes().getNamedItem("id").getNodeValue());
        Node item_1 = studentNodesList.item(1);
        Assert.assertEquals("002", item_1.getAttributes().getNamedItem("id").getNodeValue());

        Element element;// create dummy data
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

        // append dummy data
        item_1.appendChild(element);

        // write it down
        String actualXML = Utils.write(document);
        String expectedXML = Utils.readFile("output_students.xml");

        MatcherAssert.assertThat(actualXML, CompareMatcher.isSimilarTo(expectedXML).ignoreWhitespace());

    }
}
