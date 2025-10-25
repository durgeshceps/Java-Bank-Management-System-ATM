package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Back button
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        int balance = 0;

        try {
            Connectivity c = new Connectivity();

            // Use correct column name 'pin' and PreparedStatement for safety
            PreparedStatement ps = c.c.prepareStatement("SELECT * FROM bank WHERE pin = ?");
            ps.setString(1, pinnumber);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));
                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Display balance
        JLabel text = new JLabel("Your Account Balance is Rs " + balance);
        text.setBounds(170, 300, 600, 30);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        dispose();
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}
