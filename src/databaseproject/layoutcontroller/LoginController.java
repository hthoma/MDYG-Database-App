/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject.layoutcontroller;

import databaseproject.sqlconnector;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author hthoma
 */
public class LoginController implements Initializable {
    String strusername,strpassword;
    boolean connected;
    @FXML
    private Label label;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text errormsg;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws InterruptedException {
 
       strusername = username.getText();
       strpassword = password.getText();
       sqlconnector connect;
        connect = new sqlconnector(strusername,strpassword);
        try {
           connected = connect.connectDb();
        } catch (ClassNotFoundException ex) {
            errormsg.setText("ClassNotFoundException Occured: If you can see this a programmer really messed up");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            errormsg.setText("Incorrect Username or Password");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            errormsg.setText("Instantiation Exception Occured: User should not be seeing this");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            errormsg.setText("Illegal Access Occured: User should not be seing this");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connected){
            System.out.println("Connected");
      
                         try {
                             Stage stage = new Stage();
                             Parent root;
                             root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
                             Scene scene = new Scene(root);
                             stage.setScene(scene);
                             stage.show();
                             ((Node)(event.getSource())).getScene().getWindow().hide();
                            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                     }
        else
            System.out.println("Couldn't Connect");
      
       
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
