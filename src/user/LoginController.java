/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Entities.user;
import Utils.MyConnection;
import user.SceneController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import SERVICES.userServices;
import user.ProfileController;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class LoginController implements Initializable {

    
    
    
   
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton altSignUp;
    @FXML
    private JFXTextField tf1;
    @FXML
    private JFXPasswordField tf2;

    
     public static JFXTextField lab1;
    
    public static JFXPasswordField  lab2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      LoginController.lab1 =tf1;
      LoginController.lab2 =tf2;
    }    
   
    
            
  
        
        
    @FXML
    private void HandleButtonClick(ActionEvent event) throws IOException, SQLException {
        
        
         SceneController sc = new SceneController();
         if (event.getSource() == login){
             if (login()==true){
            
                JOptionPane.showMessageDialog(null , "Successfully Logged ");
               sc.switchToScene9(event); 
                 System.out.println(getsession()); 
                 
               
             } else if (Adminlogin()==true){
                 sc.switchToScene11(event); 
             } else {
                  JOptionPane.showMessageDialog(null , "Wrong Data  ");
             }
                 
             
       }
       if(event.getSource() == altSignUp ){
           
          
           sc.switchToScene1(event);
                
       }
      
     
    }
    
    public boolean login() throws SQLException 
            
    {   
                boolean resultat=false;  
      String email = tf1.getText();
        String password = tf2.getText();
        
         userServices us = new userServices();
          
        if(us.validate(email,password)==true){
           
           resultat=true;   
          
        } 
       
       return resultat; 
    } 
    public boolean Adminlogin() throws SQLException 
            
    {   
                boolean resultat=false;  
      String email = tf1.getText();
        String password = tf2.getText();
        
         userServices us = new userServices();
          
        if(us.validateAdmin(email,password)==true){
           
           resultat=true;   
          
        } 
       
       return resultat; 
    } 

public static  int getsession() throws SQLException{
         int x = 0 ;
         Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * FROM user WHERE email = ? and Mdp = ?;";
    
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setString(1,lab1.getText());
            ste.setString(2,lab2.getText() );
    
         ResultSet rs = ste.executeQuery();
         
         while(rs.next()){
             x = rs.getInt(1);
         }
   
       
     }
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
  return x;
    
   
     
}

   
   
   
}
            
    
