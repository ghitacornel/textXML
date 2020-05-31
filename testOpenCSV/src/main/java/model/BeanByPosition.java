package model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanByPosition {

    @CsvBindByPosition(position = 1)
    private String c1;

    @CsvBindByPosition(position = 2)
    private Integer c2;

    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 4)
    private Date date;

    @Override
    public String toString() {
        return "BeanByPosition{" +
                "c1='" + c1 + '\'' +
                ", c2=" + c2 +
                ", date=" + new SimpleDateFormat("dd-MMM-YYYY").format(date) +
                '}';
    }
}
