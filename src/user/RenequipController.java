/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entities.equipement;
import Utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import user.sceneControllerT;
import Utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author yessine
 */
public class RenequipController implements Initializable {

    @FXML
    private TableView<equipement> TABeq;
    @FXML
    private TableColumn<equipement,Integer> idEQ;
    @FXML
    private TableColumn<equipement,String> nomEQ;
    @FXML
    private TableColumn<equipement,String> typeEQ;
    @FXML
    private TableColumn<equipement,String> nomENTR;
Connection mycon = MyConnection.getInstance().getConnexion();
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton ReturnEQ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    RenderEquipement();
    
    }    
    
    
      public ObservableList<equipement> getEquipementtList()
    {
        ObservableList<equipement> equipementList = FXCollections.observableArrayList();
       
        String query = "SELECT * FROM equipement";
        Statement st;
        ResultSet rs;
        try 
        {
          st = mycon.createStatement();
          rs = st.executeQuery(query);
          equipement eq;
          while (rs.next())
          {
              eq = new equipement(rs.getInt("id_equi"),rs.getString("nom_equi"),rs.getString("type_eq"),rs.getString("nom_entr"));
              equipementList.add(eq);
          }         
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return equipementList;
    }

      
      
      
      
       public void RenderEquipement()
    {
        ObservableList<equipement> equipementList = getEquipementtList();
        
       
        idEQ.setCellValueFactory(new PropertyValueFactory<>("id_equi"));
        nomEQ.setCellValueFactory(new PropertyValueFactory<>("nom_equi"));
        typeEQ.setCellValueFactory(new PropertyValueFactory<>("type_eq"));
        nomENTR.setCellValueFactory(new PropertyValueFactory<>("nom_entr"));
        
        TABeq.setItems(equipementList);
        
        
         FilteredList<equipement> filteredData = new FilteredList<>(equipementList,b ->true );
        search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate((equipement) -> {
                
                
                if(newvalue.isEmpty()|| newvalue == null ){
                    return true;
                }
                String Searchkeyword = newvalue.toLowerCase();
                
                if(equipement.getNom_equi().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else  if(equipement.getType_eq().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else if(equipement.getNom_entr().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                
                
                
                }else 
                return false;
                
            });
        });
        
        SortedList<equipement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TABeq.comparatorProperty());
        
        TABeq.setItems(sortedData);
    }

    @FXML
    private void HandleButtonAction(ActionEvent event) throws IOException {
         sceneControllerT sc = new sceneControllerT();
         if(event.getSource()==ReturnEQ){
             sc.switchToScene2(event);
         }
    }
      
}
