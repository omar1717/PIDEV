package user;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Utils.MyConnection;
import Entities.Produit;
import static sun.management.Agent.error;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestionUP21Controller implements Initializable {
   
    @FXML
    private TableView<Produit> produitTable;
    private TableColumn<Produit, String> typeCol;
    private TableColumn<Produit, Integer> quantiteCol;
    private TableColumn<Produit, Double> prixUCol;
  @FXML
    private Button validerButton;
  @FXML
    private TextField TFMail ;
  
@FXML
private Button Consulter;
    @FXML
    private ComboBox<String> EventCombo;
    @FXML
    private TableColumn<Produit,String> nomProdCol;
    @FXML
    private TableColumn<Produit, String> typeProdCol;
    @FXML
    private TableColumn<Produit, Double> prixUProdCol;
    @FXML
    private Button RetourB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // In the current controller class

    boolean valid = true;    
// Ajouter un EventHandler pour le bouton "valider"
   Connection myconn =MyConnection.getInstance().getConnexion();
        validerButton.setOnAction((ActionEvent event) -> {

 
   String cinStr = TFMail.getText();
   
   //api pour verifier ladresse mail existe ou non
           


    // Récupérer la ligne sélectionnée dans la TableView "produitTable"
    Produit produit = produitTable.getSelectionModel().getSelectedItem();
    
    if (produit != null) {
        try {
   // Insérer les informations de la commande dans la table "facture" et décrémenter la quantité de produit de 1
String sql2 = "INSERT INTO facture  e(Mail,Nom_Achat,Ref_Achete,PrixU_Achat) VALUES (?, ?, ?, ?)";
PreparedStatement preparedStatement2 = myconn.prepareStatement(sql2);
preparedStatement2.setString(1, TFMail.getText()); // CIN = value from TFCIN
preparedStatement2.setString(2, produit.getNom_Prod()); // Nom_Achat = Nom_Prod
preparedStatement2.setInt(3, produit.getId_Prod()); // Ref_Achete = Id_Prod
preparedStatement2.setDouble(4, produit.getPrixU_Prod()); // PrixU_Achat = PrixU_Prod
preparedStatement2.executeUpdate();


// Décrémenter la quantité de produit de 1 dans la table "Produit"
String sql3 = "UPDATE produit SET Quantite_Prod = Quantite_Prod - 1 WHERE Id_Prod = ?";
PreparedStatement preparedStatement3 = myconn.prepareStatement(sql3);
preparedStatement3.setInt(1, produit.getId_Prod());
preparedStatement3.executeUpdate();

// Vérifier si la quantité de produit est égale à 0
String sql4 = "SELECT Quantite_Prod FROM Produit WHERE Id_Prod = ?";
PreparedStatement preparedStatement4 = myconn.prepareStatement(sql4);
preparedStatement4.setInt(1, produit.getId_Prod());
ResultSet resultSet4 = preparedStatement4.executeQuery();
if(resultSet4.next()) {
    int quantite = resultSet4.getInt("Quantite_Prod");
    if(quantite <= 0) {
        // Supprimer la ligne correspondante de la table "Produit"
        String sql5 = "DELETE FROM Produit WHERE Id_Prod = ?";
        PreparedStatement preparedStatement5 = myconn.prepareStatement(sql5);
        preparedStatement5.setInt(1, produit.getId_Prod());
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

        // Exécuter la requête SQL pour récupérer les valeurs de la colonne "Nom"
        String sql = "SELECT DISTINCT Nom_Event FROM Produit";
        PreparedStatement preparedStatement = null;
     try {
         preparedStatement = connection.prepareStatement(sql);
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

        // Définir les cellules de chaque colonne du TableView
      
        nomProdCol.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom_Prod"));
        typeProdCol.setCellValueFactory(new PropertyValueFactory<Produit, String>("Type_Prod"));
        prixUProdCol.setCellValueFactory(new PropertyValueFactory<Produit, Double>("PrixU_Prod"));

        // Ajouter un listener sur le ComboBox pour récupérer la catégorie sélectionnée
        EventCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    // Exécuter la requête SQL pour récupérer les produits correspondant à la catégorie sélectionnée
                    String sql2 = "SELECT * FROM Produit WHERE Nom_Event = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setString(1, newValue);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    // Ajouter les produits récupérés dans le TableView
                    ObservableList<Produit> produits = FXCollections.observableArrayList();
                    while (resultSet2.next()) {
                        Produit produit = new Produit(
                             
                               resultSet2.getInt("Id_Prod"),
                                resultSet2.getString("Nom_Prod"),
                                resultSet2.getString("Type_Prod"),
                                resultSet2.getInt("Quantite_Prod"),
                                resultSet2.getDouble("PrixU_Prod"),
                                resultSet2.getString("Categorie_Prod"),
                              resultSet2.getString("Nom_Event")
                        );
                        produits.add(produit);
                    }
                    produitTable.setItems(produits);
                    
                

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
    public void retourner (ActionEvent e) throws IOException{



        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("/affiche_facture/affichefacture.fxml"));

        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

}
    @FXML
    public void retournerVersBoutique (ActionEvent e) throws IOException{
       SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene7(e);

}
    public void switchToScene1(ActionEvent event) throws IOException {
     Object root = FXMLLoader.load(getClass().getResource("../affiche_facture/affichefacture.fxml"));
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     Scene scene = new Scene((Parent) root);
  stage.setScene(scene);
  stage.show();
 }
    private void OnBtnClick(ActionEvent event) throws IOException {
        if(event.getSource()== Consulter){
            switchToScene1(event);
        }
    }
}
