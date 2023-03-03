/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class AdminHomePageController implements Initializable {

    @FXML
    private JFXButton userAd;
    @FXML
    private JFXButton eventsAd;
    @FXML
    private JFXButton boutiqueAd;
    @FXML
    private JFXButton articlesAd;
    @FXML
    private JFXButton artistsAd;
    @FXML
    private JFXButton equipAd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException {
        if (event.getSource()== userAd ){
             SceneController sc = new SceneController();
             sc.switchToScene8(event);
        }
    }
    
}
