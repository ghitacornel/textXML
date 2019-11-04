package tests.bean;

import com.opencsv.bean.CsvToBeanBuilder;
import model.BeanByName;
import model.BeanByPosition;
import org.junit.Assert;
import org.junit.Test;
import tests.utils.Utils;

import java.io.StringReader;
import java.util.List;

public class TestReadAsBean {

    @Test
    public void testReadByPosition() {

        List<BeanByPosition> data = new CsvToBeanBuilder<BeanByPosition>(new StringReader(Utils.readFile("inputBeanByPosition.csv")))
                .withType(BeanByPosition.class)
                .withSkipLines(1)// skip header
                .build()
                .parse();

        String expected = "[BeanByPosition{c1='a', c2=1, date=01-Apr-1998}, BeanByPosition{c1='b', c2=2, date=02-May-1998}]";
        Assert.assertEquals(expected, data.toString());

    }

    @Test
    public void testReadByName() {

        List<BeanByName> data = new CsvToBeanBuilder<BeanByName>(new StringReader(Utils.readFile("inputBeanByName.csv")))
                .withType(BeanByName.class)
                .build()
                .parse();

        String expected = "[BeanByPosition{c1='ion', c2=30, c3=01-Apr-1998, c4=123.456}, BeanByPosition{c1='gheorghe', c2=20, c3=02-May-1998, c4=23.45}]";
        Assert.assertEquals(expected, data.toString());

    }

}
