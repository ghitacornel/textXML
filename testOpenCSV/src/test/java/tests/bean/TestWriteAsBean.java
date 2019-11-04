package tests.bean;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import model.BeanByName;
import model.BeanByPosition;
import org.junit.Assert;
import org.junit.Test;
import tests.utils.Utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

public class TestWriteAsBean {

    @Test
    public void testWriteByPosition() throws Exception {

        List<BeanByPosition> data = new CsvToBeanBuilder<BeanByPosition>(new StringReader(Utils.readFile("inputBeanByPosition.csv")))
                .withType(BeanByPosition.class)
                .withSkipLines(1)// skip header
                .build()
                .parse();

        StringWriter stringWriter = new StringWriter();
        new StatefulBeanToCsvBuilder<BeanByPosition>(stringWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withSeparator('?')// custom
                .withLineEnd("\n")// custom
                .build()
                .write(data);

        String expectedContent = Utils.readFile("outputBeanByPosition.csv");
        String actualContent = stringWriter.toString();

        Assert.assertEquals(Utils.removeLineEndings(expectedContent), Utils.removeLineEndings(actualContent));

    }

    @Test
    public void testWriteByName() throws Exception {

        List<BeanByName> data = new CsvToBeanBuilder<BeanByName>(new StringReader(Utils.readFile("inputBeanByName.csv")))
                .withType(BeanByName.class)
                .build()
                .parse();

        StringWriter stringWriter = new StringWriter();
        new StatefulBeanToCsvBuilder<BeanByName>(stringWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build()
                .write(data);

        String expectedContent = Utils.readFile("outputBeanByName.csv");
        String actualContent = stringWriter.toString();

        Assert.assertEquals(Utils.removeLineEndings(expectedContent), Utils.removeLineEndings(actualContent));

    }
}
