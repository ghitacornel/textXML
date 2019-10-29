package model;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Person {

    private int id;

    @JsonbProperty("person-name")
    private String name;

    @JsonbProperty(nillable = true)
    private String email;

    @JsonbTransient
    private int age;

    @JsonbDateFormat("dd-MM-yyyy")
    private LocalDate registeredDate;

    private BigDecimal salary;

    public Person() {
    }

    public Person(int id, String name, String email, int age, LocalDate registeredDate, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.registeredDate = registeredDate;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                name.equals(person.name) &&
                email.equals(person.email) &&
                registeredDate.equals(person.registeredDate) &&
                salary.stripTrailingZeros().equals(person.salary.stripTrailingZeros());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, registeredDate, salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @JsonbNumberFormat(locale = "en_US", value = "#0.0")
    public BigDecimal getSalary() {
        return salary;
    }

}