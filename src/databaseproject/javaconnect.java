/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hthoma
 */
public class javaconnect {
    private static Connection connection=null;
    String username, password = null;
    

    public javaconnect(String username, String password){
    this.username = username;
    this.password = password; 
    
     
 }   
        public javaconnect(){
    this.username = "NULL";
    this.password = "NULL"; 
    
     
 }   
       
    
    public boolean connectDb() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/mjubil1db", username, password);
        return true;
    }
    
}
