/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Entities.Statistique;
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
import SERVICES.eventservice;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */


@FXML
private TableView<Statistique> tableStatistiques;

@FXML
private TableColumn<Statistique, String> col_ty;

@FXML
private TableColumn<Statistique, Integer> col_no;

@FXML
private TableColumn<Statistique, Double> col_stat;
    @FXML
    private Button Back;



public void showStatistiques() {

    eventservice ev_service= new eventservice();
    List<Statistique> Statistiques = ev_service.getStatistiques();
    ObservableList<Statistique> observableStatistiques = FXCollections.observableArrayList(Statistiques);
    tableStatistiques.setItems(observableStatistiques);
    col_ty.setCellValueFactory(new PropertyValueFactory<>("type_event"));
    col_no.setCellValueFactory(new PropertyValueFactory<>("nombreevents"));
    col_stat.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));

}
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            showStatistiques();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
          SceneController sc = new SceneController();
             sc.switchToScene14(event);
    }



}