/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject.layoutcontroller;

import databaseproject.Student;
import databaseproject.sqlconnector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author hthoma
 */
public class StudentViewController implements Initializable {
    
    
      @FXML private Text SIDte;
      @FXML private Text SNamete;
      @FXML private Text RoomNumte;
      @FXML private Text Roomate1te;
      @FXML private Text Roomate2te;
      @FXML private Text Roomate3te;
      @FXML private Text PhoneNumte;
      @FXML private Text Delegationte;
      @FXML private Text DelegationNumte;
      @FXML private Text Rolete;
      @FXML private Text BillNumte;
      @FXML private Text Comitteete;
      @FXML private Text HouseSenatete;
      @FXML private Text ComitteeStatuste;
      @FXML private Text adv1;
      @FXML private Text adv2;
      
      

  @Override
    public void initialize(URL url, ResourceBundle rb) {
    sqlconnector sql = new sqlconnector();
    Student astudent = sql.getstudent();
            
    System.out.println(astudent.toString());
    SIDte.setText(astudent.getSID());
    SNamete.setText(astudent.getFName() + " " + astudent.getMname() + " " + astudent.getLname());
    RoomNumte.setText(astudent.getRNum());
    PhoneNumte.setText(astudent.getPhoneNum());
    DelegationNumte.setText(astudent.getDelegation());
    Delegationte.setText(astudent.getDName());
    adv1.setText(astudent.getAdv1());
    adv2.setText(astudent.getAdv2());
          System.out.println(astudent.toString());
    }  
}
