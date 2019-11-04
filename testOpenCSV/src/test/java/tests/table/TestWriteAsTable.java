package tests.table;

import com.opencsv.CSVWriter;
import org.junit.Assert;
import org.junit.Test;
import tests.utils.Utils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class TestWriteAsTable {

    @Test
    public void testWriteAll() throws Exception {

        List<String[]> inputData = new ArrayList<>();
        inputData.add(new String[3]);
        inputData.get(0)[0] = "1";
        inputData.get(0)[1] = "2";
        inputData.get(0)[2] = "3";
        inputData.add(new String[3]);
        inputData.get(1)[0] = "a";
        inputData.get(1)[1] = "b";
        inputData.get(1)[2] = "c";

        // write as table
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter writer = new CSVWriter(stringWriter)) {
            writer.writeAll(inputData);
        }

        // validate
        Assert.assertEquals(Utils.removeLineEndings(Utils.readFile("expectedTable.csv")),
                Utils.removeLineEndings(stringWriter.toString()));

    }

    @Test
    public void testWriteOneByOne() throws Exception {

        List<String[]> inputData = new ArrayList<>();
        inputData.add(new String[3]);
        inputData.get(0)[0] = "1";
        inputData.get(0)[1] = "2";
        inputData.get(0)[2] = "3";
        inputData.add(new String[3]);
        inputData.get(1)[0] = "a";
        inputData.get(1)[1] = "b";
        inputData.get(1)[2] = "c";

        // write as table
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter writer = new CSVWriter(stringWriter)) {
            for (String[] array : inputData) {
                writer.writeNext(array);
            }
        }

        // validate
        Assert.assertEquals(Utils.removeLineEndings(Utils.readFile("expectedTable.csv")),
                Utils.removeLineEndings(stringWriter.toString()));

    }

}
