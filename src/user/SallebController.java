/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Entities.salle;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import SERVICES.eventservice;
import SERVICES.salleservice;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SallebController implements Initializable {

     @FXML
    private Button addbtn;

    @FXML
    private TableColumn<salle, String> cincol;

    @FXML
    private TextField cintxt;

    @FXML
    private Button delbtn;

    @FXML
    private Button editbtn;

    private TableColumn<salle, String> idecol;


    
     @FXML
    private TextField numsalletxt;
    
   @FXML
    private TableColumn<salle, String> numsallcol;


    @FXML
    private TableView<salle> table;

    @FXML
    private TableColumn<salle, String> typestcol;

    private TextField typestufftxt;
    @FXML
    private ComboBox<String> typestuffbox;
    @FXML
    private PieChart pieChart;
    @FXML
    private TextField search;
    @FXML
    private Button Chart;
    
    
    
    
    
    
 void afficherStatistiques() {
    // données de statistiques
    Map<String, Integer> roleCount = new HashMap<>();
    int totalUtilisateurs = table.getItems().size();

    // parcourir les données de la table et compter le nombre d'occurrences de chaque rôle
    for (salle salle: table.getItems()) {
        String type_stuff = salle.getType_stuff();
        roleCount.put(type_stuff, roleCount.getOrDefault(type_stuff, 0) + 1);
    }

    // Création des graphiques
    pieChart.setData(createPieChartData(roleCount));
   

    // Mise à jour du label totalLabel
   

    
}


    private ObservableList<PieChart.Data> createPieChartData(Map<String, Integer> roleCount) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : roleCount.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        return pieChartData;
    }

    

    
    
     public void show(){
     
        ObservableList<salle> listee = FXCollections.observableArrayList();
        salleservice es=new salleservice ();
        listee=es.affichersalle();
       //id_eventcol.setCellValueFactory(new PropertyValueFactory<>("id_event"));
       numsallcol.setCellValueFactory(new PropertyValueFactory<>("nbresalle"));
      typestcol.setCellValueFactory(new PropertyValueFactory<>("type_stuff"));
     
      
        cincol.setCellValueFactory(new PropertyValueFactory<>("cin_stuff"));
        table.setItems(listee);
       
    }
     
     

    @FXML
    void add(ActionEvent event) {
salle e= new salle( numsalletxt.getText(),typestuffbox.getValue(),cintxt.getText());
      salleservice es= new salleservice();
      //event e = iec.e;
        es.ajoutersalle(e);
        
        try{
                    
                    Parent root = FXMLLoader.load(getClass().getResource("salleb.fxml"));
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

    @FXML
    void delete(ActionEvent event) {
        
        
      salle eventselected =  table.getSelectionModel().getSelectedItem();
      // System.out.println(selectedLN.getId_b());
     salleservice es =new salleservice(); 
        es.supprimersalle(eventselected);
        show();
      Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("la salle selectionné a été supprimer avec succès ! ");
    alert.showAndWait();

    }

    @FXML
    void edit(ActionEvent event) {
        
         String nbresalle= numsalletxt.getText();
        String type_stuff= typestuffbox.getValue();
      
      
        String cin_stuff = cintxt.getText();
       
      salle sallee = new salle(nbresalle,type_stuff, cin_stuff);
salleservice e1= new salleservice();
e1.modifiersalle(sallee);
show();

    }
    
   
    @FXML
    void selectEV(MouseEvent event) {
        
       salle selectedEV = table.getSelectionModel().getSelectedItem();
       
      numsalletxt.setText(selectedEV.getNbresalle());

     typestuffbox.setValue(selectedEV.getType_stuff());
   
       cintxt.setText(selectedEV.getCin_stuff());

    }
    
    
    
    //search dinamique
    
    
    
    
    
    
    public void searchlist(){

       salleservice as = new salleservice();
        as.affichersalle();

      // numsallcol.setCellValueFactory(new PropertyValueFactory<salle, String>("nbrsalle"));
       typestcol.setCellValueFactory(new PropertyValueFactory<salle, String>("type_stuff"));
        //cincol.setCellValueFactory(new PropertyValueFactory<salle, String>("cin"));

        table.setItems(as.affichersalle());

        FilteredList<salle> filteredData = new FilteredList<>(as.affichersalle(), b -> true);
        search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate((salle) -> {

                if(newvalue.isEmpty()|| newvalue == null ){
                    return true;
                }
                String Searchkeyword = newvalue.toLowerCase();

                if(salle.getNbresalle().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else  if(salle. getType_stuff().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }

                else 
                return false;

            });
        });

        SortedList<salle> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);


 }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         show();
         
         typestuffbox.getItems().addAll("Les photographes", "press", "femme de menage","Autre");
         
         searchlist();
         
         
    
  
}

    @FXML
     void Chart(ActionEvent event) {
          afficherStatistiques();
    }
    } 
