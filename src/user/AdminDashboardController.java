/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package user;

import Entities.user;
import Utils.MyConnection;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.userServices;

/**
 * FXML Controller class
 *
 * @author omar
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private TableView<user> Atables;
    @FXML
    private TableColumn<user, Integer> Aids;
    @FXML
    private TableColumn<user, String> Anoms;
    @FXML
    private TableColumn<user, String> Aprenoms;
    @FXML
    private TableColumn<user, LocalDate> Adates;
    @FXML
    private TableColumn<user, String> Aemails;
    @FXML
    private TableColumn<user, String> Amdps;
    @FXML
    private TableColumn<user, String> Aroles;
    @FXML
    private JFXTextField search;
    @FXML
    private Button dashreturn;
    @FXML
    private PieChart piechart;
    @FXML
    private Label over18;
    @FXML
    private Label under18;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       searchlist();
       
       
       PieChart.Data qtr1;
        try {
            qtr1 = new PieChart.Data("Age over 18 ",countover18() );
           PieChart.Data qtr2 = new PieChart.Data("Age under 18 ",countunder18() );
             piechart.getData().addAll(qtr1,qtr2);
             over18.setText(String.valueOf(countover18()));
          under18.setText(String.valueOf(countunder18()));
             
             
             
             
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
       
       
       
    }    
    public void searchlist(){
        
        userServices us = new userServices();
        us.getuserList();
        
        Aids.setCellValueFactory(new PropertyValueFactory<user, Integer>("id"));
        Anoms.setCellValueFactory(new PropertyValueFactory<user, String>("Nom"));
        Aprenoms.setCellValueFactory(new PropertyValueFactory<user, String>("Prenom"));
        Aemails.setCellValueFactory(new PropertyValueFactory<user, String>("Email"));
        Adates.setCellValueFactory(new PropertyValueFactory<user, LocalDate>("Age"));
         Amdps.setCellValueFactory(new PropertyValueFactory<user, String>("mdp"));
          Aroles.setCellValueFactory(new PropertyValueFactory<user, String>("Role"));
        
        Atables.setItems(us.getuserList());
   

        
     
        FilteredList<user> filteredData = new FilteredList<>(us.getuserList(),b ->true );
        search.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate((user) -> {
                
                
                if(newvalue.isEmpty()|| newvalue == null ){
                    return true;
                }
                String Searchkeyword = newvalue.toLowerCase();
                
                if(user.getNom().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else  if(user.getEmail().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                }
                else if(user.getPrenom().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true;
                
                
                }else if(user.getAge().toString().toLowerCase().indexOf(Searchkeyword) > -1){
                    return true; 
                }else 
                return false;
                
            });
        });
        
        SortedList<user> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Atables.comparatorProperty());
        
        Atables.setItems(sortedData);
        
      
       
    }

    @FXML
    private void OnBtnClick(ActionEvent event) throws IOException {
        if (event.getSource() == dashreturn ) {
             SceneController sc = new SceneController();
           sc.switchToScene8(event);
        }
    }
    
    // 
    public double countunder18() throws SQLException{
       double x=0;
        Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="  SELECT COUNT(*) AS total_utilisateurs FROM user WHERE age > '2005-01-01 '";
     
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); 
          
          
         ResultSet rs1 = ste.executeQuery();
         while(rs1.next()){
        x =  rs1.getDouble(1);
        
       
        
         }
         
        
        
         
          
          
         } catch (SQLException ex) {
            System.out.println(ex);
            
        }
return x;
         }
    
    
    
     public double countover18() throws SQLException{
       double x=0;
        Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="  SELECT COUNT(*) AS total_utilisateurs FROM user WHERE age < '2005-01-01 '";
     
     
     try{
         
         PreparedStatement ste= myconn.prepareStatement(query); 
          
          
         ResultSet rs1 = ste.executeQuery();
         while(rs1.next()){
        x =  rs1.getDouble(1);
       
        
         }
         
        
        
         
          
          
         } catch (SQLException ex) {
            System.out.println(ex);
            
        }
return x;
         }
}


/* 
String sql ="  SELECT COUNT(*) AS total_utilisateurs FROM user WHERE age < '2005-01-01 '";
  ResultSet rs2 = stmt.executeQuery();
          while (rs2.next()){
     double  Y =  rs2.getDouble(1);
      PieChart.Data qtr2 = new PieChart.Data("Age under 18 ", Y);
   
          }
*/