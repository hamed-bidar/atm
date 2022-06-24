package model;

import exceptions.InvalidInputException;
import exceptions.Message;

import java.util.ArrayList;
import java.util.Objects;

public class Account{

    //attributes

    private long id;
    private String username;
    private String password;
    private double balance;
    private static final double MIN_BALANCE =100d;
    private Person person;
    private ArrayList<Transaction> transactions;

    //methods

    public Account() {
    }



    public Account(String username, String password , Person person) {
        this.id= (int) (Math.random() * 5000);
        this.username = username;
        this.password = password;
        this.balance = MIN_BALANCE;
        this.person = person;

    }
    public void validatePassword(String password) {
        if (!password.equals(this.password)) {
            throw new InvalidInputException(Message.INVALID_PASSWORD);

        }
    }

    public double withdrawAction(Double amount) {
        if (this.balance - amount > MIN_BALANCE) {
            this.balance -= amount;
        } else
           // throw new InvalidInputException(Message.INVALID_AMOUNT);
            System.out.println("not enough cash!");
            return amount*-1;

    }

    public void depositAction(Double amount) {
        this.balance += amount;
    }

    public void addTransaction(Transaction transaction){
        if (Objects.isNull(transactions) ) {
            transactions= new ArrayList<>();
        }
        transactions.add(transaction);
    }
     public void passwordValidation(String password){
        if (!password.equals(this.password)){
            throw new InvalidInputException(Message.INVALID_PASSWORD);
        }
     }
    //getter and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Person getPerson() {
        return person;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", person=" + person +
                 '}';
    }
}
