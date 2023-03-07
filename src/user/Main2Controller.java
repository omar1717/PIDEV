/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



import Utils.MyConnection;
import Entities.Produit;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Main2Controller implements Initializable {
     //  @FXML
    //private Label tfQuanti_Prod;
    @FXML
    private TextField tfId_Prod;
    @FXML
    private TextField tfNom_Prod;
    @FXML
    private TextField tfType_Prod;
    @FXML
    private TextField tfQunat_Prod;
    @FXML
    private TextField tfPrixU_Prod;
    @FXML
    private TextField tfCateg_Prod;
    @FXML
    private TableView<Produit> tvProduit;

    @FXML
    private TableColumn<Produit, Integer> colIdP;
    @FXML
    private TableColumn<Produit, String> colNomP;
    @FXML
    private TableColumn<Produit, String> colTypeP;
    @FXML
    private TableColumn<Produit, Integer> colQuantiteP;
    @FXML
    private TableColumn<Produit, Double> colPrixUP;
    @FXML
    private TableColumn<Produit, String> colCategorieP;
      @FXML
    private TableColumn<Produit, String> colNomEvent ;
    

    
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
 
    @FXML
    private Label tfQuant_Prod;
    @FXML
    private ComboBox<String> nomEventComboBox;
    @FXML
    private Button ConsulterM;
    @FXML
    private Button retourVIG;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // Récupérer une instance de connexion à la base de données
    Connection conn = MyConnection.getInstance().getConnexion();

    try {
        // Créer une requête SQL pour récupérer les noms des événements
        String query = "SELECT Nom_Event FROM event";

        // Exécuter la requête SQL en utilisant un objet Statement
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        // Ajouter chaque nom d'événement à la ComboBox
        while (rs.next()) {
        nomEventComboBox.getItems().add(rs.getString("Nom_Event"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Traiter les exceptions
    }
    
nomEventComboBox.setOnAction(event -> {
    try {
        // Vérifier si tous les champs sont remplis
        if (tfNom_Prod.getText().isEmpty() || tfType_Prod.getText().isEmpty() || tfQunat_Prod.getText().isEmpty() || tfPrixU_Prod.getText().isEmpty() || tfCateg_Prod.getText().isEmpty() || nomEventComboBox.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        
        int quantite;
        double prixUnitaire;
        try {
        quantite = Integer.parseInt(tfQunat_Prod.getText());
        prixUnitaire = Double.parseDouble(tfPrixU_Prod.getText());
        if(quantite <= 0 || prixUnitaire <= 0) {
        throw new NumberFormatException();
        }
        } catch (NumberFormatException ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La quantité et le prix unitaire doivent être des nombres positifs !");
        alert.showAndWait();
        return;
        }
        
        // Créer la requête SQL pour insérer le produit
        String query = "INSERT INTO produit (Nom_Prod, Type_Prod, Quantite_Prod, PrixU_Prod, Categorie_Prod, Nom_Event) VALUES (?, ?, ?, ?, ?, ?)";
         
        // Créer un objet PreparedStatement pour insérer le produit avec le nom d'événement sélectionné
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, tfNom_Prod.getText());
        pstmt.setString(2, tfType_Prod.getText());
        pstmt.setInt(3, quantite);
        pstmt.setDouble(4, prixUnitaire);
        pstmt.setString(5, tfCateg_Prod.getText());
        pstmt.setString(6, (String) nomEventComboBox.getValue());

        // Exécuter la requête SQL pour insérer le produit dans la table produit
        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Produit inséré avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été inséré avec succès !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur d'insertion du produit");
            alert.setHeaderText(null);
            alert.setContentText("L'insertion du produit a échoué.");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Traiter les exceptions
    }
    ShowProduits();
});

     
    /**
     * Initializes the controller class.
     */
   
    
        // TODO
           // set the initial date value of the DatePicker to today's date
      
        
        // add a listener to the DatePicker value property to print the selected date
       
 
        btnUpdate.setOnAction(e->{
            btnUpdate();
            viderChamps();
            ShowProduits();
        
        });
            
         btnDelete.setOnAction(e->{
             
             btnDelete();
             viderChamps();
             ShowProduits();
         
         });
      
	
   ShowProduits();
           
        try{
        Connection myconn =MyConnection.getInstance().getConnexion();
        }catch(Exception ex){
            System.out.println("erreur de connxion de base de donnée"+ex.getMessage());
              
        }
    }
    
       public void retourner (ActionEvent e) throws IOException{



        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("/MeuilleurVente/MeuilleurVente"));

        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

}
    
    public ObservableList<Produit> getProduitList() {
        
        ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
        Connection myconn =MyConnection.getInstance().getConnexion();
       
        try {
     

      // Créer un objet Statcement
      Statement myStmt = myconn.createStatement();

      // Exécuter la requête SQLS
      ResultSet myRs = myStmt.executeQuery("SELECT * FROM produit");
   
      // Afficher les informations de chaque enregistrement
      while (myRs.next()) {
          Produit Produits = new Produit(myRs.getInt("Id_Prod"),myRs.getString("Nom_Prod"),myRs.getString("Type_Prod"),myRs.getInt("Quantite_Prod"),myRs.getDouble("PrixU_Prod"),myRs.getString("categorie_Prod"),myRs.getString("Nom_Event"));
          ProduitList.add(Produits);
      }
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
        
   
        return ProduitList;
        
    }
    public void ShowProduits(){
        
         ObservableList<Produit> listp = getProduitList();
          colIdP.setCellValueFactory(new PropertyValueFactory<>("Id_Prod"));
              colNomP.setCellValueFactory(new PropertyValueFactory<>("Nom_Prod"));
                 colTypeP.setCellValueFactory(new PropertyValueFactory<>("Type_Prod"));
                        colQuantiteP.setCellValueFactory(new PropertyValueFactory<>("Quantite_Prod"));
                          colPrixUP.setCellValueFactory(new PropertyValueFactory<>("PrixU_Prod"));
                             colCategorieP.setCellValueFactory(new PropertyValueFactory<>("categorie_Prod"));
                             colNomEvent.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom_Event"));
                          
    tvProduit.setItems(listp);
    }

    
    private void btnUpdate() {
          try {
        // Récupérer une instance de connexion à la base de données
        Connection conn = MyConnection.getInstance().getConnexion();

        // Créer une requête SQL pour mettre à jour les informations du produit
        String query = "UPDATE produit SET Nom_Prod=?, Type_Prod=?, Quantite_Prod=?, PrixU_Prod=?, Categorie_Prod=? WHERE Id_Prod=?";

        // Préparer la requête SQL en utilisant un objet PreparedStatement
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, tfNom_Prod.getText());
        pstmt.setString(2, tfType_Prod.getText());
        pstmt.setInt(3, Integer.parseInt(tfQunat_Prod.getText()));
        pstmt.setDouble(4, Double.parseDouble(tfPrixU_Prod.getText()));
        pstmt.setString(5, tfCateg_Prod.getText());
        pstmt.setInt(6, Integer.parseInt(tfId_Prod.getText()));

        // Exécuter la requête SQL
        int rowsUpdated = pstmt.executeUpdate();

        if (rowsUpdated > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Produit mis à jour avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Le produit a été mis à jour avec succès !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de mise à jour du produit");
            alert.setHeaderText(null);
            alert.setContentText("La mise à jour du produit a échoué.");
            alert.showAndWait();
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        // Traiter les exceptions
    }


  
}



    private void viderChamps() {
        tfId_Prod.setText("");
        tfId_Prod.setText("");
        tfNom_Prod.setText("");
        tfType_Prod.setText("");
        tfQunat_Prod.setText("");
        tfPrixU_Prod.setText("");
        tfCateg_Prod.setText("");
        
   
    }
    private void btnDelete() {
    // Récupérer la ligne sélectionnée dans la tableview
    Produit selectedProduit = tvProduit.getSelectionModel().getSelectedItem();
    if (selectedProduit == null) {
        // Aucune ligne sélectionnée, afficher une alerte
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne à supprimer.");
        alert.showAndWait();
        return;
    }
    // Demander une confirmation avant de supprimer la ligne
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer la ligne sélectionnée?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            // Récupérer une instance de connexion à la base de données
            Connection conn = MyConnection.getInstance().getConnexion();
            // Créer une requête SQL pour supprimer la ligne sélectionnée
            String query = "DELETE FROM produit WHERE Id_Prod = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, selectedProduit.getId_Prod());
            // Exécuter la requête SQL pour supprimer la ligne sélectionnée
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                // Afficher une alerte pour indiquer que la ligne a été supprimée
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Suppression réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La ligne sélectionnée a été supprimée avec succès.");
                successAlert.showAndWait();
                // Rafraîchir la tableview
                ShowProduits();
            } else {
                // Afficher une alerte pour indiquer que la suppression a échoué
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Erreur de suppression");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("La suppression de la ligne a échoué.");
                errorAlert.showAndWait();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Traiter les exceptions
        }
    }
}


    @FXML
         public void retourner1 (ActionEvent e) throws IOException{



        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("/MeuilleurVente/MeuilleurVente.fxml"));

        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

}
 @FXML
      public void retourVersIAD (ActionEvent e) throws IOException{

SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene3(e);

        

}
    
}
      