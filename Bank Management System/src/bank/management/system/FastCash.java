package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b100, b500, b1000, b2000, b5000, b10000, exit;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        b100 = new JButton("100");
        b100.setBounds(170, 415, 150, 30);
        b100.addActionListener(this);
        image.add(b100);

        b500 = new JButton("500");
        b500.setBounds(355, 415, 150, 30);
        b500.addActionListener(this);
        image.add(b500);

        b1000 = new JButton("1000");
        b1000.setBounds(170, 450, 150, 30);
        b1000.addActionListener(this);
        image.add(b1000);

        b2000 = new JButton("2000");
        b2000.setBounds(355, 450, 150, 30);
        b2000.addActionListener(this);
        image.add(b2000);

        b5000 = new JButton("5000");
        b5000.setBounds(170, 485, 150, 30);
        b5000.addActionListener(this);
        image.add(b5000);

        b10000 = new JButton("10000");
        b10000.setBounds(355, 485, 150, 30);
        b10000.addActionListener(this);
        image.add(b10000);

        exit = new JButton("Back");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            dispose();
            new Transactions(pinnumber).setVisible(true);
            return;
        }

        String amountStr = ((JButton) ae.getSource()).getText();
        int amount = Integer.parseInt(amountStr);

        try {
            Connectivity c = new Connectivity();

            // Calculate balance
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            int balance = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                int amt = Integer.parseInt(rs.getString("amount"));
                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
            rs.close();

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // Insert withdraw transaction
            PreparedStatement ps = c.c.prepareStatement("INSERT INTO bank(pin, date, type, amount) VALUES(?, ?, ?, ?)");
            ps.setString(1, pinnumber);
            ps.setString(2, new Date().toString());
            ps.setString(3, "Withdrawl");
            ps.setString(4, amountStr);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Rs " + amountStr + " Debited Successfully");
            dispose();
            new Transactions(pinnumber).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
