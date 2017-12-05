package databaseproject.layoutcontroller;

import databaseproject.Student;
import databaseproject.SQLConnector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * @author hthoma
 */
public class StudentViewController implements Initializable {

    @FXML
    private Text SIDte;
    @FXML
    private Text SNamete;
    @FXML
    private Text RoomNumte;
    @FXML
    private Text Roomate1te;
    @FXML
    private Text Roomate2te;
    @FXML
    private Text Roomate3te;
    @FXML
    private Text PhoneNumte;
    @FXML
    private Text Delegationte;
    @FXML
    private Text DelegationNumte;
    @FXML
    private Text Rolete;
    @FXML
    private Text BillNumte;
    @FXML
    private Text Comitteete;
    @FXML
    private Text HouseSenatete;
    @FXML
    private Text ComitteeStatuste;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SQLConnector sql = new SQLConnector();
        Student astudent = sql.getstudent();

        System.out.println(astudent.toString());
        SIDte.setText(astudent.getSID());
        SNamete.setText(astudent.getFName() + " " + astudent.getMname() + " " + astudent.getLname());
        RoomNumte.setText(astudent.getRNum());
        PhoneNumte.setText(astudent.getPhoneNum());
        DelegationNumte.setText(astudent.getDelegation());
        Delegationte.setText(astudent.getDName());

        System.out.println(astudent.toString());
    }
}
