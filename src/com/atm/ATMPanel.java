package com.atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ATMPanel extends JPanel {
    private final UserDatabase userDatabase;
    private final JFrame parentFrame;
    private User currentUser;
    private final JLabel balanceLabel;

    public ATMPanel(JFrame parentFrame, UserDatabase userDatabase) {
        this.parentFrame = parentFrame;
        this.userDatabase = userDatabase;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        balanceLabel = new JLabel("Balance: $0.00");

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Check Balance");
        JButton logoutButton = new JButton("Logout");

        depositButton.addActionListener(new DepositButtonListener());
        withdrawButton.addActionListener(new WithdrawButtonListener());
        balanceButton.addActionListener(new BalanceButtonListener());
        logoutButton.addActionListener(new LogoutButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(balanceLabel, gbc);

        gbc.gridy = 1;
        add(depositButton, gbc);

        gbc.gridy = 2;
        add(withdrawButton, gbc);

        gbc.gridy = 3;
        add(balanceButton, gbc);

        gbc.gridy = 4;
        add(logoutButton, gbc);
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    private class DepositButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountStr = JOptionPane.showInputDialog(parentFrame, "Enter amount to deposit:", "Deposit", JOptionPane.PLAIN_MESSAGE);
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    currentUser.deposit(amount);
                    updateBalance();
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class WithdrawButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountStr = JOptionPane.showInputDialog(parentFrame, "Enter amount to withdraw:", "Withdraw", JOptionPane.PLAIN_MESSAGE);
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0 && amount <= currentUser.getBalance()) {
                    currentUser.withdraw(amount);
                    updateBalance();
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid amount or insufficient funds", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class BalanceButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateBalance();
        }
    }

    private class LogoutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentUser = null;
            CardLayout cl = (CardLayout) parentFrame.getContentPane().getLayout();
            cl.show(parentFrame.getContentPane(), "LoginPanel");
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", currentUser.getBalance()));
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateBalance();
    }
}
