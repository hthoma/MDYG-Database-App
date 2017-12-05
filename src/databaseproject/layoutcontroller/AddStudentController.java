/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject.layoutcontroller;

import databaseproject.Student;
import databaseproject.sqlconnector;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 *
 * @author hthoma
 */
public class AddStudentController implements Initializable {
    String FName,MName,LName,RoomNum,Phone;
    String Delegationstr = "101";
    
    @FXML private Button addstudent;
    @FXML private TextField FNametb;
    @FXML private TextField LNametb;
    @FXML private TextField MNametb;
    @FXML private TextField RoomNumtb;
    @FXML private TextField PhoneNumtb;
    @FXML private TextField Delegation;
    
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
     @FXML
    private void handleButtonClick(ActionEvent event) throws InterruptedException, IOException{
        Button target  = (Button) event.getSource();
        switch(target.getId()){
            case "addstudent": 
             //   if(FNametb.getText().equals("")  || MNametb.getText().equals("") || LNametb.getText().equals("") || RoomNumtb.getText().equals("")|| phonetb.getText().equals("") || Delegationnumtb.getText().equals(""))
                //    System.out.println("Error, cannot leave a field empty");
                //else{
            try {
                sqlconnector sql = new sqlconnector();
                FName = FNametb.getText();
                MName = MNametb.getText();
                LName = LNametb.getText();
                Phone = PhoneNumtb.getText();
                RoomNum = RoomNumtb.getText();
                Delegationstr = Delegation.getText();
                System.out.println("DELEGATION STRING " +Delegationstr);
                ArrayList<Student> students = sql.downloadStudents();
                Iterator<Student> iter = students.iterator();
                while (iter.hasNext()) {
                 Student checkstudent = iter.next();
                 System.out.println("IN CHECK STUDENT" + checkstudent.toString());
                 if ((checkstudent.getDName()).contains(Delegationstr)){
                     System.out.println("In checkstudent with name" +checkstudent.getDName());
                   Delegationstr = checkstudent.getDelegation();
                
                 }
                
}
        
                System.out.println(FName + MName + LName + Phone + RoomNum);
                sql.addStudent(FName, MName, LName, "Freshman" ,Phone, RoomNum, Delegationstr);
            }
            catch (SQLException ex) {
                Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ((Node)(event.getSource())).getScene().getWindow().hide();
              
             break;    
        }
    }
}
