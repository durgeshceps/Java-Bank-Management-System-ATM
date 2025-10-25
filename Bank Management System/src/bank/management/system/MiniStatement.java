package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement - SBI");
        setLayout(null);

        JLabel bank = new JLabel("STATE BANK OF INDIA", SwingConstants.CENTER);
        bank.setFont(new Font("System", Font.BOLD, 18));
        bank.setBounds(80, 20, 250, 30);
        add(bank);

        JLabel card = new JLabel();
        card.setFont(new Font("System", Font.PLAIN, 14));
        card.setBounds(20, 60, 350, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setFont(new Font("System", Font.BOLD, 14));
        balance.setBounds(20, 480, 350, 20);
        add(balance);

        // Create a scrollable text area for transactions
        JTextArea mini = new JTextArea();
        mini.setFont(new Font("Courier New", Font.PLAIN, 14));
        mini.setEditable(false);
        mini.setBackground(Color.WHITE);
        mini.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(20, 100, 350, 360);
        add(scrollPane);

        try {
            Connectivity conn = new Connectivity();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE pinnumber = '" + pinnumber + "'");
            while (rs.next()) {
                String cardnum = rs.getString("cardnumber");
                card.setText("Card Number: " + cardnum.substring(0, 4) + "XXXXXXXX" + cardnum.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Connectivity conn = new Connectivity();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");

            StringBuilder statement = new StringBuilder();
            int balanceAmount = 0;

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                statement.append(String.format("%-15s %-10s %10s%n", date, type, amount));

                if (type.equalsIgnoreCase("Deposit")) {
                    balanceAmount += Integer.parseInt(amount);
                } else {
                    balanceAmount -= Integer.parseInt(amount);
                }
            }

            mini.setText("Date\t\tType\t\tAmount\n-----------------------------------\n" + statement);
            balance.setText("Available Balance: ₹ " + balanceAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setSize(400, 550);
        setLocation(30, 30);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("1460"); // test with your PIN number
    }
}
