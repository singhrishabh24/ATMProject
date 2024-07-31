package com.atm;

import javax.swing.*;
import java.awt.*;

public class ATM extends JFrame {

    public ATM() {
        UserDatabase userDatabase = new UserDatabase();
        setTitle("ATM System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new CardLayout());

        ATMPanel atmPanel = new ATMPanel(this, userDatabase);
        LoginPanel loginPanel = new LoginPanel(this, userDatabase, atmPanel);

        add(loginPanel, "LoginPanel");
        add(atmPanel, "ATMPanel");

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM());
    }
}
