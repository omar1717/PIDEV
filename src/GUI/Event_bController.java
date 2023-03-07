/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class Event_bController implements Initializable {

    @FXML
    private TableView<?> tabeleaffichage;
    @FXML
    private TableColumn<?, ?> type_eventcol;
    @FXML
    private TableColumn<?, ?> nbre_sallecol;
    @FXML
    private TableColumn<?, ?> date_debcol;
    @FXML
    private TableColumn<?, ?> date_fincol;
    @FXML
    private TextField id_eventtxt;
    @FXML
    private DatePicker date_debtxt;
    @FXML
    private DatePicker date_fintxt;
    @FXML
    private ComboBox<?> listetypeev;
    @FXML
    private ComboBox<?> type_eventbox;
    @FXML
    private Button deletebtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button btnsalle;
    @FXML
    private Button btnstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SelectEV(MouseEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void afficherppagesalle(ActionEvent event) {
    }

    @FXML
    private void showStatPage(ActionEvent event) {
    }
    
}
