/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import user.GestionUP21Controller;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Entities.Reservation;
import Utils.MyConnection;
import Entities.Produit;

/**
 * FXML Controller class
 *
 * @author MSI
 */
 
public class GSB21Controller implements Initializable {
      @FXML
    private ComboBox<String> EventCombo;
    @FXML
    private Button Reservezbutton;
    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TextField TFMail2;
   
    @FXML
    private TableColumn<Reservation, Date> Date_Res;
    @FXML
    private TableColumn<Reservation, Integer> Nbr_Place;
    @FXML
    private TableColumn<Reservation, String> Type_Res;
    @FXML
    private TableColumn<Reservation, Double> Prix_Res;
    @FXML
    private Button RetourverB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // Exécuter la requête SQL pour récupérer les valeurs de la colonne "Nom"
         Connection myconn =MyConnection.getInstance().getConnexion();
        String sql = "SELECT DISTINCT Nom_Event FROM event";
        PreparedStatement preparedStatement = null;
     try {
         preparedStatement = myconn.prepareStatement(sql);
     } catch (SQLException ex) {
         Logger.getLogger(GestionUP21Controller.class.getName()).log(Level.SEVERE, null, ex);
     }
        ResultSet resultSet = null;
     try {
         resultSet = preparedStatement.executeQuery();
     } catch (SQLException ex) {
         Logger.getLogger(GestionUP21Controller.class.getName()).log(Level.SEVERE, null, ex);
     }

        // Ajouter les valeurs récupérées dans le ComboBox
        ObservableList<String> Nom_Event = FXCollections.observableArrayList();
     try {
         while (resultSet.next()) {
             Nom_Event.add(resultSet.getString("Nom_Event"));
         }
     } catch (SQLException ex) {
         Logger.getLogger(GestionUP21Controller.class.getName()).log(Level.SEVERE, null, ex);
     }
        EventCombo.setItems(Nom_Event);
        
      
    Reservezbutton.setOnAction((ActionEvent event) -> {
 
   String cinStr = TFMail2.getText();


    // Récupérer la ligne sélectionnée dans la TableView "produitTable"
    Reservation reservation = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
    
    if (reservation != null) {
        try {
   // Insérer les informations de la commande dans la table "facture" et décrémenter la quantité de produit de 1
String sql2 = "INSERT INTO facture(Mail,Nom_Achat,Ref_Achete,PrixU_Achat) VALUES (?, ?, ?, ?)";
PreparedStatement preparedStatement2 = myconn.prepareStatement(sql2);
preparedStatement2.setString(1, TFMail2.getText()); // CIN = value from TFCIN
preparedStatement2.setString(2, reservation.getNom_Ev()); // Nom_Achat = Nom_Prod
preparedStatement2.setInt(3, reservation.getId_Res()); // Ref_Achete = Id_Prod
preparedStatement2.setDouble(4, reservation.getPrix_Res()); // PrixU_Achat = PrixU_Prod
preparedStatement2.executeUpdate();


// Décrémenter la quantité de produit de 1 dans la table "Produit"
String sql3 = "UPDATE reservation SET Nbr_Place = Nbr_Place - 1 WHERE Id_Res = ?";
PreparedStatement preparedStatement3 = myconn.prepareStatement(sql3);
preparedStatement3.setInt(1, reservation.getId_Res());
preparedStatement3.executeUpdate();

// Vérifier si la quantité de produit est égale à 0
String sql4 = "SELECT Nbr_Place FROM reservation WHERE  Id_Res= ?";
PreparedStatement preparedStatement4 = myconn.prepareStatement(sql4);
preparedStatement4.setInt(1, reservation.getId_Res());
ResultSet resultSet4 = preparedStatement4.executeQuery();
if(resultSet4.next()) {
    int quantite = resultSet4.getInt("Nbr_Place");
    if(quantite <= 0) {
        // Supprimer la ligne correspondante de la table "Produit"
        String sql5 = "DELETE FROM reservation WHERE Id_Res = ?";
        PreparedStatement preparedStatement5 = myconn.prepareStatement(sql5);
        preparedStatement5.setInt(1, reservation.getId_Res());
        preparedStatement5.executeUpdate();
    }
}
            // Afficher un message de confirmation à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("La commande a été ajoutée avec succès !");
            alert.showAndWait();

            // Actualiser la TableView "produitTable"
            EventCombo.getSelectionModel().getSelectedItem();
        } catch (SQLException e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de l'ajout de la commande.");
            alert.showAndWait();
        }
    }
});
           // Etablir la connexion avec la base de données
       Connection connection = MyConnection.getInstance().getConnexion();
        
        Date_Res.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("Date_Res"));
        Nbr_Place.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("Nbr_Place"));
        Type_Res.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Type_Res"));
         Prix_Res.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("Prix_Res"));

        // Ajouter un listener sur le ComboBox pour récupérer la catégorie sélectionnée
        EventCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    // Exécuter la requête SQL pour récupérer les produits correspondant à la catégorie sélectionnée
                    String sql2 = "SELECT * FROM Reservation WHERE Nom_Ev = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setString(1, newValue);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    // Ajouter les produits récupérés dans le TableView
                    ObservableList<Reservation> Reservations = FXCollections.observableArrayList();
                    while (resultSet2.next()) {
                        Reservation Reservation = new Reservation(
                             
                               resultSet2.getInt("Id_Res"),
                               resultSet2.getString("Nom_Ev"),
                               resultSet2.getDouble("Prix_Res"),
                               resultSet2.getDate("Date_Res"),
                               resultSet2.getInt("Nbr_Place"),
                               resultSet2.getString("Type_Res")
                        );
                        Reservations.add(Reservation);
                    }
                    reservationTable.setItems(Reservations);
                    
                

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Sélectionner la première catégorie par défaut
        EventCombo.getSelectionModel().selectFirst();
     
            

   
        // TODO
        
    }  
      @FXML
    public void retournerVersBoutique (ActionEvent e) throws IOException{
        SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene7(e);

}
    @FXML
    public void retourner (ActionEvent e) throws IOException{
    Stage stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("/affiche_facture/affichefacture.fxml"));

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    // Créer un bouton de retour ou de sortie
    Button retourButton = new Button("Retour");

    // Ajouter un gestionnaire d'événements pour le bouton
    retourButton.setOnAction(event -> {
        // Fermer la fenêtre actuelle
        stage.close();

        // Optionnellement, ouvrir une nouvelle fenêtre ou retourner à la fenêtre précédente
        // Stage stage2 = new Stage();
        // Parent root2 = FXMLLoader.load(getClass().getResource("/chemin/vers/la/fenetre/principale.fxml"));
        // Scene scene2 = new Scene(root2);
        // stage2.setScene(scene2);
        // stage2.show();
    });

    // Ajouter le bouton à la scène
  

    // Cacher la fenêtre actuelle lorsque le bouton est cliqué
    ((Node)e.getSource()).getScene().getWindow().hide();
}
    
    
}
