
package bank.management.system;

import java.sql.*;

public class Connectivity {
    
    Connection c;
    Statement s;
    
    public Connectivity() {
        try{
            
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root","526543");
            s = c.createStatement();
            
            
        } catch (Exception e ){
            System.out.println(e);
        }
    }
    
}
