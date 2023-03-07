/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICES;
import java.sql.*;
import artP.Iartist;
import static com.mysql.cj.conf.PropertyKey.logger;
import Entities.artist;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Utils.MyConnection;

/**
 *
 * @author yessine
 */
public class artistServ implements Iartist{
Connection mycon = MyConnection.getInstance().getConnexion();
   @Override
   public void ajouterArtist(artist a) {
    if (a.getNom_Ar()==null || a.getPrenom_Ar()==null || a.getNom_Ar().isEmpty() || a.getPrenom_Ar().isEmpty()) {
        System.out.println("Artist name or first name should not be empty");
        JOptionPane.showMessageDialog(null , "Artist's name or surname Should not be left empty ");
        return;
    }

    String selectSql = "SELECT * FROM artist WHERE nomA = ? AND prenom_ar = ?";    ///, photo_Ar
    String insertSql = "INSERT INTO artist (nomA, prenom_ar, date_nesAr, detail_Ar, genre_Ar, photo_Ar) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement selectStatement = mycon.prepareStatement(selectSql);
         PreparedStatement insertStatement = mycon.prepareStatement(insertSql)) {

        selectStatement.setString(1, a.getNom_Ar());
        selectStatement.setString(2, a.getPrenom_Ar());

        try (ResultSet rs = selectStatement.executeQuery()) {
            if (rs.next()) {
                System.out.println("Artist already exists in the database");
                JOptionPane.showMessageDialog(null , "already exist ");
            } else {
                insertStatement.setString(1, a.getNom_Ar());
                insertStatement.setString(2, a.getPrenom_Ar());
                insertStatement.setObject(3, a.getDate_nesAr());
                insertStatement.setString(4, a.getDetail_Ar());
                insertStatement.setString(5, a.getGenre_Ar());
                //insertStatement.setString(6,"");
                insertStatement.setBlob(6, a.getPhoto_Ar());

                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Artist added with success!");
                    JOptionPane.showMessageDialog(null , "Artist added with success!");
                }
            }
        }

    } catch (SQLException ex) {
        System.out.println(ex);
    }
}
@Override 
    public void modifierArtist(artist a, String ar) {
         if (a.getNom_Ar()==null || a.getPrenom_Ar()==null || a.getNom_Ar().isEmpty() || a.getPrenom_Ar().isEmpty()) {
        System.out.println("equipements must be entered");
        JOptionPane.showMessageDialog(null , "Should not be empty while updating ");
        return;}
       String sql="UPDATE artist SET nomA = ?, prenom_Ar = ?, date_NesAr = ?, detail_Ar = ?, genre_AR= ?, photo_Ar= ? WHERE idA = ?;";
        try{
           PreparedStatement preparedStatement = mycon.prepareStatement(sql);
           preparedStatement.setString(1, a.getNom_Ar());
           preparedStatement.setString(2, a.getPrenom_Ar());
           preparedStatement.setObject(3, a.getDate_nesAr());
           preparedStatement.setString(4, a.getDetail_Ar());
           preparedStatement.setString(5, a.getGenre_Ar());
           preparedStatement.setBlob(6, a.getPhoto_Ar());
           preparedStatement.setString(7, ar);
           preparedStatement.executeUpdate();
           System.out.println("avec suc");
           JOptionPane.showMessageDialog(null , "update with success ");
           
       }
    
       catch(SQLException ex){
           System.out.println(ex);
       }
    
    }
@Override
    public void supprimerArtist(String ar) {
         String sql="delete  FROM artist WHERE idA = ?;";
         
        try {
            PreparedStatement ste=mycon.prepareStatement(sql);
                ste.setString(1, ar);
                ste.executeUpdate();
               
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
    }

    @Override
    public void afficherArtist() {
     
            String sql="select * from artist ";
        try {
     
    PreparedStatement ste=mycon.prepareStatement(sql);

      ResultSet myRs=ste.executeQuery();
     

      
      while (myRs.next()) {
        System.out.println("ID: " + myRs.getInt("id_Ar"));
        System.out.println("Nom_Artist: " + myRs.getString("Nom_Ar"));
        System.out.println("prenom_artist: " + myRs.getString("prenom_Ar"));
        System.out.println("date_nessance: " + myRs.getDate("date_NesAr"));
        System.out.println("Detail: " + myRs.getString("detail_Ar"));
        
            
        System.out.println("-------------------------------------");
    
      }
    } catch(SQLException ex){
         System.out.println(ex);
    }
        
    }
    
   @Override 
    public artist getArtist(int id) {
        
      try (
             PreparedStatement statement = mycon.prepareStatement("SELECT * FROM artist WHERE idA = ?")){
         
                    statement.setInt(1, id);
                        try(ResultSet resultSet = statement.executeQuery()){
                            if(resultSet.next()){
                                artist art = new artist();
                                art.setNom_Ar(resultSet.getString("nom_Ar"));
                                art.setPrenom_Ar(resultSet.getString("prenom_Ar"));
                                art.setDetail_Ar(resultSet.getString("detail_Ar"));
                                art.setGenre_Ar(resultSet.getString("genre_Ar"));
                                return art;
                            } else{ return null ;}
                            
                        }
                    
         
    
                 
                 
                 
                 
                 
                 } catch (SQLException ex) {
        Logger.getLogger(artistServ.class.getName()).log(Level.SEVERE, null, ex);
}
    return null;
    
    
    
    
}
}