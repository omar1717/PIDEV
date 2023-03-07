/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICES;
import Entities.Statistique;
import Entities.event;
import Entities.salle;
//umport interfacee.ievent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.image.ImageView;
import Utils.MyConnection;

/**
 *
 * @author LENOVO
 */
public class eventservice  {
    Connection myconn =MyConnection.getInstance().getConnexion();
   // @Override
   public void ajouterevent(event e) {
    try {
        // Création d'une requête SQL pour insérer un nouveau event dans la table "event"
        PreparedStatement prepareStatement;
        String sql= "INSERT INTO event VALUES (?, ?, ?, ?, ?, ?)";
        prepareStatement = myconn.prepareStatement(sql);

        // Définition des valeurs pour les paramètres de la requête
        prepareStatement.setString(1, e.getNom_event());
        prepareStatement.setString(2, e.getType_event());
        prepareStatement.setString(3, e.getNbre_salle());
        prepareStatement.setDate(4, e.getDate_deb());
        prepareStatement.setDate(5, e.getDate_fin());
        prepareStatement.setInt(6, 0); // Initialisation de nbr_participant à 0
        //prepareStatement.setInt(7, e.getNbr_participants());

        prepareStatement.executeUpdate();
        System.out.println("ajout avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("echec d'ajout ");
    }
}
    

    //@Override
  

   // @Override
    public void supprimerevent(event e) {
        try {
            // Création d'une requête SQL pour supprimer un produit existant dans la table "event"
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM event where nom_event = ?");
            preparedStatement.setString(1, e.getNom_event());

        

            // Exécution de la requête pour supprimer le produit de la table
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   // @Override
    public ObservableList<event> afficherevent() {
        ObservableList<event> listee = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM event";
            Statement statement = myconn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    String id_event = result.getString("nom_event");
    String type_event = result.getString("type_event");
  
    String nbre_salle= result.getString("nbre_salle");
     Date date_deb=result.getDate("date_deb");
     Date date_fin=result.getDate("date_fin");
     int nbr_participants=result.getInt("nbr_participants");
   
     event e1=new event(id_event,type_event,  nbre_salle, date_deb, date_fin ,nbr_participants);
    listee.add(e1);
               //String output = "boutique %d : %s | %s | %s | %s | %d | %d | %s | %s  ";
    //System.out.println(String.format(output, ++count, nom, email, lien,description,num_telephone,num_fixe,governerat,ville));
    
    
    }
    
            } catch (SQLException ex) {
            System.out.println(ex);
            }

    return listee;
        
    }




        
    

public ObservableList<event> afficherevent(String event_filtre) {
    ObservableList<event> liste_event = FXCollections.observableArrayList();
    FilteredList<event> listeFiltree = new FilteredList<>(liste_event, a -> a.getType_event().equals("en cours"));


    try {
        String sql = "SELECT * FROM event";
        if (event_filtre != null && !event_filtre.isEmpty()) {
            sql += " WHERE sujet = ?";
        }
        PreparedStatement statement = myconn.prepareStatement(sql);
        if (event_filtre != null && !event_filtre.isEmpty()) {
            statement.setString(1, event_filtre);
        }
        ResultSet result = statement.executeQuery();
        while (result.next()){
             String nom_event = result.getString("nom_event");
            String type_event = result.getString("type_event");
        
            String nbre_salle= result.getString("nbre_salle");
            Date date_deb=result.getDate("date_deb");
            Date date_fin=result.getDate("date_fin");
            event articless=new event(nom_event,type_event, nbre_salle, date_deb, date_fin);
            liste_event.add(articless);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return liste_event;
}


//stat

 public List<Statistique> getStatistiques() {
        List<Statistique> Statistiques = new ArrayList<>();
        try {
            PreparedStatement prepareStatement;
            String sql = "SELECT type_event, COUNT(*) AS nombreevents, COUNT(*) / (SELECT COUNT(*) FROM event) * 100 AS pourcentage FROM event GROUP BY type_event";
            prepareStatement = myconn.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next()) {
                String type_event = result.getString("type_event");
                int nombreevents = result.getInt("nombreevents");
                double pourcentage = result.getDouble("pourcentage");
                Statistique statistique = new Statistique(type_event, nombreevents, pourcentage);
                Statistiques.add(statistique);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Statistiques;
    }
 
 //nbrparticipants
 public int getNbrParticipants(String nom_event) {
    int nbr_participants = 0;
    try {
        String query = "SELECT nbr_participants FROM event WHERE nom_event = ?";
        PreparedStatement pst = myconn.prepareStatement(query);
        pst.setString(1, nom_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nbr_participants = rs.getInt("nbr_participants");
        }
        pst.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return nbr_participants;
}

public String getNomEvent(int id_event) {
    String nom_event = "";
    try {
        String query = "SELECT nom_event FROM event ";
        PreparedStatement pst = myconn.prepareStatement(query);
        //pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nom_event = rs.getString("nom_event");
        }
        pst.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return nom_event;
}
public void modifierevent(event e) {
    try {
        String query = "UPDATE event SET nbr_participants = ? WHERE nom_event = ?";
        PreparedStatement pst =  myconn.prepareStatement(query);
        pst.setInt(1, e.getNbr_participants());
        pst.setString(2, e.getNom_event());
        pst.executeUpdate();
        pst.close();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}



 public void modifiereventt(event e) {
                    try {
            // Création d'une requête SQL pour mettre à jour l'event existant dans la table "event"
            PreparedStatement preparedStatement = myconn.prepareStatement("UPDATE event SET type_event = ?,  nbre_salle = ? , date_deb  = ?, date_fin = ?  WHERE nom_event = ?");

            // Définition des valeurs pour les paramètres de la requête
           
            preparedStatement.setString(1, e.getType_event());
         
          
            preparedStatement.setString(2, e.getNbre_salle());
            preparedStatement.setDate(3,e.getDate_deb());
              preparedStatement.setDate(4,e.getDate_fin());
              preparedStatement.setString(5,e.getNom_event());
            preparedStatement.executeUpdate();
            System.out.println(" event modifiée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(eventservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
}