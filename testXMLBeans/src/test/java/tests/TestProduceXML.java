package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openuri.easypo.Customer;
import org.openuri.easypo.LineItem;
import org.openuri.easypo.PurchaseOrderDocument;
import org.openuri.easypo.Shipper;
import org.xmlunit.matchers.CompareMatcher;
import utils.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

public class TestProduceXML {

    @Test
    public void testParse() {

        PurchaseOrderDocument purchaseOrderDocument = PurchaseOrderDocument.Factory.newInstance();
        PurchaseOrderDocument.PurchaseOrder purchaseOrder = purchaseOrderDocument.addNewPurchaseOrder();

        {
            Customer customer = purchaseOrder.addNewCustomer();
            customer.setName("customer name");
            customer.setAddress("customer address");
            customer.setAge(20);
        }

        {
            // build date
            Calendar instance = Calendar.getInstance();
            instance.clear();
            instance.set(Calendar.YEAR, 2000);
            instance.set(Calendar.MONTH, 1);
            instance.set(Calendar.DAY_OF_MONTH, 2);
            purchaseOrder.setDate(instance);
        }

        {
            LineItem lineItem = purchaseOrder.addNewLineItem();
            lineItem.setDescription("line item 1");
            lineItem.setPerUnitOunces(BigDecimal.valueOf(1));
            lineItem.setPrice(BigDecimal.valueOf(2));
            lineItem.setQuantity(BigInteger.valueOf(3));
        }

        {
            LineItem lineItem = purchaseOrder.addNewLineItem();
            lineItem.setDescription("line item 2");
            lineItem.setPerUnitOunces(BigDecimal.valueOf(4));
            lineItem.setPrice(BigDecimal.valueOf(5));
            lineItem.setQuantity(BigInteger.valueOf(6));
        }

        {
            Shipper shipper = purchaseOrder.addNewShipper();
            shipper.setName("shipper name");
            shipper.setPerOunceRate(BigDecimal.valueOf(3));
        }

        String actualXML = Utils.write(purchaseOrderDocument);
        String expectedXML = Utils.readFile("expectedProducedXML.xml");

        Assert.assertThat(actualXML, CompareMatcher.isIdenticalTo(expectedXML).ignoreWhitespace());
    }
}
