/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICES;

import Entities.event;
import Entities.salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import SERVICES.eventservice;
import Utils.MyConnection;



/**
 *
 * @author LENOVO
 */
public class salleservice{
Connection myconn =MyConnection.getInstance().getConnexion();
    //@Override
    public void ajoutersalle(salle s) {
        
    
   
        try {
            // Création d'une requête SQL pour insérer un nouveau event dans la table "event"
            
            PreparedStatement prepareStatement;
           String sql= "INSERT INTO salle VALUES (?, ? , ?)";
            prepareStatement = myconn.prepareStatement(sql);

            // Définition des valeurs pour les paramètres de la requête
             prepareStatement.setString(1, s.getNbresalle());
            prepareStatement.setString(2, s.getType_stuff());

             prepareStatement.setString(3,s.getCin_stuff());
             


            // Exécution de la requête pour insérer l'event dans la table
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }

      
    }

    //@Override
    public void modifiersalle(salle s) {
         
         try {
            // Création d'une requête SQL pour mettre à jour l'event existant dans la table "event"
            PreparedStatement preparedStatement = myconn.prepareStatement("UPDATE salle SET type_stuff = ?,  cin_stuff = ? WHERE nbre_salle = ?");

            // Définition des valeurs pour les paramètres de la requête
           
            preparedStatement.setString(1, s.getType_stuff());
         
           
            preparedStatement.setString(2, s.getCin_stuff());
            preparedStatement.setString(3,s.getNbresalle());
            preparedStatement.executeUpdate();
            System.out.println(" SALLE modifiée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Override
    public void supprimersalle(salle s) {
        try {
            // Création d'une requête SQL pour supprimer un produit existant dans la table "salle"
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM salle where nbre_salle = ?");
              preparedStatement.setString(1, s.getNbresalle());


            // Exécution de la requête pour supprimer salle de la table
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   // @Override
    public ObservableList<salle> affichersalle() {
        ObservableList<salle> listes = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM salle";
            Statement statement = myconn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    String nbre_salle= result.getString("nbre_salle");      
    String type_stuff = result.getString("type_stuff");
   
    String cin_stuff = result.getString("cin_stuff");
    
    
    
     salle s1=new salle(nbre_salle,type_stuff, cin_stuff);
    listes.add(s1);
        
    
    
    
    }
    
            } catch (SQLException ex) {
            System.out.println(ex);
            }

    return listes;
        
    }
 
}
