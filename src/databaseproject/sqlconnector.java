/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
    
    public ArrayList<Student> searchStudent(String Name,String RoomNum,String BillNum,String PhoneNum) throws SQLException{
        ArrayList<Student> students;
        students = downloadStudents();
        
        if(!Name.isEmpty()){
           Iterator<Student> iter = students.iterator();
           while (iter.hasNext()) {
            Student checkstudent = iter.next();
        if (!(checkstudent.getFName() +  " " + checkstudent.getMname()+ " " + checkstudent.getLname()).contains(Name))
            iter.remove();
}
        }
        
           if(!PhoneNum.isEmpty()){
           Iterator<Student> iter = students.iterator();
           while (iter.hasNext()) {
            Student checkstudent = iter.next();
        if (!(checkstudent.getPhoneNum()).contains(PhoneNum))
            iter.remove();
}
        }
                 if(!RoomNum.isEmpty()){
           Iterator<Student> iter = students.iterator();
           while (iter.hasNext()) {
            Student checkstudent = iter.next();
        if (!(checkstudent.getRNum()).contains(RoomNum))
            iter.remove();
}
        }
                 
                                if(!BillNum.isEmpty()){
           Iterator<Student> iter = students.iterator();
           while (iter.hasNext()) {
            Student checkstudent = iter.next();
        if (!(checkstudent.getRNum()).contains(BillNum))//Not implemented yet!
            iter.remove();
}
        }
        
        return students;
        
   
    }
    
    
    
    
    public  static void addStudent(String FName, String MName, String LName, String Year,String PhoneNum, String RoomNum, String DName) throws SQLException{
           String querys= "SELECT * FROM mjubil1db.Student;";
            Statement queryStatement = connection.createStatement();
            ResultSet results = queryStatement.executeQuery(querys);
            int highestval = 0 ;
            int highestvalpay = 0;
            String delegation;
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

        
  
         //   querys= "Select DellID from Delegation where DName= ' " + DName + " ';";
         //   queryStatement = connection.createStatement();
         //   results = queryStatement.executeQuery(querys);
            
            while(results.next())
            {
                
                  delegation = results.getString("DellID");
                 
                  
            }   

        
        PreparedStatement preparedStmt = connection.prepareStatement("insert into Student(StudentID, FName, MName, LName, AcademicYear, FinAid, EnrollDate, PhoneNum, RoomNum, DelegID)" +  " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        

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
        preparedStmt.setString (10,"101");
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

