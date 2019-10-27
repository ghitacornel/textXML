package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openuri.easypo.LineItem;
import org.openuri.easypo.PurchaseOrderDocument;
import utils.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestProcessXML {

    @Test
    public void testParse() throws Exception {

        String xmlAsString = Utils.readFile("input.xml");

        PurchaseOrderDocument purchaseOrderDocument = PurchaseOrderDocument.Factory.parse(xmlAsString);
        PurchaseOrderDocument.PurchaseOrder purchaseOrder = purchaseOrderDocument.getPurchaseOrder();

        {// complex business here
            purchaseOrder.getCustomer().setAge(30);
            purchaseOrder.getCustomer().setName("ion");
            purchaseOrder.getCustomer().setAddress("bucharest");
            purchaseOrder.removeLineItem(1);
            LineItem lineItem = purchaseOrder.addNewLineItem();
            lineItem.setDescription("line item 3");
            lineItem.setPerUnitOunces(BigDecimal.valueOf(7));
            lineItem.setPrice(BigDecimal.valueOf(8));
            lineItem.setQuantity(BigInteger.valueOf(9));
        }

        String actualXML = Utils.write(purchaseOrderDocument);
        String expectedXML = Utils.readFile("output.xml");

        Assert.assertEquals(expectedXML, actualXML);

    }
}
