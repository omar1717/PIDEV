/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import SERVICES.artistServ;
import com.jfoenix.controls.JFXButton;
import com.mysql.cj.jdbc.Blob;
import Entities.artist;
import Utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author yessine
 */
public class RenderArtistController implements Initializable {

    
        private artistServ arts;
        private artist currentArt;
       // private ImageView img= new ImageView();
    @FXML
    private Label nomArtist;
    @FXML
    private Label prenomArtist;
    @FXML
    private Label genre;
    
    
    @FXML
    private Label detailArt;
    @FXML
    private JFXButton prev;
    @FXML
    private JFXButton next;
Connection mycon = MyConnection.getInstance().getConnexion();
    @FXML
    private ImageView image= new ImageView();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                // TODO

                displayCurrentArtist(9);
                
            } catch (SQLException ex) {
                Logger.getLogger(RenderArtistController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }  
    
     int idd =9;
@FXML
    private void HandleButtonAction(ActionEvent event) throws SQLException {
           
           
               
               if (event.getSource()== next ){
                
                    displayCurrentArtist(idd++);
                    ;
                    };
            if (event.getSource()== prev){
                 
                if(idd>=9){
                    displayprevtArtist(idd--);
                   
                } 
            }
    }
    
    
    private void displayCurrentArtist(int id) throws SQLException{
      try (
             PreparedStatement statement = mycon.prepareStatement("SELECT * FROM artist WHERE idA = ?")){
         
                    statement.setInt(1, id);
                        try(ResultSet resultSet = statement.executeQuery()){
                            if(resultSet.next()){
                                
                                nomArtist.setText(resultSet.getString("nomA"));
                                prenomArtist.setText(resultSet.getString("prenom_ar"));
                                detailArt.setText(resultSet.getString("detail_Ar"));
                                genre.setText(resultSet.getString("genre_Ar"));
                                java.sql.Blob blob= resultSet.getBlob("photo_Ar");
                                if (blob!=null)
                                image.setImage(new Image(blob.getBinaryStream()));
        
    }
    
    
                        } 
      }
    }
    
      private void displayprevtArtist(int id) throws SQLException{
      try (
             PreparedStatement statement = mycon.prepareStatement("SELECT * FROM artist WHERE idA = ?")){
         
                    statement.setInt(1, id);
                        try(ResultSet resultSet = statement.executeQuery()){
                            if(resultSet.next()){
                                
                                nomArtist.setText(resultSet.getString("nomA"));
                                prenomArtist.setText(resultSet.getString("prenom_ar"));
                                detailArt.setText(resultSet.getString("detail_Ar"));
                                genre.setText(resultSet.getString("genre_Ar"));
                                java.sql.Blob blob= resultSet.getBlob("photo_Ar");
                                if (blob!=null)
                                image.setImage(new Image(blob.getBinaryStream()));
    }
    
    
                        } 
      }
    }
    
   
}
