/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Entities.article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.objects.NativeString.search;
import SERVICES.article_serv;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontController implements Initializable {

    @FXML
    private TableColumn<article, String> dscrx;
    @FXML
    private TableView<article> tab_affichagex;
    @FXML
    private TableColumn<article, String> sujx;
    @FXML
    private ComboBox<String> comboboxx;
    
       private FilteredList<article> listeFiltree;
    private ObservableList<article> liste_articles;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxx.getItems().addAll("politique", "sport", "actualité");
    article_serv a = new article_serv();
    ObservableList<article> liste_article = FXCollections.observableArrayList();

        liste_article=a.afficher_articles();
        
    // Créer une liste filtrée vide
    listeFiltree = new FilteredList<>(liste_article);

    // Ajouter les colonnes du TableView

    sujx.setCellValueFactory(new PropertyValueFactory<>("sujet"));

    dscrx.setCellValueFactory(new PropertyValueFactory<>("etat"));



    // Afficher les articles dans le TableView
    tab_affichagex.setItems(listeFiltree);
    }    

    

    @FXML
    private void combobox(ActionEvent event) {
         String option = comboboxx.getValue();
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
        tab_affichagex.refresh();

    System.out.println(listeFiltree);
    }
    
     
}
