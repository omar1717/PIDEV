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
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import services.Voteservices;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class AvotesController implements Initializable {

    @FXML
    private JFXTextField AVid;
    @FXML
    private Rating Aavote;
    @FXML
    private Rating Aevote;
    @FXML
    private JFXTextField clientid;
    @FXML
    private Button Avote;
    @FXML
    private Button Adelvote;
    @FXML
    private Button Auvote;
    @FXML
    private TableView<Votes> Vtable;
    @FXML
    private TableColumn<Votes, Integer> Avtid;
    @FXML
    private TableColumn<Votes, Double> Aav;
    @FXML
    private TableColumn<Votes, Double> Aev;
    @FXML
    private TableColumn<Votes, Integer> tclientid;
    @FXML
    private Button refresh;
    @FXML
    private JFXButton manageusers;
    @FXML
    private JFXComboBox<?> selectA1;
    @FXML
    private JFXComboBox<?> selectA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ShowVotes();
        try {
            showArtistsA();
        } catch (SQLException ex) {
            Logger.getLogger(AvotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void OnBtnClick(ActionEvent event) throws IOException {
        if (event.getSource() == Avote){
                insertAVote();
                ShowVotes();
          
       }
        if (event.getSource()== Adelvote){
            RemoveVRow();
        }
         if (event.getSource()== Auvote){
           UpdateVRow() ;
           ShowVotes();
          
        }
          if (event.getSource()== refresh){
           
           ShowVotes();
          
        }
            if (event.getSource()== manageusers){
           
           SceneController sc = new SceneController();
           sc.switchToScene8(event);
          
        }
    }
    
     public void insertAVote() {
        
        
        double VoteA=Aavote.getRating();
           double voteE=Aevote.getRating();
           String email = clientid.getText();
         
            
                Voteservices vs = new Voteservices();
                Votes v = new Votes(VoteA,voteE, vs.getclient(email),getselectedartistA());
             vs.ajouterVote(v);
               
            
           
        }
     public void ShowVotes(){
        Voteservices vs = new Voteservices();
        vs.getvoteList();
        
       
        Aav.setCellValueFactory(new PropertyValueFactory<>("voteA"));
        Aev.setCellValueFactory(new PropertyValueFactory<>("voteE"));
        tclientid.setCellValueFactory(new PropertyValueFactory<>("idC"));
       
        
        Vtable.setItems(vs.getvoteList());
    }
     
     
    public void RemoveVRow()
        {
            Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            Votes voteSelected;           
            voteSelected=Vtable.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(voteSelected.getId());     
            //Remove From db
            
            PreparedStatement pst = null; 
            //Our Query
            String query="delete FROM votes WHERE id= ?";
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
           ShowVotes();
        }
    

     public int getselectedartistA(){
         Voteservices vs = new Voteservices();
          String artist = selectA1.getSelectionModel().getSelectedItem().toString();
        
         return vs.getArtist(artist);
        
    }
        public void showArtistsA() throws SQLException{
          Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select * FROM artist ";
      ObservableList data = FXCollections.observableArrayList();
      
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            
            
     
         ResultSet rs = ste.executeQuery();
         while (rs.next()){
             data.add(rs.getString(2) );
             
         }
         
          selectA1.setItems(data);
         }
    }catch (SQLException ex) {
            System.out.println(ex);
            
        }
}
    
        public void UpdateVRow()
        {
            Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            Votes voteSelected;           
            voteSelected=Vtable.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(voteSelected.getId());     
            //Remove From db
            
            PreparedStatement pst = null; 
            //Our Query
            String query="UPDATE votes SET voteA = ? , voteE = ? , idA = ? WHERE id = ?";
            try
            {          
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values         
             pst.setDouble(1, Aavote.getRating());
            pst.setDouble(2, Aevote.getRating());
             pst.setInt(3,getselectedartistA());
            pst.setString(4,IdSelected);
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Updated");
            }catch(Exception ex)
            {
            System.out.println("Update impossible à effectuer.\nErreur :" + ex);
            }    
            //Refresh Table
           ShowVotes();
        }
    
        
        
        
        
        
        
}
