package tests.table;

import com.opencsv.CSVReader;
import org.junit.Assert;
import org.junit.Test;
import tests.utils.Utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestReadAsTable {

    @Test
    public void testReadAll() throws Exception {

        String file = Utils.readFile("inputTable.csv");

        // read as table
        List<String[]> list;
        try (CSVReader csvReader = new CSVReader(new StringReader(file))) {
            list = csvReader.readAll();
        }

        // validate
        List<String> all = new ArrayList<>();
        for (String[] strings : list) {
            all.addAll(Arrays.asList(strings));
        }
        Assert.assertEquals("[a, b, c, 1, 2, 3, dfs sdffd,  ee 3243,  324d dsd]", all.toString());

    }

    @Test
    public void testReadLineByLine() throws Exception {

        String file = Utils.readFile("inputTable.csv");

        // read as table
        List<String[]> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new StringReader(file))) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }
        }

        // validate
        List<String> all = new ArrayList<>();
        for (String[] strings : list) {
            all.addAll(Arrays.asList(strings));
        }
        Assert.assertEquals("[a, b, c, 1, 2, 3, dfs sdffd,  ee 3243,  324d dsd]", all.toString());

    }
}
