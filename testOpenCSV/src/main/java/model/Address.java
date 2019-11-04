package model;

import com.opencsv.bean.CsvBindByName;

public class Address {

    @CsvBindByName
    private String street;

    @CsvBindByName
    private int number;

}
