package model;

import model.Account;
import model.TransactionStatus;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Transaction {

    //attributes

    protected double amount;
    protected Account account;
    protected TransactionStatus transactionStatus;
    protected Date createdDate;
    protected double id;

    public abstract void operator (Account account ,double amount);

    //getter and setters

    public double getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public TransactionStatus getT() {
        return transactionStatus;
    }
    public void setT(TransactionStatus t) {
        this.transactionStatus = t;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", account=" + account +
                ", transactionStatus=" + transactionStatus +
                ", createdDate=" + createdDate +
                ", id=" + id +
                '}';
    }
}
