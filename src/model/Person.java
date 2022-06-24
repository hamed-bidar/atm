package model;

import exceptions.InvalidInputException;
import exceptions.Message;

public class Person {

    //attributes

    private long id;
    private String name;
    private String family;
    private long age;
    private String nationalCode;

    //methods

    public Person(String name, String family, String nationalCode, long age) {
        this.name = name;
        this.family = family;
        this.age=age;
        this.nationalCode = nationalCode;
    }

    public Person() {
    }

    public void Validation(int age, String nationalCode) {
        if (age < 18) {
            throw new InvalidInputException(Message.INVALID_AGE);
        }
        if (nationalCode.length() != 10){
            throw new InvalidInputException(Message.INVALID_NATIONAL_CODE);

        }
    }

    //getter and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public long getAge() {
        return age;
    }
    public void setAge(long age) {
        this.age = age;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
