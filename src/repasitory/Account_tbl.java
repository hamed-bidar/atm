package repasitory;


import exceptions.InvalidInputException;
import exceptions.Message;
import model.Account;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Account_tbl {

    private Connection conn = null;

    public Account_tbl(Connection conn) {
        this.conn = conn;
    }

    public void ATM() {
    }

    public long insertAccount(Account account) {

        long dbId = 0;
        String SQL = "INSERT INTO account ( username,password,balance,person_id) "
                + "VALUES(?,?,?,? )";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());
            pstmt.setDouble(3, account.getBalance());
            pstmt.setLong(4, account.getPerson().getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        dbId = rs.getLong("id");
                        System.out.println("account " + rs.getString("username") + " created.");

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

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();

        String SQL = "SELECT * FROM account";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                long balance = resultSet.getLong("balance");
                long person_id = resultSet.getLong("person_id");


                Account account = new Account();
                account.setId(id);
                account.setUsername(username);
                account.setPassword(password);
                account.setBalance(balance);

                Person_tbl ptbl = new Person_tbl(conn);
                Person p = new Person();
                p = ptbl.getPersonById(person_id);
                account.setPerson(p);

                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public Account getAccountById(long id) {
        Account account = new Account();
        account.setId(id);
        String SQL = "SELECT  * from account where id=" + id;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                long balance = resultSet.getLong("balance");
                long person_id = resultSet.getLong("person_id");

                Person_tbl person_tbl = new Person_tbl(conn);

                account.setId(id);
                account.setUsername(username);
                account.setPassword(password);
                account.setBalance(balance);
                account.setPerson(person_tbl.getPersonById(person_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void updateBalance(Account account) {

        Connection cm = new ConnectionManager().connect();


        String SQL = "UPDATE Account SET balance =" + account.getBalance();

        try {
            PreparedStatement pstmt = cm.prepareStatement(SQL);
            boolean affectedRows = pstmt.execute();
            System.out.println(affectedRows);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public Account find(String username, String password) throws SQLException {


        Account account =null;

        String SQL = "SELECT * from account WHERE username='" + username + "' AND password='" + password + "'";
        PreparedStatement pstmt = null; try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {

            e.printStackTrace();
            throw new InvalidInputException(Message.INVALID_USERNAME_OR_PASS);
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                long balance = resultSet.getLong("balance");
                long person_id = resultSet.getLong("person_id");

                Person_tbl person_tbl = new Person_tbl(conn);
                account = new Account();


                //set account details
                account.setId(id);
                account.setUsername(username);
                account.setPassword(password);
                account.setBalance(balance);
                account.setPerson(person_tbl.getPersonById(person_id));
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InvalidInputException(Message.INVALID_USERNAME_OR_PASS);
           // System.out.println(Message.INVALID_USERNAME);
        }

        return account;
    }

    public long getBalance(long id){
        Account account = new Account();
        account.setId(id);
        String SQL = "SELECT  * from account where id=" + id;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {

                long balance = resultSet.getLong("balance");

                Person_tbl person_tbl = new Person_tbl(conn);

                account.setBalance(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getBalance(id);

    }


}

