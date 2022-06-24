package repasitory;

import model.Account;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction_tbl {

    Connection cm = new ConnectionManager().connect();
    private Connection conn = null;
    public Transaction_tbl(Connection conn) {
        this.conn = conn;
    }

    public long insertTransaction(Double amount, Account account) {

        long dbId = 0;
        String SQL = "INSERT INTO transaction ( amount,account_id) "
                + "VALUES(?,?)";
        try {
            PreparedStatement pstmt = cm.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setDouble(1, amount);
            pstmt.setLong(2, account.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dbId = rs.getLong("id");
                        System.out.println("transaction  created.");

                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return dbId;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<>();

        String SQL = "SELECT * FROM transaction";
        PreparedStatement pstmt = null;
        try {
            pstmt = cm.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                long account_id = resultSet.getLong("account_id");

                Transaction transaction = new Transaction() {
                    @Override
                    public void operator(Account account, double amount) {

                    }
                };
                transaction.setId(id);
                transaction.setAmount(amount);
                transaction.setCreatedDate(date);

                Account_tbl account_tbl = new Account_tbl(cm);
                Account account = new Account();
                account = account_tbl.getAccountById(account_id);
                transaction.setAccount(account);

                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    public Transaction getTransactionById(long id) {

        Transaction transaction = new Transaction() {
            @Override
            public void operator(Account account, double amount) {
            }
        };
        transaction.setId(id);
        String SQL = "SELECT  * from transaction where id=" + id;
        PreparedStatement pstmt = null;
        try {
            pstmt = cm.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {

                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                long account_id = resultSet.getLong("account_id");

                transaction.setId(id);
                transaction.setAmount(amount);
                transaction.setCreatedDate(date);

                Account_tbl account_tbl = new Account_tbl(cm);

                transaction.setAccount(account_tbl.getAccountById(account_id));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }
}




