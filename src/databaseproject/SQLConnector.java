package databaseproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * @author Harry Thomasian
 */
public class SQLConnector {

    private static Connection connection = null;
    private static Student selectedstudent = null;
    private String username, password = null;

    public SQLConnector() {}

    public static Connection getConnector() {
        return connection;
    }

    public SQLConnector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ArrayList<Staff> downloadStaff() throws SQLException {
        ArrayList<Staff> staff = new ArrayList<Staff>();
        Statement queryStatement = connection.createStatement();

        String querys = "SELECT * FROM mjubil1db.Staff;";
        ResultSet results = queryStatement.executeQuery(querys);
        while (results.next()) {
            Staff astaff;
            astaff = new Staff(results.getString("StaffID"), results.getString("RoomNum"));
            staff.add(astaff);
        }
        return staff;
    }

    public boolean connectDb() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/mjubil1db", username, password);
        return true;
    }

    public ArrayList<Student> searchStudent(String Name, String RoomNum, String PhoneNum, String ID) throws SQLException {
        ArrayList<Student> students;
        students = downloadStudents();

        if (!Name.isEmpty()) {
            Iterator<Student> iter = students.iterator();
            while (iter.hasNext()) {
                Student checkstudent = iter.next();
                if (!(checkstudent.getFName() + " " + checkstudent.getMname() + " " + checkstudent.getLname()).contains(Name)) {
                    iter.remove();
                }
            }
        }

        if (!PhoneNum.isEmpty()) {
            Iterator<Student> iter = students.iterator();
            while (iter.hasNext()) {
                Student checkstudent = iter.next();
                if (!(checkstudent.getPhoneNum()).contains(PhoneNum)) {
                    iter.remove();
                }
            }
        }
        if (!RoomNum.isEmpty()) {
            Iterator<Student> iter = students.iterator();
            while (iter.hasNext()) {
                Student checkstudent = iter.next();
                if (!(checkstudent.getRNum()).contains(RoomNum)) {
                    iter.remove();
                }
            }
        }

        if (!ID.isEmpty()) {
            Iterator<Student> iter = students.iterator();
            while (iter.hasNext()) {
                Student checkstudent = iter.next();
                if (!(checkstudent.getSID()).contains(ID)) {
                    iter.remove();
                }
            }
        }
        return students;
    }

    public static void addStudent(String FName, String MName, String LName, String Year, String PhoneNum, String RoomNum, String DName) throws SQLException {
        String querys = "SELECT * FROM mjubil1db.Student;";
        Statement queryStatement = connection.createStatement();
        ResultSet results = queryStatement.executeQuery(querys);
        int highestval = 0;
        int highestvalpay = 0;
        String delegation;
        while (results.next()) {
            Student astudent;
            int val = Integer.parseInt(results.getString("StudentID"));

            if (val > highestval) {
                highestval = val;
            }
        }

        PreparedStatement preparedStmt = connection.prepareStatement("insert into Student(StudentID, FName, MName, LName, AcademicYear, FinAid, EnrollDate, PhoneNum, RoomNum, DelegID)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        String num = Integer.toString((highestval + 1));
        int strLength = 6;
        String pad = "";
        for (int i = 0; i < strLength - num.length(); i++) {
            pad += "0";
        }
        num = pad + num;

        preparedStmt.setString(1, num);
        preparedStmt.setString(2, FName);
        preparedStmt.setString(3, MName);
        preparedStmt.setString(4, LName);
        preparedStmt.setString(5, Year);
        preparedStmt.setInt(6, 0);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        preparedStmt.setDate(7, date);
        preparedStmt.setString(8, PhoneNum);
        preparedStmt.setString(9, RoomNum);
        preparedStmt.setString(10, DName);
        preparedStmt.execute();
        System.out.println("Added student " + FName);

    }

    public static void setstudent(Student student) {
        selectedstudent = student;
    }

    public static Student getstudent() {
        return selectedstudent;
    }

    public ArrayList<Student> downloadStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<Student>();
        Statement queryStatement = connection.createStatement();

        String querys = "SELECT * FROM mjubil1db.Student;";
        ResultSet results = queryStatement.executeQuery(querys);
        while (results.next()) {
            Student astudent;
            astudent = new Student(results.getString("FName"), results.getString("MName"), results.getString("LName"), results.getString("StudentID"), results.getString("RoomNum"), results.getString("DelegID"), results.getString("PhoneNum"));

            Statement queryStatement2 = connection.createStatement();
            String querys2 = "Select * from Delegation where DelID = '" + astudent.getDelegation() + "';";
            ResultSet resultsDeleg = queryStatement2.executeQuery(querys2);
            resultsDeleg.next();
            astudent.setDName(resultsDeleg.getString("DName"));
            
            /*   Statement queryStatement3 = connection.createStatement();
                    String querys3= "select Paymentplan.Amtdue " +
                               "from mjubil1db.Student, mjubil1db.Paymentplan " +
                        "where Student.PayID = Paymentplan.PayID " +
                            "and StudentID = '" + astudent.getSID() +             
                            "';";
                    resultsDeleg = queryStatement3.executeQuery(querys3);
                    resultsDeleg.next();
                    int amountdue = resultsDeleg.getInt("Amtdue");
                    if (amountdue == 0)
                    astudent.setPaidUp(true);
                    else
                    astudent.setPaidUp(false);
                    
                 
                     Statement queryStatement4 = connection.createStatement();
                    String querys4= "select FName, LName, Role" +
                                     "from Student, Role " +
                                    "where Student.StudentID = Role.StudID " +
                                    "and Student.StudentID = '" + astudent.getSID() + "';";
                    resultsDeleg = queryStatement4.executeQuery(querys4);
                    resultsDeleg.next();
                    astudent.setRole(resultsDeleg.getString("Role"));   
             */
            
            students.add(astudent);
        }
        return students;
    }

}
