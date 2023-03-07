/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Entities.journaliste;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import SERVICES.journaliste_serv;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Sign_up_journalisteController implements Initializable {

    @FXML
    private TextField nom_su;
    @FXML
    private TextField Email_su;
    @FXML
    private TextField prenom_su;
    @FXML
    private TextField mdp_su;
    @FXML
    private Button add_su;
    @FXML
    private Button gotologin;
    @FXML
    private Button back;

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 


    @FXML
    private void onbtncl(ActionEvent event) {
        String nom = nom_su.getText();
    String prenom = prenom_su.getText();
    String email = Email_su.getText();
    String mdp = mdp_su.getText();
    
    // Vérification des saisies
    if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Certains champs sont vides");
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    if(mdp.length() < 8) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Le mot de passe doit contenir au moins 8 caractères");
        alert.setContentText("Veuillez saisir un mot de passe composé d'au moins 8 caractères.");
        alert.showAndWait();
        return;
    }
    
    if(!mdp.matches(".*[A-Z].*")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText("Le mot de passe doit contenir au moins une majuscule");
        alert.setContentText("Veuillez saisir un mot de passe comportant au moins une majuscule.");
        alert.showAndWait();
        return;
    }
    
    journaliste j = new journaliste();
    j.setNom(nom);
    j.setPrenom(prenom);
    j.setMail(email);
    j.setMdp(mdp);
    journaliste_serv js = new journaliste_serv();
    try {
        // Vérification de l'existence du mail dans la base de données
        if(js.checkEmailExist(email)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Le mail existe déjà");
            alert.setContentText("Le mail " + email + " existe déjà dans la base de données.");
            alert.showAndWait();
            return;
        }
        
        js.ajouterJournaliste(j);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Journaliste ajouté avec succès");
        alert.setContentText("Le journaliste a été ajouté à la base de données avec succès.");
        alert.showAndWait();
        // Réinitialisation des champs de saisie
        nom_su.setText("");
        prenom_su.setText("");
        Email_su.setText("");
        mdp_su.setText("");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du journaliste à la base de données: " + ex.getMessage());
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de l'ajout du journaliste");
        alert.setContentText("Une erreur s'est produite lors de l'ajout du journaliste à la base de données.");
        alert.showAndWait();
    }
    } 

    @FXML
    private void gotologin(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login_journaliste.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToScene23(event);
        
    }

    

   

   
}
