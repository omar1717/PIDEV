/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
    import Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MeuilleurVenteController implements Initializable {

    @FXML
    private PieChart Chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Connection myconn =MyConnection.getInstance().getConnexion();
            
            String sql = "SELECT Nom_Achat, COUNT(*) AS nb_ventes FROM facture GROUP BY Nom_Achat ORDER BY nb_ventes DESC LIMIT 5";
            PreparedStatement statement = myconn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
             ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
while (resultSet.next()) {
    String nomProduit = resultSet.getString("Nom_Achat");
    int nbVentes = resultSet.getInt("nb_ventes");
    PieChart.Data data = new PieChart.Data(nomProduit, nbVentes);
    pieChartData.add(data);
    Chart.setData(pieChartData);
Chart.setTitle("Les Cinque Meilleures ventes ");
}

        } catch (SQLException ex) {
            Logger.getLogger(MeuilleurVenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

}
    @FXML
  public void retournerVersProduitAdmin (ActionEvent e) throws IOException{
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("/GUIProduit/Main2.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();

}
    }   

    

