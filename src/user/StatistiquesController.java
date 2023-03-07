/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Entities.Statistiques;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import SERVICES.article_serv;

/**
 * FXML Controller class
 *
 * @author MSI
 */





public class StatistiquesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
 @FXML
private TableView<Statistiques> tableStatistiques;

@FXML
private TableColumn<Statistiques, String> col_su;

@FXML
private TableColumn<Statistiques, Integer> col_nbr_art;

@FXML
private TableColumn<Statistiques, Double> col_stat;

//...

public void showStatistiques() {
    
    article_serv art_service= new article_serv();
    List<Statistiques> Statistiques = art_service.getStatistiques();
    ObservableList<Statistiques> observableStatistiques = FXCollections.observableArrayList(Statistiques);
    tableStatistiques.setItems(observableStatistiques);
    col_su.setCellValueFactory(new PropertyValueFactory<>("sujet"));
    col_nbr_art.setCellValueFactory(new PropertyValueFactory<>("nombreArticles"));
    col_stat.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
    
}
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            showStatistiques();
    }    
    
}
