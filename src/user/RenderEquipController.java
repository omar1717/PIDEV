/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.jfoenix.controls.JFXTextField;
import Entities.equipement;
import Utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; 
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.MyConnection;



/**
 * FXML Controller class
 *
 * @author yessine
 */

public class RenderEquipController implements Initializable {

    private TableView<equipement> TabEQQ;
    @FXML
    private TableColumn<equipement,Integer> idEQQ;
    @FXML
    private TableColumn<equipement,String> nomEQQ;
    @FXML
    private TableColumn<equipement,String> typeEQQ;
    @FXML
    private TableColumn<equipement,String> nomENTRR;

     Connection mycon = MyConnection.getInstance().getConnexion();
   
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<equipement> TabQQ;
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
              eq = new equipement(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
              equipementList.add(eq);
          }         
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return equipementList;
    }
    
    
      /////////////////////////////////LIST TO TABLE//////////////////////////////////////////
   public void RenderEquipement(){
        ObservableList<equipement> equipementList = getEquipementtList();
        
       
        idEQQ.setCellValueFactory(new PropertyValueFactory<>("id_equi"));
        nomEQQ.setCellValueFactory(new PropertyValueFactory<>("nom_equi"));
        typeEQQ.setCellValueFactory(new PropertyValueFactory<>("type_eq"));
        nomENTRR.setCellValueFactory(new PropertyValueFactory<>("nom_entr"));
        
        TabEQQ.setItems(equipementList);
    }
    
       
      /*     FilteredList<equipement> filteredData = new FilteredList<>(equipementList,b ->true );
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
        sortedData.comparatorProperty().bind(TabEQ.comparatorProperty());
        
        TabEQ.setItems(sortedData);
        
        */
}
    