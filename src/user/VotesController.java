/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Entities.Votes;
import Entities.user;
import Utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import services.Voteservices;
import services.userServices;
import static user.LoginController.lab1;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class VotesController implements Initializable {

    @FXML
    private JFXButton vote;
    @FXML
    private Rating votea;
    @FXML
    private Rating votee;
    @FXML
    private JFXButton returnfromvote;
    @FXML
    private JFXComboBox selectA;
    @FXML
    private JFXComboBox<?> selectE;
    @FXML
    private JFXButton savevote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showArtists();
        } catch (SQLException ex) {
            Logger.getLogger(VotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void OnButtonClick(ActionEvent event) throws IOException, SQLException {
        SceneController sc = new SceneController();
      if(event.getSource() == vote ){
           insertVote();
           sc.switchToScene4(event);
    }
        if(event.getSource() == returnfromvote   ){
           
           sc.switchToScene4(event);
           
    }
            if(event.getSource() == savevote   ){
           
                System.out.println(getselectedartist());
                UpdateVote();
                 sc.switchToScene4(event);
    }
    }
    public void insertVote() throws SQLException {
        
        
            
           double VoteA=votea.getRating();
           double voteE=votee.getRating();
           
         
            
                Voteservices vs = new Voteservices();
                Votes v = new Votes(VoteA,voteE,LoginController.getsession(),getselectedartist());
             vs.ajouterVote(v);
               
            
           
        }
    
    public int getselectedartist(){
         Voteservices vs = new Voteservices();
          String artist = selectA.getSelectionModel().getSelectedItem().toString();
        
         return vs.getArtist(artist);
        
    }
    
    
    
    
    
    
    
    public void showArtists() throws SQLException{
          Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * FROM artist ";
      ObservableList data = FXCollections.observableArrayList();
      
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            
            
     
         ResultSet rs = ste.executeQuery();
         while (rs.next()){
             data.add(rs.getString(2) );
             
         }
         
          selectA.setItems(data);
         }
    }catch (SQLException ex) {
            System.out.println(ex);
            
        }
}
    
    
    public void UpdateVote(){
        
         Connection myconn =MyConnection.getInstance().getConnexion();
        PreparedStatement pst = null; 
        //Our Query
        String query="UPDATE votes SET voteA = ? , voteE = ? , idA =?  WHERE idC = ?";
        try
        {          
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values
             
             
            
            pst.setDouble(1, votea.getRating());
            pst.setDouble(2, votee.getRating());
            
            pst.setInt(3,getselectedartist());
             pst.setInt(4, LoginController.getsession());
        
            
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Updated");
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("Update impossible à effectuer.\nErreur :" + ex);
        }    
    }
    
    

    }