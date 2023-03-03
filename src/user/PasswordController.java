/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class PasswordController implements Initializable {

  
    @FXML
    private JFXButton return1;
    @FXML
    private JFXPasswordField pwd;
    @FXML
    private JFXPasswordField pwdconfirm;
    @FXML
    private JFXButton savepwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
    
         if(event.getSource() == savepwd ){
        if(changepwd()== true )  {
           
           sc.switchToScene2(event);
        }
         
           
         
    }
          if(event.getSource() == return1 ){
           
           
           sc.switchToScene4(event);
    }
    }

 public boolean changepwd() {
     
       userServices us = new userServices();
    boolean   result = false;
      
           String  passwd = pwdconfirm.getText();
              if (pwd.getText().isEmpty()||pwdconfirm.getText().isEmpty()){
                JOptionPane.showMessageDialog(null , "PlEASE FILL DATA ");
                
                }else if (!pwdconfirm.getText().equals(pwd.getText())) {
                        JOptionPane.showMessageDialog(null , "Wrong Confirmation ");
                } else {
                 us.updatepwd(passwd);
                 JOptionPane.showMessageDialog(null , "Password Updated Successfully Please Login with your new Password ");
              result = true;
                
                }
        return result;

            
} 
}


