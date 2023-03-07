/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICES;

import artP.Iequip;
import Entities.artist;
import Entities.equipement;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import Utils.MyConnection;


/**
 *
 * @author yessine
 */
public  class equipServ implements  Iequip{

  Connection mycon = MyConnection.getInstance().getConnexion();

@Override
    public void ajoutereq(equipement eq) {
    if (eq.getNom_equi().isEmpty() || eq.getType_eq().isEmpty()) {
        System.out.println("equipements must be entered");
        return;
    }

    String selectSql = "SELECT * FROM equipement WHERE nom_equi = ? AND type_eq = ?";
    String insertSql = "INSERT INTO equipement (nom_equi, type_eq, nom_entr) VALUES (?, ?, ?)";

    try (PreparedStatement selectStatement = mycon.prepareStatement(selectSql);
         PreparedStatement insertStatement = mycon.prepareStatement(insertSql)) {

        selectStatement.setString(1, eq.getNom_equi());
        selectStatement.setString(2, eq.getType_eq());

        try (ResultSet rs = selectStatement.executeQuery()) {
            if (rs.next()) {
                System.out.println("Equipement already exists in the database");
                JOptionPane.showMessageDialog(null , "Equipement already exists in the database");
            } else {
                insertStatement.setString(1, eq.getNom_equi());
                insertStatement.setString(2, eq.getType_eq());
                insertStatement.setString(3, eq.getNom_entr());


                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Equipement added with success!");
                    JOptionPane.showMessageDialog(null , "Equipement added with success!");
                }
            }
        }

    } catch (SQLException ex) {
        System.out.println(ex);
    }
    }
   

    @Override
    public void affichereq() {
               String sql="select * from equipement ";
        try {
     
    PreparedStatement ste=mycon.prepareStatement(sql);

      ResultSet myRs=ste.executeQuery();
     

      
      while (myRs.next()) {
        System.out.println("ID: " + myRs.getInt("id_equi"));
        System.out.println("Nom_equipement: " + myRs.getString("Nom_equi"));
        System.out.println("prenom_equipement: " + myRs.getString("type_eq"));
        System.out.println("nom entreprise: " + myRs.getInt("id_entr"));
       
        
            
        System.out.println("-------------------------------------");
    
      }
    } catch(SQLException ex){
         System.out.println(ex);
    }
    }

    @Override
    public void modifiereq(equipement eq, String str) {
   if ( eq.getNom_equi().isEmpty() || eq.getType_eq().isEmpty()) {
        System.out.println("equipements must be entered");
        JOptionPane.showMessageDialog(null , "Should not be empty ");
        return;
    }
     String sql="UPDATE equipement SET nom_equi = ?, type_eq = ?,  nom_entr = ? WHERE id_equi = ?";
        try{
           PreparedStatement preparedStatement = mycon.prepareStatement(sql);
           preparedStatement.setString(1, eq.getNom_equi());
           preparedStatement.setString(2, eq.getType_eq());
           preparedStatement.setString(3, eq.getNom_entr());
           preparedStatement.setString(4, str);
           preparedStatement.executeUpdate();
           JOptionPane.showMessageDialog(null , "modification with success ! ");
       }
    
       catch(SQLException ex){
           System.out.println(ex);
       }
    
    
    
    
    }
    
    
    
    
    
@Override
    public void supprimereq(String eq) {
    String sql="delete  FROM equipement WHERE id_equi = ?;";
         
        try {
            PreparedStatement ste=mycon.prepareStatement(sql);
                ste.setString(1, eq);
                ste.executeUpdate();
                 JOptionPane.showMessageDialog(null , "Deleted with success");
               
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
    }
    
    
    
    
    
    
    


   
}
