package tests;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class TestParser {

    @Test
    public void testParseByPath() throws Exception {
        String inputXML = Utils.readFile("input.xml");
        Document xmlDocument = Utils.parseDocument(inputXML);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/Tutorials/Tutorial";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        Assert.assertEquals(2, nodeList.getLength());
        Node item_0 = nodeList.item(0);
        Assert.assertEquals("01", item_0.getAttributes().getNamedItem("tutId").getTextContent());
        Assert.assertEquals("java", item_0.getAttributes().getNamedItem("type").getTextContent());
        Node item_1 = nodeList.item(1);
        Assert.assertEquals("02", item_1.getAttributes().getNamedItem("tutId").getNodeValue());
        Assert.assertEquals("java", item_1.getAttributes().getNamedItem("type").getNodeValue());

    }

    @Test
    public void testParseByAttributeValue() throws Exception {
        String inputXML = Utils.readFile("input.xml");
        Document xmlDocument = Utils.parseDocument(inputXML);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String id = "02";
        String expression = "/Tutorials/Tutorial[@tutId=" + "'" + id + "'" + "]";
        Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

        Assert.assertEquals("02", node.getAttributes().getNamedItem("tutId").getNodeValue());
        Assert.assertEquals("java", node.getAttributes().getNamedItem("type").getNodeValue());

    }

    @Test
    public void testParseBySubTagValue() throws Exception {
        String inputXML = Utils.readFile("input.xml");
        Document xmlDocument = Utils.parseDocument(inputXML);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String tagValue = "Guava";
        String expression = "//Tutorial[descendant::title[text()=" + "'" + tagValue + "'" + "]]";
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

        Assert.assertEquals(1, nodeList.getLength());
        Node item_0 = nodeList.item(0);
        Assert.assertEquals("01", item_0.getAttributes().getNamedItem("tutId").getTextContent());
        Assert.assertEquals("java", item_0.getAttributes().getNamedItem("type").getTextContent());

    }
}
