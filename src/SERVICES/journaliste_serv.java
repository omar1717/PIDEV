/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICES;
import Entities.journaliste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.activation.MimetypesFileTypeMap;
import user.Login_journalisteController;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;



/**
 *
 * @author MSI
 */
public class journaliste_serv {
    Connection myconnexion =MyConnection.getInstance().getConnexion();
    // @Override
    public void ajouter_journaliste(journaliste j) {
        
    
   
        try {
            // Création d'une requête SQL pour insérer un nouveau event dans la table "event"
            
            PreparedStatement prepareStatement;
           String sql= "INSERT INTO journaliste VALUES (?, ? ,? , ?, ?)";
            prepareStatement = myconnexion.prepareStatement(sql);

            // Définition des valeurs pour les paramètres de la requête
            prepareStatement.setInt(1, j.getId_journaliste());
            prepareStatement.setString(2, j.getNom());
         
            prepareStatement.setString(3,j.getPrenom());
            prepareStatement.setInt(4,j.getNbr_article());
            prepareStatement.setInt(5,j.getAvertissement());

             


            // Exécution de la requête pour insérer l'event dans la table
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(journaliste_serv.class.getName()).log(Level.SEVERE, null, ex);
        }

      
    }

    // @Override
    public void modifier_journaliste(journaliste j) {
         
         try {
            // Création d'une requête SQL pour mettre à jour l'event existant dans la table "event"
            PreparedStatement preparedStatement = myconnexion.prepareStatement("UPDATE journaliste SET nom = ?, prenom = ?, nbr_article = ?, avertissement, WHERE id = ?");

            // Définition des valeurs pour les paramètres de la requête
            preparedStatement.setString(1,j.getNom());
            preparedStatement.setString(2, j.getPrenom());
         
            preparedStatement.setInt(3,j.getNbr_article());
            preparedStatement.setInt(4, j.getAvertissement());
           

            // Exécution de la requête pour mettre à jour l event dans la table
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(journaliste_serv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   // @Override
    public void supprimer_journaliste(int id_journaliste) {
        try {
            // Création d'une requête SQL pour supprimer un produit existant dans la table "journaliste"
            PreparedStatement preparedStatement = myconnexion.prepareStatement("DELETE FROM journaliste where id_journaliste = "+id_journaliste+"");


            // Exécution de la requête pour supprimer salle de la table
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(journaliste_serv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   // @Override
    public void afficher_journaliste() {
         try {


      // Créer un objet Statement
      Statement myStmt = myconnexion.createStatement();

      // Exécuter la requête SQL
      ResultSet myRs = myStmt.executeQuery("SELECT * FROM journaliste");

      // Afficher les informations de chaque enregistrement
      while (myRs.next()) {
        System.out.println("id_journaliste: " + myRs.getInt("id_journaliste"));
        System.out.println("nom: " + myRs.getString("nom"));
        System.out.println("prenom: " + myRs.getString("prenom"));
        System.out.println("nbr_article: " + myRs.getInt("nbr_article"));
        System.out.println("avertissement: " + myRs.getInt("avertissement"));
       
        System.out.println("-------------------------------------");
      }

      // Fermer la connexion
      myconnexion.close();
    } catch (SQLException exc) {
    }
    }

    public void ajouterJournaliste(journaliste j) throws SQLException {
    String requete = "INSERT INTO journaliste (nom, prenom, mail, motdepasse) VALUES (?, ?, ?, ?)";
    PreparedStatement pst = myconnexion.prepareStatement(requete);
    pst.setString(1, j.getNom());
    pst.setString(2, j.getPrenom());
    pst.setString(3, j.getMail());
    pst.setString(4, j.getMdp());
    pst.executeUpdate();
}
    
    public boolean checkEmailExist(String mail) throws SQLException {
    String requete = "SELECT * FROM journaliste WHERE mail=?";
    PreparedStatement pst = myconnexion.prepareStatement(requete);
    pst.setString(1, mail);
    ResultSet rs = pst.executeQuery();
    return rs.next();
}
    
    public ObservableList<journaliste> getarticlelist()
    {
        ObservableList<journaliste> jounaliste_liste = FXCollections.observableArrayList();

        String query ="Select * FROM journaliste  ";

        try 
        {
         PreparedStatement ste= myconnexion.prepareStatement(query);
           ResultSet rs = ste.executeQuery();

          while (rs.next())
          {
            journaliste  j = new journaliste(rs.getInt(1),rs.getString(2) ,rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)); 
              jounaliste_liste.add(j);
          }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return jounaliste_liste;
    }
    
    public journaliste getJournalisteFromSession() throws SQLException {
    Connection myconn = MyConnection.getInstance().getConnexion();
    int journalisteId = Login_journalisteController.getsession();

    String query = "SELECT * FROM journaliste WHERE id_journaliste = ?;";
    PreparedStatement preparedStatement = myconn.prepareStatement(query);
    preparedStatement.setInt(1, journalisteId);
    ResultSet resultSet = preparedStatement.executeQuery();

    journaliste journaliste = null;
    if (resultSet.next()) {
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        int nbr_article = resultSet.getInt("nbr_article");
        int avertissement = resultSet.getInt("avertissement");
        String mail = resultSet.getString("mail");
        String mdp = resultSet.getString("motdepasse");
        journaliste = new journaliste(journalisteId, nom, prenom, nbr_article, avertissement, mail, mdp);
    }

    return journaliste;
}
    
   /*  public static File uploadImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        return fileChooser.showOpenDialog(stage);
    }
*/
    
    public boolean uploadImage(File file) {
        // Vérifier si le fichier est bien une image
        String contentType = new MimetypesFileTypeMap().getContentType(file);
        if (!contentType.startsWith("image/")) {
            System.out.println("Le fichier n'est pas une image !");
            return false;
        }
        
        // Copier le fichier dans le dossier images
        Path destination = Paths.get("images", file.getName());
        try {
            Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    } 
    
  public void delete() throws SQLException {
    Connection myconn = MyConnection.getInstance().getConnexion();
    String query = "DELETE FROM journaliste WHERE id_journaliste = ?";
    journaliste j = new journaliste();
    int id_journaliste = j.getId_journaliste();
    PreparedStatement ste = myconn.prepareStatement(query);
    ste.setInt(1, id_journaliste);
    ste.executeUpdate();
}
  
  
public static boolean updateJournaliste(int id, String nom, String prenom, String motdepasse) throws SQLException {
    boolean result = false;
    if (motdepasse.length() >= 8) {
    Connection myconn = MyConnection.getInstance().getConnexion();
        String query = "UPDATE journaliste SET nom=?, prenom=?, motdepasse=? WHERE id_journaliste=?";
        try {
            PreparedStatement stm = myconn.prepareStatement(query);
            stm.setString(1, nom);
            stm.setString(2, prenom);
            stm.setString(3, motdepasse);
            stm.setInt(4, id);
            stm.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    return result;
}
public boolean validateAdmin(String mail , String mdp ) throws SQLException {
     String query ="Select * FROM journaliste WHERE mail = ? and motdepasse = ? and role = ? ;";
     try{
         PreparedStatement ste= myconnexion.prepareStatement(query); {
            ste.setString(1,mail);
            ste.setString(2, mdp);
            ste.setString(3, "Admin");

         ResultSet resultSet = ste.executeQuery();
          if (resultSet.next()) {

              JOptionPane.showMessageDialog(null , "Successfully Logged Admin ");
                return true;

            }
     }
     }catch (SQLException ex) {
            System.out.println(ex);

        }
        return false;
    }

public boolean validate(String email , String mdp ) throws SQLException {
     String query ="Select * FROM journaliste WHERE mail = ? and motdepasse = ? and Role = ? ;";
     try{
         PreparedStatement ste= myconnexion.prepareStatement(query); {
            ste.setString(1,email);
            ste.setString(2, mdp);
                 ste.setString(3, "Client");
         ResultSet resultSet = ste.executeQuery();
          if (resultSet.next()) {
              
              
                return true;
                 
            }
     }
     }catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return false;
    }

}
    

