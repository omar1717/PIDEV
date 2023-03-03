/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class EditController implements Initializable {

   
    @FXML
    private JFXButton updatecurrent;
    @FXML
    private JFXTextField Uname;
    @FXML
    private JFXTextField usurname;
    @FXML
    private DatePicker udate;
    @FXML
    private JFXTextField umail;
    
    @FXML
    private JFXButton undou;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException, SQLException {
         SceneController sc = new SceneController();
    
        if(event.getSource() == updatecurrent ){
        if(UpdateCurrentUser()== true )  {
           
           sc.switchToScene2(event);
          
        }
        }
        
        if(event.getSource() == undou ){
       
             
           sc.switchToScene4(event);
        } 
           
             
           
    }
    
    
     public boolean UpdateCurrentUser() {
          userServices us = new userServices();
       boolean  result = false ;
           Connection myconn =MyConnection.getInstance().getConnexion();
         
        String sql="UPDATE user SET nom = ?, prenom = ?, age = ?,Email = ? WHERE id = ? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            
                 ste.setString(1,Uname.getText());
            ste.setString(2,usurname.getText());
            ste.setObject(3,udate.getValue());
             ste.setString(4,umail.getText());
              ste.setInt(5,LoginController.getsession());
              
                if (usurname.getText().isEmpty()||Uname.getText().isEmpty()||udate.getValue()==null||umail.getText().isEmpty()){
                JOptionPane.showMessageDialog(null , "PlEASE FILL DATA ");
                
                }else   if(! getmail().equalsIgnoreCase(umail.getText())&& us.validateEmail(umail.getText())== true ){
            JOptionPane.showMessageDialog(null , "EMAIL ALREADY EXISTS  ");
            } else  {
                    JOptionPane.showMessageDialog(null , "Your Account is Updated Successfully , Please Login  again  ");
                ste.executeUpdate();
              result = true;
                
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }


    public String getmail(){
         userServices us = new userServices();
         String email = null ;
       boolean  result = false ;
           Connection myconn =MyConnection.getInstance().getConnexion();
         
        String sql="select email from user  WHERE id = ? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setInt(1, LoginController.getsession());
             ResultSet rs = ste.executeQuery();
         while (rs.next())
         {   
          email= rs.getString(1);
        
         }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return email;
    }
}
