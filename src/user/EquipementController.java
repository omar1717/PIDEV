/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import SERVICES.equipServ;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entities.equipement;
import Utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Utils.MyConnection;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import user.sceneControllerT;



/**
 * FXML Controller class
 *
 * @author yessine
 */
public class EquipementController implements Initializable {

    @FXML
    private TableView<equipement> TabEQ;
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
    private JFXTextField nomEqui;
    @FXML
    private JFXTextField TypEqui;
    @FXML
    private JFXTextField NomE;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Delete;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXButton Refresh;
    private JFXTextField search;
    @FXML
    private JFXButton swit;
    @FXML
    private JFXButton PToPDF;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        RenderEquipement();
        //searchlist();
// TODO
    }    
     /////////////////////////////////DB TO LIST//////////////////////////////////////////
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
    
    
      /////////////////////////////////LIST TO TABLE//////////////////////////////////////////
    public void RenderEquipement()
    {
        ObservableList<equipement> equipementList = getEquipementtList();
        
       
        idEQ.setCellValueFactory(new PropertyValueFactory<>("id_equi"));
        nomEQ.setCellValueFactory(new PropertyValueFactory<>("nom_equi"));
        typeEQ.setCellValueFactory(new PropertyValueFactory<>("type_eq"));
        nomENTR.setCellValueFactory(new PropertyValueFactory<>("nom_entr"));
        
        TabEQ.setItems(equipementList);
    }

    @FXML
    private void handleButtonAction(ActionEvent event)throws IOException, FileNotFoundException, DocumentException  {
        sceneControllerT sc = new sceneControllerT();
        
        if (event.getSource()==Add){
            ajoutEQ();
            
        }
        if (event.getSource()==Delete){
            RemoveEQ();
            RenderEquipement();
        }
         if (event.getSource()==Update){
            ModifyRow();
            RenderEquipement();
        }
        if (event.getSource()==Refresh){
            
            RenderEquipement();
        }
        
        if (event.getSource()==swit){
            sc.switchToScene1(event);
            
        }
        if (event.getSource() == PToPDF ){
            printtopdf();
        }
       
    }
    
    
    public void ajoutEQ(){
        equipement eq= new equipement(nomEqui.getText(),TypEqui.getText(), NomE.getText());
        equipServ EE = new equipServ();
        EE.ajoutereq(eq);
    }
    
    public void RemoveEQ(){
        equipement equipemenSelected;
        equipemenSelected= TabEQ.getSelectionModel().getSelectedItem();
        //Convert to String
        String idSelected= String.valueOf(equipemenSelected.getId_equi());
        //call for remove meth
        equipServ EE= new equipServ();
        EE.supprimereq(idSelected);
        
        
    }
    
    public void ModifyRow(){
        if (nomEqui.getText().isEmpty() ||TypEqui.getText().isEmpty()) {
        System.out.println("equipement  should not be empty");
        return;
    }
        equipement equipemenSelected;
        equipemenSelected= TabEQ.getSelectionModel().getSelectedItem();
        int a = equipemenSelected.getId_equi();
        // convert to String
         String idSelected= String.valueOf(a);
          //call for modify meth
          equipement eq= new equipement(nomEqui.getText(),TypEqui.getText(), NomE.getText());
          equipServ EE= new equipServ();
        EE.modifiereq(eq, idSelected);
        RenderEquipement();
    }

   

    
    
    
    /* public void searchlist(){
        
        ObservableList<equipement> equipementList = getEquipementtList();
        
        RenderEquipement();

        
     
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
        sortedData.comparatorProperty().bind(TabEQ.comparatorProperty());
        
        TabEQ.setItems(sortedData);
        
     } */

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
       
    /**
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void printtopdf() throws FileNotFoundException, DocumentException{
         equipServ sc = new equipServ();
         try{
         String fileName = "C:/PDF/EquipList.pdf";
         Document document = new Document();
  PdfWriter.getInstance(document, new FileOutputStream(fileName));

document.open();
for (equipement eq : getEquipementtList()) {
    // Write the user information to the PDF document
    document.add(new Paragraph("Nom eq: " + eq.getNom_equi()));
    document.add(new Paragraph("Type : " + eq.getType_eq()));
    document.add(new Paragraph("Nom entreprise : " + eq.getNom_entr()));
    
     
    document.add(new Paragraph("------------------------------------------------------------------------------"));
}

document.close();

JOptionPane.showMessageDialog(null , "PDF LIST CREATED");
         } catch (DocumentException | FileNotFoundException e){
             System.out.println(e);
         }



     }

    
    
   
    

  
    
}
