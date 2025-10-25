package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Title
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        // New PIN label
        JLabel pintext = new JLabel("NEW PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 320, 180, 25);
        image.add(pin);

        // Re-enter PIN label
        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 360, 180, 25);
        image.add(repin);

        // Buttons
        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = new String(pin.getPassword());
                String rpin = new String(repin.getPassword());

                // Input validation
                if (npin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if (rpin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                Connectivity conn = new Connectivity();

                // Update bank table
                PreparedStatement ps1 = conn.c.prepareStatement("UPDATE bank SET pin=? WHERE pin=?");
                ps1.setString(1, rpin);
                ps1.setString(2, pinnumber);
                ps1.executeUpdate();

                // Update login table
                PreparedStatement ps2 = conn.c.prepareStatement("UPDATE login SET pinnumber=? WHERE pinnumber=?");
                ps2.setString(1, rpin);
                ps2.setString(2, pinnumber);
                ps2.executeUpdate();

                // Update signupThree table
                PreparedStatement ps3 = conn.c.prepareStatement("UPDATE signupThree SET pinnumber=? WHERE pinnumber=?");
                ps3.setString(1, rpin);
                ps3.setString(2, pinnumber);
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                dispose();
                new Transactions(rpin).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { // Back button
            dispose();
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
