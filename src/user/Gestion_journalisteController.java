/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Utils.MyConnection;
import Entities.journaliste;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static user.Login_journalisteController.getsession;
import SERVICES.journaliste_serv;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Gestion_journalisteController implements Initializable {

    @FXML
    private TextField name_journ;
    @FXML
    private TextField lastname_journ;
    @FXML
    private TextField avert_journ;
    @FXML
    private TextField nbr_arti_journ;
    @FXML
    private Button ajout_image;
    @FXML
    private ImageView imageView;
    @FXML
    private Button supprimer_journ;
    @FXML
    private TextField mdp;
    @FXML
    private Label motdepasssse;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        journaliste_serv serviceJournaliste = new journaliste_serv();
try {
    journaliste journaliste = serviceJournaliste.getJournalisteFromSession();
    name_journ.setText(journaliste.getNom());
    lastname_journ.setText(journaliste.getPrenom());
    avert_journ.setText(Integer.toString(journaliste.getAvertissement()));
    nbr_arti_journ.setText(Integer.toString(journaliste.getNbr_article()));
} catch (SQLException e) {
    e.printStackTrace();
    
}

    }    
    

     /*    @FXML
     private void onUploadImage(ActionEvent event) {
     }*/

    @FXML
    private void handlebutton(ActionEvent event) {
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(ajout_image.getScene().getWindow());
        
        // Si un fichier a été sélectionné, l'envoyer au service pour le télécharger
        if (file != null) {
            journaliste_serv service = new journaliste_serv();
            boolean success = service.uploadImage(file);
            if (success) {
                // Afficher l'image dans l'imageview
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            } else {
                // Afficher un message d'erreur
                Alert alert = new Alert(AlertType.ERROR, "Erreur lors du téléchargement de l'image !");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onsuppclicked(ActionEvent event) throws SQLException, IOException {
            
          int idJournalisteConnecte = getsession();

    // Supprimer le compte du journaliste connecté
    Connection myconn = MyConnection.getInstance().getConnexion();
    String query = "DELETE FROM journaliste WHERE id_journaliste = ?;";

    try {
        PreparedStatement ste = myconn.prepareStatement(query);
        ste.setInt(1, idJournalisteConnecte);
        ste.executeUpdate();

        // Rediriger vers la page d'accueil après la suppression du compte
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mahmoud/login_journaliste.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) supprimer_journ.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }

    @FXML
    private void modifier_journaliste(ActionEvent event) throws SQLException {
           int id_journaliste = Login_journalisteController.getsession();
String nom = name_journ.getText();
String prenom = lastname_journ.getText();
String motdepasse = mdp.getText();
if (motdepasse.length() < 8) {
    // Afficher un message d'erreur si le mot de passe est trop court
    return;
}

boolean success = journaliste_serv.updateJournaliste(id_journaliste, nom, prenom, motdepasse);

if (success) {
    // Afficher un message de confirmation de la mise à jour
    System.out.println("Mise à jour réussie");
} else {
    // Afficher un message d'erreur en cas de problème lors de la mise à jour
    System.out.println("Erreur lors de la mise à jour");
}

}

    @FXML
    private void chngr(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("liste_article.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

}



    

