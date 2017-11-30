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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
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
@FXML private TableColumn StudentName;   


      @Override
    public void initialize(URL url, ResourceBundle rb) {
        sqlconnector sql = new sqlconnector();
        Connection connect = sql.getConnector();
        
     ArrayList<Student> students = null;
    try {
        students = sql.downloadStudents();
    } catch (SQLException ex) {
        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    for(int i = 0;i < students.size();i++){
        Student aStudent = students.get(i);
       System.out.println(aStudent.toString());
                   
    }
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
    
    
    
    
}
