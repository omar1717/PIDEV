/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;


import Entities.event;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
//import javafx.scene.image.Image;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import SERVICES.eventservice;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ParticipationController implements Initializable {

    @FXML
    private ComboBox<event> nomeventbox;
    @FXML
    private Button validerbtn;
    
     ObservableList<event> listee = FXCollections.observableArrayList();
        eventservice ss= new eventservice();
         
    
    
    public void initnomevent()
         {
         listee = ss.afficherevent();
        nomeventbox.setItems(listee);
         }



   


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         initnomevent();
         
         

    }

    @FXML
   void valider(ActionEvent event) {
        
        
        
     event selectedEvent = nomeventbox.getValue();
    String nom_event = selectedEvent.getNom_event();
    int participants = ss.getNbrParticipants(nom_event);
    if (participants < 10) {
        participants += 1;
        selectedEvent.setNbr_participants(participants);
        ss.modifierevent(selectedEvent);
    } else {
        // Afficher une alerte indiquant qu'il n'y a plus de places disponibles pour cet événement
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Plus de places disponibles");
        alert.setHeaderText("Il n'y a plus de places disponibles pour cet événement.");
        alert.setContentText("Veuillez choisir un autre événement ou revenir plus tard.");
        alert.showAndWait();
    }
   }

}
