/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Entities.user;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;
import SERVICES.userServices;
import user.SceneController;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class FXMLController implements Initializable {

    @FXML
    private JFXTextField nomtf;
    @FXML
    private JFXTextField prenomtf;
    @FXML
    private JFXTextField emailtf;
    @FXML
    private JFXPasswordField mdptf;
    @FXML
    private Button SignUp;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton quitter;
    @FXML
    private JFXButton altLogIn;
    @FXML
    private Label role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onButtonClick(ActionEvent event) throws IOException, SQLException {
       if (event.getSource() == SignUp){
           
          insert();
       }
       if(event.getSource() == quitter ){
           
           System.exit(0);
       }
        if(event.getSource() == altLogIn ){
           
           SceneController sc = new SceneController();
           sc.switchToScene2(event);
           
       }
           
    }

     public void insert() throws SQLException {
        
        
            userServices us = new userServices();
            String  Name= nomtf.getText();
            String Surname= prenomtf.getText();
            LocalDate Age = date.getValue();
            String Email= emailtf.getText();
            String Mdp= mdptf.getText();
            
            
            
            
              
             
            if (Name.isEmpty()||Surname.isEmpty()||Age==null||Email.isEmpty()||Mdp.isEmpty()){
                JOptionPane.showMessageDialog(null , "PlEASE FILL DATA ");
            } else  if(us.validateEmail(Email)== true){
            JOptionPane.showMessageDialog(null , "EMAIL ALREADY EXISTS  ");
            } else 
                
           
                
                {
                
                user p =new user(Name,Surname,Age,Email,Mdp,"aaa");
                us.ajouterPersonne(p);
            
           
        }

   
    
    
}
}

            
            
             

  
  
     

