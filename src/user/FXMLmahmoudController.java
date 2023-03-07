/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import static com.sun.jndi.toolkit.dir.DirSearch.search;
import Entities.article;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeString.search;
import SERVICES.article_serv;


/**
 * FXML Controller class
 *
 * @author MSI
 */

public class FXMLmahmoudController implements Initializable {

      @FXML
    private TextField etattxt;
      
      
    @FXML
    private TextField search;

    @FXML
    private TextField id_journalistetxt;

    @FXML
    private TextField sujettxt;

    @FXML
    private Button addbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private TableColumn<article, String> etat_col;
    private FilteredList<article> listeFiltree;
    private ObservableList<article> liste_articles;


    @FXML
    private Label idtxt;
    
       @FXML
    private ComboBox<String> combobox;

    @FXML
    private TableColumn<article, Integer> id_journcol;
 


    @FXML
    private TableColumn<article, String> sujcol;

  
    @FXML
    private TableView<article> table_article;

    @FXML
    private Button updatebtn;
    
    @FXML
    void add(ActionEvent event) {
        
         if(id_journalistetxt.getText().isEmpty()||sujettxt.getText().isEmpty()||etattxt.getText().isEmpty()){
            
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Champ vide");
                        alert.setHeaderText("Remplissez les champs vides s'il vous plait");
                        alert.showAndWait();
        }
            
            
        else {
        String sujet = sujettxt.getText();
        if(!sujet.equals("politique") && !sujet.equals("sport") && !sujet.equals("actualité")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sujet invalide");
            alert.setHeaderText("Le sujet doit être 'politique', 'sport' ou 'actualité'");
            alert.showAndWait();
        } else {
            article art = new article(Integer.parseInt(id_journalistetxt.getText()), sujet, etattxt.getText());
            article_serv art_service = new article_serv();
            art_service.ajouter_article(art);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLmahmoud.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                System.out.println("ajout avec succès");
                show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}






    @FXML
    void delete(ActionEvent event) {

        int id=Integer.parseInt(idtxt.getText());
        int id_journ=Integer.parseInt(id_journalistetxt.getText());
        String sujet= sujettxt.getText();
        String etat = etattxt.getText();
        article a10 = new article(id ,id_journ, sujet, etat);
article_serv a= new article_serv();
a.supprimer_article(a10);
show();
    }

    @FXML
    void update(ActionEvent event) {
        
        int id=Integer.parseInt(idtxt.getText());
        int id_journ=Integer.parseInt(id_journalistetxt.getText());
        String sujet= sujettxt.getText();
        String etat = etattxt.getText();

       article ar = new article( id,id_journ, sujet, etat);
article_serv a1= new article_serv();
a1.modifier_article(ar);
show();
  
    }
    
    public void show(){
     
        ObservableList<article> liste_article = FXCollections.observableArrayList();
        
        article_serv art_s=new article_serv ();
        liste_article=art_s.afficher_articles();
       id_journcol.setCellValueFactory(new PropertyValueFactory<>("id_journ"));
       sujcol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       etat_col.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
        table_article.setItems( liste_article );

        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    combobox.getItems().addAll("politique", "sport", "actualité");
    article_serv a = new article_serv();
    ObservableList<article> liste_article = FXCollections.observableArrayList();

        liste_article=a.afficher_articles();
        
    // Créer une liste filtrée vide
    listeFiltree = new FilteredList<>(liste_article);

    // Ajouter les colonnes du TableView

    id_journcol.setCellValueFactory(new PropertyValueFactory<>("id_journ"));

    sujcol.setCellValueFactory(new PropertyValueFactory<>("sujet"));

    etat_col.setCellValueFactory(new PropertyValueFactory<>("etat"));


    // Afficher les articles dans le TableView
    table_article.setItems(listeFiltree);
    searchlist();
    }


@FXML
    void onComboBoxChanged(ActionEvent eventt) {
    String option = combobox.getValue();
    System.out.println("ComboBox changed");

    // Appliquer le filtre sur la liste complète des articles
    if (option.equals("politique")) {
        listeFiltree.setPredicate(article -> article.getSujet().equals("politique"));
    } else if (option.equals("sport")) {
        listeFiltree.setPredicate(article -> article.getSujet().equals("sport"));
    } else if (option.equals("actualité")) {
        listeFiltree.setPredicate(article -> article.getSujet().equals("actualité"));
    } else {
        listeFiltree.setPredicate(null);
    }
        table_article.refresh();

    System.out.println(listeFiltree);
    
}    

    @FXML
    private void SelectEV(MouseEvent event) {
     article selectedEV =  table_article.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(selectedEV.getId()));
        id_journalistetxt.setText(String.valueOf(selectedEV.getId_journ()));
        sujettxt.setText(selectedEV.getSujet());
        etattxt.setText(selectedEV.getEtat());
       
    }
    
    
    public void showStatPage(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}  
    
    
 public void searchlist(){
        
        article_serv as = new article_serv();
        as.getarticlelist();
        
        id_journcol.setCellValueFactory(new PropertyValueFactory<article, Integer>("id_journ"));
        sujcol.setCellValueFactory(new PropertyValueFactory<article, String>("sujet"));
        etat_col.setCellValueFactory(new PropertyValueFactory<article, String>("etat"));
        
        table_article.setItems(as.getarticlelist());

        FilteredList<article> filteredData = new FilteredList<>(as.getarticlelist(), b -> true);
        search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate((article) -> {
                
                if(newvalue.isEmpty()|| newvalue == null ){
                    return true;
                }
                String Searchkeyword = newvalue.toLowerCase();
                
                if(article.getEtat().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else  if(article.getSujet().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                
                else 
                return false;
                
            });
        });
        
        SortedList<article> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_article.comparatorProperty());
        
        table_article.setItems(sortedData);
        
      
 }
    }
