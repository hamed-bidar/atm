package model;

import repasitory.Account_tbl;
import repasitory.Transaction_tbl;

import java.sql.Connection;


public class Withdraw extends Transaction {
    private Connection conn = null;
    public Withdraw(Connection conn) {
        this.conn = conn;
    }

    public Withdraw() {
    }

    @Override
    public void operator(Account account, double amount) {

        try {

            this.account.withdrawAction(amount);

         /*   //update in database

            Account_tbl account_tbl = new Account_tbl(conn);
            account_tbl.updateBalance(account);

            Transaction_tbl transaction_tbl = new Transaction_tbl(conn);
            transaction_tbl.insertTransaction(amount, account);*/

        } catch (Exception exception) {
            exception.printStackTrace();

        }


    }
}



