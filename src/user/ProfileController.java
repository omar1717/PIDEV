/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import Entities.user;
import Utils.MyConnection;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.controlsfx.control.Rating;
import SERVICES.userServices;



/**
 *
 * @author omar
 */
public class ProfileController implements Initializable {

    @FXML
    private JFXButton profile;
    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton password;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton Logout;
    @FXML
    private StackPane SP;
   
    @FXML
    private JFXButton activity;
    private TableView<user> table;
    private TableColumn<user, String> nameC;
    private TableColumn<user, String> surnameC;
    private TableColumn<user, String> EmailC;
    private TableColumn<user, String> AgeC;
    private JFXButton view;
    

    @FXML
    private Label namelabel;
   
    @FXML
    private Label maillabel;
    
    @FXML
    private Label surnamelabel;
   
    @FXML
    private Label agelabel;
    @FXML
    private Rating rat1;
    @FXML
    private Rating rat2;
    @FXML
    private JFXButton returnhome;
    @FXML
    private ImageView pdp;
    @FXML
    private Label lartist;
    @FXML
    private Label levent;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            displayuser();
            displayuservoteAr();
            displayuservoteEv();
          displayQr();
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      
    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException, SQLException {
     SceneController sc = new SceneController();
          if(event.getSource() == edit ){
           
          
           sc.switchToScene3(event);
    }
            if(event.getSource() == password ){
           
           
           sc.switchToScene5(event);
    }
            if(event.getSource() == Logout ){
           
           
           sc.switchToScene2(event);
    }
             if(event.getSource() == delete ){
           
           sc.switchToScene1(event);
           userServices us = new userServices();
           
           
           us.supprimerUser();
            
              JOptionPane.showMessageDialog(null , "YOUR ACCOUNT HAS BEEN DELETED  ");
          
    }
              if(event.getSource() == activity ){
           
           sc.switchToScene6(event);
    }
               if(event.getSource() == view ){
           
          userServices us = new userServices();
                   
    } 
               if(event.getSource() == profile ){
                displayuser();
                displayuservoteAr();
                displayuservoteEv();
          
                   
    }
                      if(event.getSource() == returnhome ){
              sc.switchToScene9(event);
          
                   
    }
            
    }
    
    
    
    
    
public void displayuser() throws SQLException{
     Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * FROM user WHERE id = ?";
    LoginController lc = new LoginController();
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setInt(1,lc.getsession());
            
     
         ResultSet rs = ste.executeQuery();
         while (rs.next())
         {   namelabel.setText(rs.getString(2));
         surnamelabel.setText(rs.getString(3));
         maillabel.setText(rs.getString(5));
         agelabel.setText(rs.getString(4));
         }
        
         } 
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
    
}
public void displayuservoteAr() throws SQLException{
     Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select voteA, nomA from votes inner join artist WHERE  votes.idA = artist.idA  AND votes.idC = ?";
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setInt(1,LoginController.getsession());
            
     
         ResultSet rs = ste.executeQuery();
         while (rs.next())
         {   
             rat1.setRating(rs.getDouble(1));
     
         lartist.setText(rs.getString(2));
        
         }
        
         } 
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
    
}
public void displayuservoteEv() throws SQLException{
     Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select voteE, nom_event from votes inner join event WHERE votes.idE = event.nom_event AND votes.idC = ?;";
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setInt(1,LoginController.getsession());
            
     
         ResultSet rs = ste.executeQuery();
         while (rs.next())
         {   
            
         rat2.setRating(rs.getDouble(1));
         levent.setText(rs.getString(2));
        
         }
        
         } 
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
    
}




public void displayQr() throws WriterException{
   Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * from user Where id = ?";
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setInt(1,LoginController.getsession());
             ResultSet rs = ste.executeQuery();
            
        
         while (rs.next())
         {   
           String myText = ("this is " + rs.getString(2));
QRCodeWriter qrCodeWriter = new QRCodeWriter();
ByteMatrix bitMatrix = qrCodeWriter.encode(myText, BarcodeFormat.QR_CODE, 200, 200);
WritableImage qrCodeImage = SwingFXUtils.toFXImage(MatrixToImageWriter.toBufferedImage(bitMatrix), null);
ImageView imageView = new ImageView(qrCodeImage);
pdp.setImage(qrCodeImage);
     
        
         }
        
         } 
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
}
         
 
 
 
 
 
 
 
 
 
 
 
 
         }
     
   


   


