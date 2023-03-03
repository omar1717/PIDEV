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
public class InternauteController implements Initializable {

    @FXML
    private JFXButton espaceJ;
    @FXML
    private JFXButton Intsignup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException {
        if (event.getSource()== Intsignup){
            SceneController sc = new SceneController();
            sc.switchToScene1(event);
        }
    }
    
}
