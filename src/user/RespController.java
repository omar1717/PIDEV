/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Entities.user;
import Utils.MyConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import services.userServices;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class RespController implements Initializable {

    @FXML
    private JFXTextField tfid;
    @FXML
    private JFXTextField tfnom;
    @FXML
    private JFXTextField tfprenom;
    @FXML
    private DatePicker tfdate;
    @FXML
    private JFXTextField tfemail;
    @FXML
    private JFXTextField tfmdp;
    @FXML
    private JFXTextField tfrole;
    @FXML
    private TableView<user> Atable;
    @FXML
    private TableColumn<user, Integer> Aid;
    @FXML
    private TableColumn<user, String> Anom;
    @FXML
    private TableColumn<user, String> Aprenom;
    @FXML
    private TableColumn<user, LocalDate> Adate;
    @FXML
    private TableColumn<user, String> Aemail;
    @FXML
    private TableColumn<user, String> Amdp;
    @FXML
    private TableColumn<user, String> Arole;
    @FXML
    private Button insert;
    @FXML
    private Button Adelete;
    @FXML
    private Button Aupdate;
    @FXML
    private JFXButton voting;
    @FXML
    private JFXButton dash;
    @FXML
    private JFXButton printpdf;
    @FXML
    private JFXButton returnadh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowUsers();
    }    
     public void Ainsert() throws SQLException {
         userServices us = new userServices();
            String  Name= tfnom.getText();
            String Surname= tfprenom.getText();
            LocalDate Age = tfdate.getValue();
            String Email= tfemail.getText();
            String Mdp= tfmdp.getText();
            
            if (Name.isEmpty()||Surname.isEmpty()||Age==null||Email.isEmpty()||Mdp.isEmpty()){
                JOptionPane.showMessageDialog(null , "PlEASE FILL DATA ");
            } else  if(us.validateEmail(Email)== true){
            JOptionPane.showMessageDialog(null , "EMAIL ALREADY EXISTS  ");
            } else {
                
                user p =new user(Name,Surname,Age,Email,Mdp,"aaa");
                us.ajouterPersonne(p);
            
           
        }
}

    @FXML
    private void OnBtnClick(ActionEvent event) throws IOException, FileNotFoundException, DocumentException, SQLException {
         if(event.getSource() == insert ){
           
          Ainsert();
         ShowUsers();
           
    }  if(event.getSource() == Aupdate ){
           
         UpdateRow();
          ShowUsers();
           
    }
    if(event.getSource() == Adelete ){
        RemoveRow();
         ShowUsers();
           
    }
    if(event.getSource() == voting ){
        
     SceneController sc = new SceneController();
           sc.switchToScene7(event);
    }
     if(event.getSource() == dash ){
        
     SceneController sc = new SceneController();
           sc.switchToScene10(event);
    }
      if(event.getSource() == printpdf ){
       printtopdf();
           
    }
       if(event.getSource() == returnadh ){
     SceneController sc = new SceneController();
           sc.switchToScene11(event);
           
    }
     
     
     
    }
    
    public void ShowUsers(){
        userServices us = new userServices();
        us.getuserList();
        
        Aid.setCellValueFactory(new PropertyValueFactory<user,Integer>("id"));
        Anom.setCellValueFactory(new PropertyValueFactory<user,String>("Nom"));
        Aprenom.setCellValueFactory(new PropertyValueFactory<user,String>("Prenom"));
        Aemail.setCellValueFactory(new PropertyValueFactory<user,String>("Email"));
        Adate.setCellValueFactory(new PropertyValueFactory<user,LocalDate>("Age"));
         Amdp.setCellValueFactory(new PropertyValueFactory<user,String>("mdp"));
          Arole.setCellValueFactory(new PropertyValueFactory<user,String>("Role"));
        
        Atable.setItems(us.getuserList());
    }
    

             

    public void RemoveRow()
        {Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            user userSelected;           
            userSelected=Atable.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(userSelected.getId());     
            //Remove From db
            
            PreparedStatement pst = null; 
            //Our Query
            String query="DELETE FROM user WHERE id = ?";
            try
            {          
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values           
            pst.setString(1,IdSelected);
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Deleted");
            }catch(Exception ex)
            {
            System.out.println("Delete impossible à effectuer.\nErreur :" + ex);
            }    
            //Refresh Table
           ShowUsers();
        }
    
     public void UpdateRow()
        {Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            user userSelected;           
            userSelected=Atable.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(userSelected.getId());     
            //Remove From db
            
            PreparedStatement pst = null; 
            //Our Query
            String query="UPDATE user SET nom = ? , prenom = ? , Age = ?,Email = ?,mdp = ? WHERE id = ?";
            try
            {          
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values           
             pst.setString(1, tfnom.getText());
            pst.setString(2, tfprenom.getText());
            pst.setObject(3, tfdate.getValue());
            pst.setString(4,   tfemail.getText());
            pst.setString(5,   tfmdp.getText());
            pst.setString(6,IdSelected);
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Updated");
            }catch(HeadlessException | SQLException ex)
            {
            System.out.println("Update impossible à effectuer.\nErreur :" + ex);
            }    
            //Refresh Table
           ShowUsers();
        }
     
     
     public void printtopdf() throws FileNotFoundException, DocumentException{
         userServices sc = new userServices();
         try{
         String fileName = "C:/Gen PDF/USERLIST.pdf";
         Document document = new Document();
  PdfWriter.getInstance(document, new FileOutputStream(fileName));
  
document.open();
for (user user : sc.getuserList()) {
    // Write the user information to the PDF document
    document.add(new Paragraph("Name: " + user.getNom()));
    document.add(new Paragraph("Email: " + user.getEmail()));
    document.add(new Paragraph("Role: " + user.getRole()));
     document.add(new Paragraph("Birt Date: " + user.getAge()));
    document.add(new Paragraph("------------------------------------------------------------------------------"));
}

document.close();

JOptionPane.showMessageDialog(null , "PDF LIST CREATED");
         } catch (DocumentException | FileNotFoundException e){
             System.out.println(e);
         }
          


     }
     
}
     

