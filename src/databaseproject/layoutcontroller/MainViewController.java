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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
@FXML private TableView StudentName;   
@FXML private Button refreshlist;


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
                             ((Node)(event.getSource())).getScene().getWindow().hide();
                         break; 
        }
        
    }   
    
     @FXML
    private void handleButtonClick(ActionEvent event) throws InterruptedException, IOException{
     
        Button target  = (Button) event.getSource();
        switch(target.getId()){
            case "refreshlist":renderList();
                            
                          
        }
        
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
    //Make columns
    
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
        Delegation.setCellValueFactory(new PropertyValueFactory<>("Delegation"));
        
    TableColumn<Student, String> RoomNum = new TableColumn<>("Room #");
        RoomNum.setMinWidth(60);
        RoomNum.setCellValueFactory(new PropertyValueFactory<>("RNum"));
        
    TableColumn<Student, String> EContact = new TableColumn<>("Emergency Contact");
        EContact.setMinWidth(210);
        EContact.setCellValueFactory(new PropertyValueFactory<>("PhoneNum"));
        
    StudentName.setItems(oListStudent);
    StudentName.getColumns().addAll(StudentID,FnameColumn,MnameColumn,LnameColumn,Delegation,RoomNum,EContact);
    
    
    }
    
    
    
    
}
