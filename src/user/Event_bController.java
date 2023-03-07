/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;


import Entities.event;
import Entities.salle;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;

import java.util.ResourceBundle;


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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import SERVICES.eventservice;
import SERVICES.salleservice;


public class Event_bController implements Initializable{
    
    
    @FXML
    private Button btnsalle;



       @FXML
    private Button addbtn;

    @FXML
    private TableColumn<event, Date> date_debcol;
    

  @FXML
    private DatePicker date_debtxt;

   

    @FXML
    private DatePicker date_fintxt;

    @FXML
    private TableColumn<event, Date>  date_fincol;

   

    @FXML
    private Button deletebtn;


    @FXML
    private TextField id_eventtxt;

    @FXML
    private TableColumn<event, String>  nbre_sallecol;

    private TextField nbre_salletxt;

  
    

    @FXML
    private TableView<event> tabeleaffichage;

    @FXML
    private TableColumn<event, String> type_eventcol;

    private TextField type_eventtxt;

    @FXML
    private Button updatebtn;
    @FXML
    private Button btnstat;
    @FXML
    private ComboBox<salle> listetypeev;
    @FXML
    private ComboBox<String> type_eventbox;
    @FXML
    private Button back;
    @FXML
    private TableColumn<event, Integer> participants;

    
    
    
    

    
  
    
    
    public void show(){
     
        ObservableList<event> listee = FXCollections.observableArrayList();
        eventservice es=new eventservice ();
        listee=es.afficherevent();
       //id_eventcol.setCellValueFactory(new PropertyValueFactory<>("id_event"));
       type_eventcol.setCellValueFactory(new PropertyValueFactory<>("type_event"));
    
       nbre_sallecol.setCellValueFactory(new PropertyValueFactory<>("nbre_salle"));
      
        date_debcol.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
       date_fincol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        participants.setCellValueFactory(new PropertyValueFactory<>("nbr_participants"));
        tabeleaffichage.setItems(listee);
        
        
    }
    
     

   

    
    @FXML
    void add(ActionEvent event) {

    event e= new event( id_eventtxt.getText(),type_eventbox.getValue(), listetypeev.getValue().getNbresalle(), Date.valueOf(date_debtxt.getValue()),Date.valueOf(date_fintxt.getValue()),0);
       eventservice es= new eventservice();
      //event e = iec.e;
        
        LocalDate dateDebut = date_debtxt.getValue();
    LocalDate dateFin =date_fintxt.getValue();
        if (dateDebut == null || dateFin == null) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please enter both start and end dates.");
        alert.showAndWait();
    } else {
        // Check if the end date is after the start date
        if (dateFin.isBefore(dateDebut)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("End date must be after start date.");
            alert.showAndWait();
        } else {
            // Dates are valid, continue with application logic
            es.ajouterevent(e);
            
           
        
        
    }
        try{
                    
                    Parent root = FXMLLoader.load(getClass().getResource("event_b.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    System.out.println("ajout avec succes");
                    
                    show();
        
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
        }
        

    }
    
    

    
   
   
  @FXML
    void update(ActionEvent event) {
        
        String nom_event= id_eventtxt.getText();
        String type_event= type_eventbox.getValue();
        String nbre_salle = listetypeev.getValue().getNbresalle();
        Date date_deb = Date.valueOf(date_debtxt.getValue());
        Date date_fin= Date.valueOf(date_fintxt.getValue());
        
        
       event eventt = new event(nom_event,type_event,nbre_salle,date_deb , date_fin);
eventservice e1= new eventservice();
e1.modifiereventt(eventt);
show();
  
    }
     @FXML
    void delete(ActionEvent event) {
       


event eventselected =  tabeleaffichage.getSelectionModel().getSelectedItem();
      // System.out.println(selectedLN.getId_b());
      eventservice es =new eventservice(); 
        es.supprimerevent(eventselected);
        show();
      Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("l'eventselectionné a été supprimer avec succès ! ");
    alert.showAndWait();
    }


   
    
    
       @FXML
    void afficherppagesalle(ActionEvent event) {

    
    try
    {
    
          Parent root= FXMLLoader.load(getClass().getResource("salleb.fxml"));
            Scene scene= new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     catch (Exception e) {
          // e.printstackTrace (); 
     }

    }

    @FXML
    void SelectEV(MouseEvent event) {
         event selectedEV = tabeleaffichage.getSelectionModel().getSelectedItem();
       
       id_eventtxt.setText(selectedEV.getNom_event());

       type_eventbox.setValue(selectedEV.getType_event());
     
      //listetypeev.setValue(selectedEV.getNbre_salle());
       date_debtxt.setValue(selectedEV.getDate_deb().toLocalDate());
       
       date_fintxt.setValue(selectedEV.getDate_fin().toLocalDate());
              
       
       
    }

    @FXML
  
        
        public void showStatPage(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
        
         ObservableList<salle> listes = FXCollections.observableArrayList();
         salleservice ss= new salleservice();
         
         public void initnumerosalle()
         {
         listes = ss.affichersalle();
         listetypeev.setItems(listes);
         }
         
          @Override
    public void initialize(URL location, ResourceBundle resources) {
         
       initnumerosalle();
        
       show();
       
       listetypeev.setCellFactory(new Callback<ListView<salle>, ListCell<salle>>() {
    @Override
    public ListCell<salle> call(ListView<salle> param) {
        return new ListCell<salle>() {
            @Override
            protected void updateItem(salle item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText("");
                } else {
                    setText(item.getNbresalle());
                }
            }
        };
    }
});
       
       
       type_eventbox.getItems().addAll("film", "ceremonie", "fete");
//type_eventbox.setValue("film");
      
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToScene11(event);
        
    }

    }


