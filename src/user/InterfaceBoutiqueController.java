/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class InterfaceBoutiqueController implements Initializable {

    @FXML
    private Button ConsuProdB;
    @FXML
    private Button ReservePlaceB;
    @FXML
    private Button ConsulterPB;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    } 
    @FXML
     public void retourner (ActionEvent e) throws IOException{

  SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene6(e);

    

}
    @FXML
     public void retourner2 (ActionEvent e) throws IOException{
   SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene4(e);


 

}
    @FXML
      public void retourner3 (ActionEvent e) throws IOException{



        SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene5(e);

}

    @FXML
    private void back(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
sc.switchToScene9(event);
    }
    
}
