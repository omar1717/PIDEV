 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import SERVICES.journaliste_serv;
import SERVICES.userServices;
import Utils.MyConnection;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static user.LoginController.getsession;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Login_journalisteController implements Initializable {

    @FXML
    private TextField mail_login;
    @FXML
    private PasswordField mdp_login;
    @FXML
    private Button login;
    @FXML
    private Button gotosignup;

     public static  TextField logins;
     public static  PasswordField loginss;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Login_journalisteController.logins=mail_login;
       Login_journalisteController.loginss=mdp_login;
    }    

    @FXML
    private void gotosignup(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("sign_up_journaliste.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    public boolean Adminlogin() throws SQLException 
            
    {   
                boolean resultat=false;  
      String email = mail_login.getText();
        String password = mdp_login.getText();
        
         journaliste_serv us = new journaliste_serv();
          
        if(us.validateAdmin(email,password)==true){
           
           resultat=true;   
          
        } 
       
       return resultat; 
    } 
    public boolean login() throws SQLException 
            
    {   
                boolean resultat=false;  
      String email = mail_login.getText();
        String password = mail_login.getText();
        
         journaliste_serv us = new journaliste_serv();
          
        if(us.validate(email,password)==true){
           
           resultat=true;   
          
        } 
       
       return resultat; 
    } 
    
    
    
    @FXML
    private void onlogcl(ActionEvent event) throws SQLException, IOException {
        SceneController sc = new SceneController();
         if (event.getSource() == login){
             if (login()==true){
            
                JOptionPane.showMessageDialog(null , "Successfully Logged ");
               sc.switchToScene16(event); 
                 System.out.println(getsession()); 
                 
               
             } else if (Adminlogin()==true){
                 sc.switchToScene11(event); 
             } else {
                  JOptionPane.showMessageDialog(null , "Wrong Data  ");
             }
                 
             
       }
       if(event.getSource() == gotosignup ){
           
          
           sc.switchToScene1(event);
           
       }

    }
    
    
    
    public static  int getsession() throws SQLException{
         int x = 0 ;
         Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * FROM journaliste WHERE mail = ? and motdepasse = ?;";


     try{

         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setString(1,logins.getText());
            ste.setString(2,loginss.getText() );

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
