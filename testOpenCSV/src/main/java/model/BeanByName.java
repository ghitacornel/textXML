package model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import com.opencsv.bean.CsvRecurse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanByName {

    @CsvBindByName(column = "salary")
    @CsvNumber("##.##")
    private Double salary;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName
    private int age;

    @CsvDate("yyyy-MM-dd")
    @CsvBindByName(column = "dob")
    private Date dateOfBirth;

    @CsvRecurse
    private Address address;

    @Override
    public String toString() {
        return "BeanByPosition{" +
                "c1='" + name + '\'' +
                ", c2=" + age +
                ", c3=" + new SimpleDateFormat("dd-MMM-YYYY").format(dateOfBirth) +
                ", c4=" + salary +
                '}';
    }

}
