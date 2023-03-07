/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import SERVICES.artistServ;
import com.itextpdf.text.Image;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import Entities.artist;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
import Utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author yessine
 */
public class ArtistAdmController implements Initializable {

    @FXML
    private TableColumn<artist,Integer> idART;
    @FXML
    private TableColumn<artist,String> nomART;
    @FXML
    private TableColumn<artist,String> prenomART;
    @FXML
    private TableColumn<artist,Date> dateART;
    @FXML
    private TableColumn<artist,String> detailART;
    @FXML
    private JFXTextField nomA;
    @FXML
    private JFXTextField prenomA;
    @FXML
    private JFXDatePicker dateA;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXButton addA;
    @FXML
    private JFXButton deleteA;
    @FXML
    private JFXButton updateA;
    @FXML
    private TableView<artist> Atable;
Connection mycon = MyConnection.getInstance().getConnexion();
    @FXML
    private TableColumn<artist, String> genre;
    @FXML
    private JFXTextField genreAR;
    @FXML
    private ImageView ImageLabel;
    @FXML
    private JFXButton chooseImageBtn;
    artist ar = new artist();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RenderArtist();
    }    

  @FXML
private void HandleButtonAction(ActionEvent event) throws SQLException {
    if (event.getSource() == addA) {
        ajoutART();
        RenderArtist();
    } else if (event.getSource() == updateA) {
        modifyAR();
        RenderArtist();
    } else if (event.getSource() == deleteA) {
        RemoveEQ();
        RenderArtist();
    } else if (event.getSource() == chooseImageBtn) {
        chooseImage();
    }
}



    
    
    
    
     public ObservableList<artist> getartisttList()
    {
        ObservableList<artist> artistlist = FXCollections.observableArrayList();
       
        String query = "SELECT * FROM artist";
        Statement st;
        ResultSet rs;
        try 
        {
          st = mycon.createStatement();
          rs = st.executeQuery(query);
          artist eq;
          while (rs.next())
          {
              Date dateA = rs.getDate("date_NesAr");
              eq = new artist( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(4));
               artistlist.add(eq);
          }         
        }catch(SQLException ex){
        }
        return artistlist;
    }
    
    
      /////////////////////////////////LIST TO TABLE//////////////////////////////////////////
    public void RenderArtist()
    {
        ObservableList<artist> artistlist = getartisttList();
        
       
        idART.setCellValueFactory(new PropertyValueFactory<>("id_Ar"));
        nomART.setCellValueFactory(new PropertyValueFactory<>("nom_Ar"));
        prenomART.setCellValueFactory(new PropertyValueFactory<>("prenom_Ar"));
        dateART.setCellValueFactory(new PropertyValueFactory<>("dateStr"));
        detailART.setCellValueFactory(new PropertyValueFactory<>("detail_Ar"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre_Ar"));
        Atable.setItems(artistlist);
        
    }
    
 

private void chooseImage() throws SQLException {
    FileChooser fileChooser = new FileChooser();
    // set the title and filters for the file chooser
    fileChooser.setTitle("Choose Image File");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
    // show the file chooser dialog and get the selected file
    File selectedFile = fileChooser.showOpenDialog(addA.getScene().getWindow());
    if (selectedFile != null) {
        try {
            // read the selected file into a byte array
            FileInputStream fis = new FileInputStream(selectedFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] imageBytes = bos.toByteArray();
            fis.close();
            // display the selected image in the imageLabel
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            ImageLabel.setImage(image);
            // create an instance of the artist class and set the image bytes
            
            ar.setImageBytes(imageBytes);
            // set the image bytes in the artist object
            artistServ EE = new artistServ();
            EE.ajouterArtist(ar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public void ajoutART() throws SQLException {
    //byte[] imageBytes = artist.setImageBytes();
    
    //Blob photo = new SerialBlob(ar.);
    artist ar = new artist(nomA.getText(), prenomA.getText(), dateA.getValue(), desc.getText(), genreAR.getText(), this.ar.getPhoto_Ar());
    artistServ EE = new artistServ();
    EE.ajouterArtist(ar);
}

public void modifyAR() throws SQLException {
    if (nomA.getText()==null || prenomA.getText()==null || nomA.getText().isEmpty() || prenomA.getText().isEmpty()) {
        System.out.println("artist should not be empty while modifying");
        return;
    }

    artist artistSelected = Atable.getSelectionModel().getSelectedItem();
    if (artistSelected == null) {
        System.out.println("Please select an artist to update");
        return;
    }
    String idSelected = String.valueOf(artistSelected.getId_Ar());
    //byte[] imageBytes = artist.getImageBytes();
   // Blob photo = new SerialBlob(imageBytes);
    artist ar = new artist(nomA.getText(), prenomA.getText(), dateA.getValue(), desc.getText(), genreAR.getText(), this.ar.getPhoto_Ar());
    artistServ EE = new artistServ();
    EE.modifierArtist(ar, idSelected);
}

    
    
     public void RemoveEQ(){
            artist artistSelected;
        artistSelected= Atable.getSelectionModel().getSelectedItem();
        //Convert to String
        String idSelected= String.valueOf(artistSelected.getId_Ar());
        //call for remove meth
         artistServ EE = new artistServ();
        EE.supprimerArtist(idSelected);
        JOptionPane.showMessageDialog(null , "deleted with success");
        
        
    }

    
    
}
