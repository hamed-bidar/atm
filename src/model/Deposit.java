package model;

import repasitory.Account_tbl;
import repasitory.Transaction_tbl;

import java.sql.Connection;

public class Deposit extends Transaction {

    private Connection conn = null;
    public Deposit(Connection conn) {
        this.conn = conn;
    }

    public Deposit() {
    }

    @Override
    public void operator(Account account, double amount) {


        try {
            this.account.depositAction(amount);

           /* //update in database

            Account_tbl account_tbl = new Account_tbl(conn);
            account_tbl.updateBalance(account);
            Transaction_tbl transaction_tbl = new Transaction_tbl(conn);
            transaction_tbl.insertTransaction(amount, account);*/

        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }
}
