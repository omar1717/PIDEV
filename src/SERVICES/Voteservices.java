/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

/**
 *
 * @author omar
 */

import Entities.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Utils.MyConnection;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import user.LoginController;


public class Voteservices implements IVOTES {
Connection myconn =MyConnection.getInstance().getConnexion();
    @Override
    public void ajouterVote(Votes v) {
      
        try {
      
        
             
            String sql="INSERT INTO `votes` ( `voteA`, `voteE`, `idC`, `idA`) VALUES ( ?, ?, ?, ?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setDouble(1,v.getVoteA());
            ste.setDouble(2,v.getVoteE());
          ste.setInt(3,v.getIdC());
           ste.setInt(4,v.getIdA());
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null , "Added");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerVote(Votes v) {
         String sql="delete FROM Votes WHERE idC = ? ;";
         
        try {
              PreparedStatement ste= myconn.prepareStatement(sql);
                ste.setInt(1, v.getIdC());
                ste.executeUpdate();
                JOptionPane.showMessageDialog(null , "Removed");
               
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
    }

  public ObservableList<Votes> getvoteList()
    {
        ObservableList<Votes> votelist = FXCollections.observableArrayList();
        
        String query ="Select * FROM votes  ";
        
        try 
        {
         PreparedStatement ste= myconn.prepareStatement(query);
           ResultSet rs = ste.executeQuery();
          
          while (rs.next())
          {
            Votes  v = new Votes(rs.getInt(1),rs.getDouble(2) ,rs.getDouble(3),rs.getInt(4)); 
              votelist.add(v);
          }         
        }catch(SQLException ex){
        }
        return votelist;
    }

    public int getArtist(String artist){
        int x =0;
          Connection myconn =MyConnection.getInstance().getConnexion();
     String query ="Select idA FROM artist  where NomA = ?";

      
        try{
         
         PreparedStatement ste= myconn.prepareStatement(query); {
            
             ste.setString(1,artist);
            
               ResultSet rs = ste.executeQuery();
         
         while(rs.next()){
             x = rs.getInt(1);
         }
     
         
    }
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return x;
    }
    
    
public int  getclient(String email){
    int x=0;
    String sql="select id from user WHERE email = ? ;";
         
        try {
              PreparedStatement ste= myconn.prepareStatement(sql);
                ste.setString(1, email);
             ResultSet rs = ste.executeQuery();
               
                while(rs.next()){
                    x = rs.getInt(1);
                }
               
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
    return x;
}

}