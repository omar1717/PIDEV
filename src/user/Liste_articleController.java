/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import Utils.MyConnection;
import Entities.article;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import SERVICES.article_serv;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Liste_articleController implements Initializable {

    @FXML
    private TableView<article> tab_arti;
    @FXML
    private TableColumn<article, String> descrip;
    @FXML
    private TableColumn<article, String> subject;
    @FXML
    private Button show;
    @FXML
    private TextField sujtxt;
    @FXML
    private TextField sujtxtt;
    @FXML
    private Button add_btn;
    @FXML
    private Button fasakh;
    @FXML
    private TableColumn<article, Integer> id_col;
    @FXML
    private Button modif;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void afficher_article() throws SQLException{
          int id_journaliste = Login_journalisteController.getsession();
    ObservableList<article> articlesList = article_serv.getArticlesByJournaliste();
    
    subject.setCellValueFactory(new PropertyValueFactory<article, String>("sujet"));
    descrip.setCellValueFactory(new PropertyValueFactory<article, String>("etat"));
    id_col.setCellValueFactory(new PropertyValueFactory<article, Integer>("id"));
    tab_arti.setItems(articlesList);
    }    

    @FXML
    private void OnBtnClick(ActionEvent event) throws SQLException {
        if (event.getSource()== show ){
            afficher_article();
        }
        
    }
    private void SelectEV(MouseEvent event) {
     article selectedEV =  tab_arti.getSelectionModel().getSelectedItem();
        sujtxt.setText(String.valueOf(selectedEV.getSujet()));
        sujtxtt.setText(String.valueOf(selectedEV.getEtat()));

       
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
         if(sujtxt.getText().isEmpty()||sujtxtt.getText().isEmpty()){
            
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Champ vide");
                        alert.setHeaderText("Remplissez les champs vides s'il vous plait");
                        alert.showAndWait();
        }
            
            
        else {
        String sujet = sujtxt.getText();
        if(!sujet.equals("politique") && !sujet.equals("sport") && !sujet.equals("actualité")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sujet invalide");
            alert.setHeaderText("Le sujet doit être 'politique', 'sport' ou 'actualité'");
            alert.showAndWait();
        } else {
            article art = new article((Login_journalisteController.getsession()), sujet, sujtxtt.getText());
            article_serv art_service = new article_serv();
            art_service.ajouter_article(art);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("liste_article.fxml"));
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
    public void show(){
     
        ObservableList<article> liste_article = FXCollections.observableArrayList();
        
        article_serv art_s=new article_serv ();
        liste_article=art_s.afficher_articles();
       subject.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       descrip.setCellValueFactory(new PropertyValueFactory<>("etat"));
      
        tab_arti.setItems( liste_article );

        
    }

    @FXML
    private void onbtndel(ActionEvent event) throws SQLException {
        Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            article articleSelected;
            articleSelected=tab_arti.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(articleSelected.getId());
            //Remove From db

            PreparedStatement pst = null;        
            //Our Query
            String query="DELETE FROM articles WHERE id = ?";
            try
            {
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values
            pst.setString(1,IdSelected);
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Deleted");
            }catch(Exception ex)
            {
            System.out.println("Delete impossible à effectuer.\nErreur :" + ex);
            }
            //Refresh Table
          afficher_article();
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Connection myconn =MyConnection.getInstance().getConnexion();
            //Get Id Selected
            article articleSelected;
            articleSelected=tab_arti.getSelectionModel().getSelectedItem();
            //Convert to String
            String IdSelected =String.valueOf(articleSelected.getId());
            //Remove From db

            PreparedStatement pst = null; 
            //Our Query
            String query="UPDATE articles SET etat = ? , sujet = ? WHERE id = ?";
            try
            {
            //Create PreparedStatement
            pst=myconn.prepareStatement(query);
            //Insert Values
             pst.setString(1, sujtxt.getText());
            pst.setString(2, sujtxtt.getText());
            pst.setString(3,IdSelected);
            //Execute Statement
            pst.executeUpdate();
            //Erreur Message
            JOptionPane.showMessageDialog(null , "Updated");
            }catch(HeadlessException | SQLException ex)
            {
            System.out.println("Update impossible à effectuer.\nErreur :" + ex);
            }
            //Refresh Table
           afficher_article();
    }
    }
    

