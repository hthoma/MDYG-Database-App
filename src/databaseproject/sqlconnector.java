/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harry Thomasian
 */
public class sqlconnector {
    private static Connection connection=null;
    String username, password = null;
    

    public sqlconnector(String username, String password){
    this.username = username;
    this.password = password; 
    
     
 }   

    public boolean connectDb() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/mjubil1db", username, password);
        return true;
    }
    
    public String runSQL(String sqlcommand){
        //TODO: Create runSQL class
        return "Not done yet";
    }
    
    public ArrayList<Student> downloadStudents() throws SQLException{
        
          PreparedStatement updateStaff;
        Statement queryStatement = connection.createStatement();
        updateStaff = null;
            String querys= "SELECT FName, LName FROM mjubil1db.Student;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
                
                System.out.print(results.getString("FName"));
                System.out.print("    ");
                System.out.print(results.getString("LName"));
                System.out.println();
            }   

        
        return null;
    }
}
