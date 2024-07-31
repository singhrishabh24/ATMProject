package com.atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private final JTextField userIdField;
    private final JPasswordField pinField;
    private final UserDatabase userDatabase;
    private final JFrame parentFrame;
    private final ATMPanel atmPanel;

    public LoginPanel(JFrame parentFrame, UserDatabase userDatabase, ATMPanel atmPanel) {
        this.parentFrame = parentFrame;
        this.userDatabase = userDatabase;
        this.atmPanel = atmPanel;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(10);

        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField(10);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userIdLabel, gbc);
        gbc.gridx = 1;
        add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pinLabel, gbc);
        gbc.gridx = 1;
        add(pinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = userIdField.getText();
            String pin = new String(pinField.getPassword());

            if (userDatabase.isValidUser(userId, pin)) {
                User user = userDatabase.getUser(userId);
                atmPanel.setCurrentUser(user);
                CardLayout cl = (CardLayout) parentFrame.getContentPane().getLayout();
                cl.show(parentFrame.getContentPane(), "ATMPanel");
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Invalid User ID or PIN", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
