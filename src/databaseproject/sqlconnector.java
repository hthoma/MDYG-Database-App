/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harry Thomasian
 */
public class sqlconnector {
    private static Connection connection=null;
    private static Student selectedstudent = null;
    String username, password = null;
    

    public sqlconnector() {
    }
    
    public static Connection getConnector(){
        return connection;
    }

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
    
    public  static void addStudent(String FName, String MName, String LName, String Year,String PhoneNum, String RoomNum) throws SQLException{
           String querys= "SELECT * FROM mjubil1db.Student;";
            Statement queryStatement = connection.createStatement();
            ResultSet results = queryStatement.executeQuery(querys);
            int highestval = 0 ;
            int highestvalpay = 0;
            while(results.next())
            {
                Student astudent;
                  int val = Integer.parseInt(results.getString("StudentID"));
                  //int val2 = Integer.parseInt(results.getString("PayID"));
                  if (val > highestval)
                      highestval = val;
                  //if (val2 > highestvalpay)
                   //   highestvalpay = val;
            }   

        
        
        PreparedStatement preparedStmt = connection.prepareStatement("insert into Student(StudentID, FName, MName, LName, AcademicYear, FinAid, EnrollDate, PhoneNum, RoomNum)" +  " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        

        preparedStmt.setString (1, Integer.toString((highestval + 1)));
        preparedStmt.setString (2,FName);
        preparedStmt.setString (3,MName);
        preparedStmt.setString (4,LName);
        preparedStmt.setString (5,Year);
        preparedStmt.setInt (6,0);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        preparedStmt.setDate(7, date);
        preparedStmt.setString (8,PhoneNum);
        preparedStmt.setString (9,RoomNum);
        preparedStmt.execute();
        System.out.println("Added student " + FName);
        
        
    }
    public static void setstudent(Student student){
        selectedstudent = student;
    }
    
    public static Student getstudent(){
        return selectedstudent;
    }
    public ArrayList<Student> downloadStudents() throws SQLException{
        ArrayList<Student> students = new ArrayList<Student>();
          PreparedStatement updateStaff;
        Statement queryStatement = connection.createStatement();
        updateStaff = null;
            String querys= "SELECT * FROM mjubil1db.Student;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
                Student astudent;
            astudent = new Student(results.getString("FName"),results.getString("MName"),results.getString("LName"),results.getString("StudentID"), results.getString("RoomNum"), results.getString("DelegID"), results.getString("PhoneNum"));
                students.add(astudent);
            }   

        
        return students;
        
        
    }

    

      
}

