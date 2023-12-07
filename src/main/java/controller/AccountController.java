package src.main.java.controller;

import javafx.scene.control.Label;
import src.main.java.model.Account;

public class AccountController {

    public Label totalBalanceLabel;
    private Account account;

    public void loadAccount(Account account){

        System.out.println(account);
    }
}
