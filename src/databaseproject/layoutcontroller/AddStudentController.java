/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject.layoutcontroller;

import databaseproject.sqlconnector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author hthoma
 */
public class AddStudentController implements Initializable {
    
    
    @FXML private Button addstudent;
    @FXML private TextField FNametb;
    @FXML private TextField LNametb;
    @FXML private TextField MNametb;
    @FXML private TextField RoomNumtb;
    @FXML private TextField phonetb;
    @FXML private TextField Delegationnumtb;
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
     @FXML
    private void handleButtonClick(ActionEvent event) throws InterruptedException, IOException{
        Button target  = (Button) event.getSource();
        switch(target.getId()){
            case "addstudent": 
                if(FNametb.getText() == "" || MNametb.getText() == "" || LNametb.getText() == "" || RoomNumtb.getText() == "" || phonetb.getText() == "" || Delegationnumtb.getText() == "")
                    System.out.println("Error, cannot leave a field empty");
                else{
                    sqlconnector sql = new sqlconnector();
                    sql.runSQL("insert into Student values(T001," + FNametb.getText() + " , " + MNametb.getText() + " , " + LNametb.getText() + " , " + "Sophmore, 0, 2018," +  phonetb.getText() + "," + RoomNumtb.getText() + ", 001 , 001 ," + Delegationnumtb.getText() +");");
                }
                 
        }
    }
}
