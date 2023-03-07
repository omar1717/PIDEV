package user;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Entities.Reservation;
import Utils.MyConnection;



public class GestionBilletController implements Initializable {
    
   
    @FXML
    private Button btnInsert;
    
     @FXML
    private ComboBox<String> nomEventComboBox;
    @FXML
    private DatePicker dateResDatePicker;
    @FXML
    private TextField nbrPlaceTextField;
    @FXML
    private TextField typeResTextField;
    @FXML
    private TextField prixResTextField;

    
    @FXML
private TableView<Reservation> reservationTableView;
    @FXML
    private TableColumn<Reservation, Integer> IdRes;
    @FXML
    private TableColumn<Reservation, String> NomEV;
    @FXML
    private TableColumn<Reservation, Double> PrixRes;
    @FXML
    private TableColumn<Reservation, Date> DateRes;
    @FXML
    private TableColumn<Reservation, Integer> NbrPlace;
    @FXML
    private TableColumn<Reservation, String> TypeRes;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfId_Res;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // Récupérer les noms d'événements à partir de la base de données et les ajouter à la ComboBox
          
        Connection conn = MyConnection.getInstance().getConnexion();
        
        ObservableList<String> nomEventList = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT Nom_Event FROM event");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomEv = rs.getString("Nom_Event");
                nomEventList.add(nomEv);
            }
        } catch (SQLException e) {
            // Afficher un message d'erreur en cas de problème avec la base de données
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la récupération des événements : " + e.getMessage());
            alert.showAndWait();
        }
        nomEventComboBox.setItems(nomEventList);
         

         btnInsert.setOnAction(e->{ 
             try { 
                 insertReservation();
             } catch (SQLException ex) {
                 Logger.getLogger(GestionBilletController.class.getName()).log(Level.SEVERE, null, ex);
             }
});
          btnDelete.setOnAction(e->{
              
             btnDelete();
             ShowProduits();
         
         });
           btnUpdate.setOnAction(e->{
            btnUpdate();
          
            ShowProduits();
        
        });
            
  ShowProduits();
    }
public void insertReservation() throws SQLException {

    Double prixRes = Double.parseDouble(prixResTextField.getText());
    LocalDate dateRes = dateResDatePicker.getValue();
    Integer nbrPlace = Integer.parseInt(nbrPlaceTextField.getText());
    String typeRes = typeResTextField.getText();
    String nomEvent = nomEventComboBox.getSelectionModel().getSelectedItem();

    // Vérifier si la capacité de l'événement est suffisante
    Connection conn = MyConnection.getInstance().getConnexion();
    PreparedStatement checkCapacityStmt = conn.prepareStatement(
            "SELECT nbr_participants, date_deb, date_fin FROM event WHERE nom_event = ?");
    checkCapacityStmt.setString(1, nomEvent);
    ResultSet rs = checkCapacityStmt.executeQuery();
    if (rs.next()) {
        int capaciteMax = rs.getInt(1);
        LocalDate dateDeb = rs.getDate(2).toLocalDate();
        LocalDate dateFin = rs.getDate(3).toLocalDate();
        if (nbrPlace > capaciteMax) {
            // Si le nombre de places demandées est supérieur à la capacité maximale de l'événement
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de places demandées est supérieur à la capacité maximale de l'événement.");
            alert.showAndWait();
            return;
        } else if (dateRes.isBefore(dateDeb) || dateRes.isAfter(dateFin)) {
            // Si la date de réservation est en dehors de la plage de dates de l'événement
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de réservation est en dehors de la plage de dates de l'événement.");
            alert.showAndWait();
            return;
        }
    } else {
        // Si l'événement n'existe pas dans la base de données
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'événement sélectionné n'existe pas.");
        alert.showAndWait();
        return;
    }

    // Insérer la nouvelle réservation dans la base de données
    PreparedStatement insertStmt = conn.prepareStatement(
            "INSERT INTO Reservation (Nom_Ev, Prix_Res, Date_Res, Nbr_Place, Type_Res) " +
                    "VALUES (?, ?, ?, ?, ?)");
    insertStmt.setString(1, nomEvent);
    insertStmt.setDouble(2, prixRes);
    insertStmt.setDate(3, Date.valueOf(dateRes));
    insertStmt.setInt(4, nbrPlace);
    insertStmt.setString(5, typeRes);
    insertStmt.executeUpdate();

    // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("La réservation a été ajoutée avec succès.");
    alert.showAndWait();

    // Effacer les champs de saisie
    nomEventComboBox.getSelectionModel().clearSelection();
    prixResTextField.setText("");
    dateResDatePicker.setValue(null);
    nbrPlaceTextField.setText("");
    typeResTextField.setText("");

    ShowProduits();
}

      
      //methode Affiche
       public ObservableList<Reservation> getProduitList() {
        
        ObservableList<Reservation> ProduitList = FXCollections.observableArrayList();
        Connection myconn =MyConnection.getInstance().getConnexion();
        try {
      // Créer un objet Statcement
      Statement myStmt = myconn.createStatement();

      // Exécuter la requête SQLS
      ResultSet myRs = myStmt.executeQuery("SELECT * FROM reservation");
   
      // Afficher les informations de chaque enregistrement
      while (myRs.next()) {
          Reservation x=  new Reservation(myRs.getInt("Id_Res"),myRs.getString("Nom_Ev"),myRs.getDouble("Prix_Res"),myRs.getDate("Date_Res"),myRs.getInt("Nbr_Place"),myRs.getString("Type_Res"));
          ProduitList.add(x);
      }
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
        
   
        return ProduitList;
        
    }
         public void btnDelete() {
    // Récupérer la ligne sélectionnée dans la tableview
    Reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();
    if (selectedReservation == null) {
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
            String query = "DELETE FROM Reservation WHERE Id_Res = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, selectedReservation.getId_Res());
            // Exécuter la requête SQL pour supprimer la ligne sélectionnée
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                // Afficher une alerte pour indiquer que la ligne a été supprimée
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Suppression réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("La ligne sélectionnée a été supprimée avec succès.");
                successAlert.showAndWait();
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
    public void ShowProduits(){
        
         ObservableList<Reservation> listp = getProduitList();
          IdRes.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Id_Res"));
           NomEV.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Nom_Ev"));
            PrixRes.setCellValueFactory(new PropertyValueFactory<Reservation,Double>("Prix_Res"));
             DateRes.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("Date_Res"));
          NbrPlace.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("Nbr_Place")); 
        TypeRes.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Type_Res"));
    reservationTableView.setItems(listp);
    }

    public void btnUpdate() {
         try {
        // Récupérer une instance de connexion à la base de données
        Connection conn = MyConnection.getInstance().getConnexion();

        // Créer une requête SQL pour mettre à jour les informations du produit
     String query = "UPDATE Reservation SET Prix_Res=?, Date_Res=?, Nbr_Place=?, Type_Res=? WHERE Id_Res=?";

// Préparer la requête SQL en utilisant un objet PreparedStatement
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setDouble(1, Double.parseDouble(prixResTextField.getText()));
pstmt.setDate(2, Date.valueOf(dateResDatePicker.getValue()));
pstmt.setInt(3, Integer.parseInt(nbrPlaceTextField.getText()));
pstmt.setString(4, typeResTextField.getText());
pstmt.setInt(5, Integer.parseInt(tfId_Res.getText()));



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
    @FXML
      public void retourVersIAD2 (ActionEvent e) throws IOException{



SceneControllerAla sca = new SceneControllerAla();
sca.switchToScene3(e);

}
    }
  







        
    
      
    
    

