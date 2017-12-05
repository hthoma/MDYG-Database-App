/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject.layoutcontroller;

import databaseproject.Staff;
import databaseproject.Student;
import databaseproject.sqlconnector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hthoma
 */
public class MainViewController implements Initializable {




@FXML private Menu moreselect;
@FXML private MenuItem helpselect;   
@FXML private MenuItem showstaff; 
@FXML private TableView StudentTable ;   
@FXML private Button refreshlist;
@FXML private Button inspectstudent;
@FXML private Button addstudent;
@FXML private TextField Nametb;
@FXML private TextField PhoneNumtb;
@FXML private TextField RoomNumtb;
@FXML private TextField Roleet;
@FXML private TextField SIDtb;
@FXML private Button searchstudent;
@FXML private MenuItem quitbutton;
@FXML private CheckBox checkbox;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
      renderList();
    }  
    
    

    
    @FXML
    private void handleMenuClick(ActionEvent event) throws InterruptedException, IOException{
     
        MenuItem target  = (MenuItem) event.getSource();
        switch(target.getId()){
            case "helpselect":
                             Stage stage = new Stage();
                             Parent root;
                             root = FXMLLoader.load(getClass().getClassLoader().getResource("databaseproject/layout/HelpInfo.fxml"));
                             Scene scene = new Scene(root);
                             stage.setScene(scene);
                             stage.show();
                            
                         break; 
            case "showstaff": renderStaff();
            break;
            case "quitbutton": System.exit(0);
            break;
        }
        
    }   
    
     @FXML
    private void handleButtonClick(ActionEvent event) throws InterruptedException, IOException, SQLException{
     
        Button target  = (Button) event.getSource();
        switch(target.getId()){
            case "refreshlist":renderList();
            break;
            case "inspectstudent": 
           if(StudentTable.getSelectionModel().getSelectedItem() != null){
            Student astudent = (Student)StudentTable.getSelectionModel().getSelectedItem();
           sqlconnector sql = new sqlconnector();
            sql.setstudent(astudent);
            System.out.println(astudent.getFName() + " selected!");
            Stage stage = new Stage();
                             Parent root;
                             root = FXMLLoader.load(getClass().getClassLoader().getResource("databaseproject/layout/StudentView.fxml"));
                             Scene scene = new Scene(root);
                             stage.setScene(scene);
                             stage.show();
           }
           
            break;
            case "addstudent":  Stage stage = new Stage();
                             Parent root;
                             root = FXMLLoader.load(getClass().getClassLoader().getResource("databaseproject/layout/AddStudent.fxml"));
                             Scene scene = new Scene(root);
                             stage.setScene(scene);
                             stage.show();
            break;
            case "searchstudent":  
                 sqlconnector sql = new sqlconnector();
                 ArrayList<Student> searchedstudents = sql.searchStudent(Nametb.getText(),RoomNumtb.getText(),PhoneNumtb.getText(),SIDtb.getText(),Roleet.getText(),checkbox.isSelected());
                  ObservableList<Student> oListStudent = FXCollections.observableArrayList(searchedstudents);
                   TableColumn<Student, String> FnameColumn = new TableColumn<>("First Name");
        FnameColumn.setMinWidth(120);
        FnameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        
 
    TableColumn<Student, String> MnameColumn = new TableColumn<>("Middle Name");
        MnameColumn.setMinWidth(120);
        MnameColumn.setCellValueFactory(new PropertyValueFactory<>("Mname"));
        
    TableColumn<Student, String> LnameColumn = new TableColumn<>("Last Name");
        LnameColumn.setMinWidth(120);
        LnameColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));
       
    TableColumn<Student, String> StudentID = new TableColumn<>("SID");
        StudentID.setMinWidth(100);
        StudentID.setCellValueFactory(new PropertyValueFactory<>("SID"));
       //TODO: Make display delegation name, not number.
    TableColumn<Student, String> Delegation = new TableColumn<>("Delegation");
        Delegation.setMinWidth(120);
        Delegation.setCellValueFactory(new PropertyValueFactory<>("DName"));
        
    TableColumn<Student, String> RoomNum = new TableColumn<>("Room #");
        RoomNum.setMinWidth(60);
        RoomNum.setCellValueFactory(new PropertyValueFactory<>("RNum"));
        
    TableColumn<Student, String> EContact = new TableColumn<>("Emergency Contact");
        EContact.setMinWidth(210);
        EContact.setCellValueFactory(new PropertyValueFactory<>("PhoneNum"));
                  StudentTable.setItems(oListStudent);
                   StudentTable.getColumns().clear();
                   StudentTable.getColumns().addAll(StudentID,FnameColumn,MnameColumn,LnameColumn,Delegation,RoomNum,EContact);
                   break;
    
        }
        
    }   
    
    
    private void renderStaff(){
         sqlconnector sql = new sqlconnector();
        Connection connect = sql.getConnector();
         ArrayList<Staff> staff = null;
         try {
        staff = sql.downloadStaff();
    } catch (SQLException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
          ObservableList<Staff> oListStaff = FXCollections.observableArrayList(staff);
        TableColumn<Staff, String> StaffName = new TableColumn<>("Name");
        StaffName.setMinWidth(60);
        StaffName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<Staff, String> StaffID = new TableColumn<>("Staff ID");
        StaffID.setMinWidth(120);
        StaffID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<Staff, String> RoomNum = new TableColumn<>("Room #");
        RoomNum.setMinWidth(60);
        RoomNum.setCellValueFactory(new PropertyValueFactory<>("RoomNum"));
        StudentTable.setItems(oListStaff);
    StudentTable.getColumns().clear();
    StudentTable.getColumns().addAll(StaffName,StaffID,RoomNum);
        
    }
    
    private void renderList(){
          sqlconnector sql = new sqlconnector();
        Connection connect = sql.getConnector();
        
     ArrayList<Student> students = null;
    try {
        students = sql.downloadStudents();
    } catch (SQLException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println("List Refreshed!");
    for(int i = 0;i < students.size();i++){
        Student aStudent = students.get(i);
       System.out.println(aStudent.toString());
                   
    }

    
    ObservableList<Student> oListStudent = FXCollections.observableArrayList(students);
    TableColumn<Student, String> FnameColumn = new TableColumn<>("First Name");
        FnameColumn.setMinWidth(120);
        FnameColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        
 
    TableColumn<Student, String> MnameColumn = new TableColumn<>("Middle Name");
        MnameColumn.setMinWidth(120);
        MnameColumn.setCellValueFactory(new PropertyValueFactory<>("Mname"));
        
    TableColumn<Student, String> LnameColumn = new TableColumn<>("Last Name");
        LnameColumn.setMinWidth(120);
        LnameColumn.setCellValueFactory(new PropertyValueFactory<>("Lname"));
       
    TableColumn<Student, String> StudentID = new TableColumn<>("SID");
        StudentID.setMinWidth(100);
        StudentID.setCellValueFactory(new PropertyValueFactory<>("SID"));
       //TODO: Make display delegation name, not number.
    TableColumn<Student, String> Delegation = new TableColumn<>("Delegation");
        Delegation.setMinWidth(120);
        Delegation.setCellValueFactory(new PropertyValueFactory<>("DName"));
        
    TableColumn<Student, String> RoomNum = new TableColumn<>("Room #");
        RoomNum.setMinWidth(60);
        RoomNum.setCellValueFactory(new PropertyValueFactory<>("RNum"));
        
    TableColumn<Student, String> EContact = new TableColumn<>("Emergency Contact");
        EContact.setMinWidth(210);
        EContact.setCellValueFactory(new PropertyValueFactory<>("PhoneNum"));
        
    StudentTable.setItems(oListStudent);
    StudentTable.getColumns().clear();
    StudentTable.getColumns().addAll(StudentID,FnameColumn,MnameColumn,LnameColumn,Delegation,RoomNum,EContact);
    
    
    }
    
    
    
    
}
