
package bank.management.system;

import javax.swing.*; // JFrame ko import karne ke liye
import java.awt.*;   // image or color bagera add karne ke liye is class ka usee karte hai.
import java.awt.event.*;


public class signupTwo  extends JFrame implements ActionListener {
    
    long random;
    JTextField pan,aadhar;
    JRadioButton syes,sno,eyes,eno;
   
     JButton next;
     JComboBox religion,category,income,education,occupation;
        String formno;    
            
            
    signupTwo( String formno) {
        
        this.formno = formno;
        
        
        
                         setLayout(null); // border formno.setBound ka line use kiya hai.
        
                         setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        
        
                         JLabel additionalDetails = new JLabel("Page 2 : Additional Detaile");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290,80,400,30);
        add(additionalDetails);
        
        JLabel name = new JLabel("Religion:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100,140,100,30);
        add(name);
         
        String valReligion[] = {"Hindu","Muslim","Sikh","Cristian","other"};
         religion = new JComboBox (valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);
                
     
        
        JLabel fname = new JLabel("Category:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100,190,200,30);
        add(fname);
        
                String valcategory[] = {"General","OBC","SC","ST","OTHER"};
                 category = new JComboBox (valcategory);
                 category.setBounds(300,190,400,30);
                 category.setBackground(Color.WHITE);
                 add(category);
                 
       
        JLabel dob = new JLabel("Income:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100,240,200,30);
        add(dob);
        
        String incomecategory[] = {"Null","<1,50,000","<2,50,000","<5,00,000","Upto 10,00,000"};
                 income = new JComboBox (incomecategory);
                 income.setBounds(300,240,400,30);
                 income.setBackground(Color.WHITE);
                 add(income);
       
        
        JLabel gender = new JLabel("Educational:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100,290,200,30);
        add(gender);
        
        
              JLabel email = new JLabel("Qualification:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100,315,200,30);
        add(email);
        
         String educationValues[] = {"Non-Graduation","Graduate","Post-Graduation","Doctrate","Others"};
                 education = new JComboBox (educationValues);
                 education.setBounds(300,315,400,30);
                 education.setBackground(Color.WHITE);
                 add(education);
        
         
        
        JLabel marital = new JLabel("Occupation:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100,390,200,30);
        add(marital);
        
         String OccupationStatus[] = {"Salaried","Self-Employed","Bussiness","Student","Influencer","Youtuber","Trader","Others"};
                 occupation = new JComboBox (OccupationStatus);
                 occupation.setBounds(300,390,400,30);
                 occupation.setBackground(Color.WHITE);
                 add(occupation);
        
                  
                JLabel panNo = new JLabel("Pan Number:");
        panNo.setFont(new Font("Raleway", Font.BOLD, 20));
        panNo.setBounds(100,440,200,30);
        add(panNo);
        
          pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD,14));
        pan.setBounds(300,440,400,30);
        add(pan);
       
        JLabel adhar = new JLabel("Adhar Number:");
        adhar.setFont(new Font("Raleway", Font.BOLD, 20));
        adhar.setBounds(100,490,200,30);
        add(adhar);
        
          aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway", Font.BOLD,14));
        aadhar.setBounds(300,490,400,30);
        add(aadhar);
        
        JLabel senior = new JLabel("Senior Citizen:");
        senior.setFont(new Font("Raleway", Font.BOLD, 20));
        senior.setBounds(100,540,200,30);
        add(senior);
        
        syes = new JRadioButton("Yes");
        syes.setBounds(300,540,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);
        
        sno = new JRadioButton("NO");
        sno.setBounds(450,540,100,30);
        sno.setBackground(Color.WHITE);
        add(sno);
        
        ButtonGroup seniorgroup = new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);
        
        
         JLabel eacc = new JLabel("Exisiting Account:");
        eacc.setFont(new Font("Raleway", Font.BOLD, 20));
        eacc.setBounds(100,590,200,30);
        add(eacc);
        
        eyes = new JRadioButton("Yes");
        eyes.setBounds(300,590,100,30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
        
        eno = new JRadioButton("NO");
        eno.setBounds(450,590,100,30);
        eno.setBackground(Color.WHITE);
        add(eno);
        
        ButtonGroup eaccgroup = new ButtonGroup();
        eaccgroup.add(eyes);
        eaccgroup.add(eno);
        
         
        
        
         next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        
        
        
        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        String formno = this.formno; //long
        String sreligion = (String) religion.getSelectedItem(); // setText
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        
       
        String seniorcitizen = null;
        if(syes.isSelected()){
            seniorcitizen = "Yes";
        } else if (sno.isSelected()){
            seniorcitizen = "No";
        }
    
                          
               String existingaccount = null;
                  if (eyes.isSelected()){
                existingaccount = "Yes";
       }          else if (eno.isSelected ()){
       existingaccount = "No";
}       
      String span = pan.getText();
      String saadhar = aadhar.getText();
    
    
      try {
         Connectivity c = new Connectivity(); //  connecting to mysql database.
          String query = "insert into signuptwo values ('"+formno+"','"+sreligion+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+span+"','"+saadhar+"','"+seniorcitizen+"','"+existingaccount+"')";
            c.s.executeUpdate(query);
      
            //signup3 Object
            setVisible(false);
            new signupThree(formno).setVisible(true);
      }
     
     catch (Exception e ) {
      System.out.println(e);                             
                                
      }
}
        public static void main(String args[]) {
         new signupTwo("");
      }
    
} 
