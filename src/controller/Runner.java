package controller;

import java.sql.SQLException;

public class Runner {

    public static void main(String[] args) throws SQLException {
        ATM atm = new ATM();
        atm.runAtm();
        atm.logIn();


    }
}



