/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package SERVICES;
import Entities.article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.MyConnection;
import Entities.Statistiques;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import static jdk.nashorn.internal.runtime.Debug.id;
import user.Login_journalisteController;


/**
 *
 * @author MSI
 */
public class article_serv {
    Connection myconnexion =MyConnection.getInstance().getConnexion();
    // @Override
    public void ajouter_article(article a ) {
       try {
        // Vérifier que le sujet est valide (politique, sport ou actualité)
        String sujet = a.getSujet();
        if (!sujet.equals("politique") && !sujet.equals("sport") && !sujet.equals("actualité")) {
            throw new IllegalArgumentException("Le sujet doit être 'politique', 'sport' ou 'actualité'");
        }
        
        // Création d'une requête SQL pour insérer un nouveau article dans la table "articles"
        PreparedStatement prepareStatement;
        String sql = "INSERT INTO articles VALUES (?, ?, ?, ?)";
        prepareStatement = myconnexion.prepareStatement(sql);

        // Définition des valeurs pour les paramètres de la requête
        prepareStatement.setInt(1, a.getId());
        prepareStatement.setInt(2, a.getId_journ());
        prepareStatement.setString(3, sujet);
        prepareStatement.setString(4, a.getEtat());

        prepareStatement.executeUpdate();
        System.out.println("ajout avec succes");
    } catch (SQLException ex) {
        Logger.getLogger(article_serv.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("echec d'ajout");
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
    }
    //@Override
  public void modifier_article(article a) {
                    String sql ="UPDATE articles SET  id_journ= ?, sujet= ?, etat= ? WHERE id = ?";

        try {
            PreparedStatement ste=myconnexion.prepareStatement(sql);
            
                ste.setInt(1, a.getId_journ());
                ste.setString(2,a.getSujet());
                ste.setString(3,a.getEtat());
                ste.setInt(4, a.getId());
             
                ste.executeUpdate();
                  System.out.println(" article modifiée avec succés !");
              
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
  
   // @Override
    public void supprimer_article(article a) {
        try {
            // Création d'une requête SQL pour supprimer un produit existant dans la table "articles"
            PreparedStatement preparedStatement = myconnexion.prepareStatement("DELETE FROM articles where id = ?");
            preparedStatement.setInt(1, a.getId());
        

            // Exécution de la requête pour supprimer le produit de la table
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(article_serv.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     // @Override
    public void afficher_article() {
        try {


      // Créer un objet Statement
      Statement myStmt = myconnexion.createStatement();

      // Exécuter la requête SQL
      ResultSet myRs = myStmt.executeQuery("SELECT * FROM articles");

      // Afficher les informations de chaque enregistrement
      while (myRs.next()) {
          System.out.println("-----------------TABLE--------------------");
        System.out.println("id: " + myRs.getInt("id"));
        System.out.println("id_journ: " + myRs.getString("id_journ"));
        System.out.println("sujet: " + myRs.getString("sujet"));
        System.out.println("etat: " + myRs.getString("etat"));
        System.out.println("-------------------------------------");
      }

      // Fermer la connexion
      myconnexion.close();
    } catch (SQLException exc) {
    }


    }
    
      public ObservableList<article> afficher_articles() {
        ObservableList<article> liste_article = FXCollections.observableArrayList();
           try {
            String sql = "SELECT * FROM articles";
            Statement statement = myconnexion.createStatement();
            ResultSet result = statement.executeQuery(sql);
                    

 
    int count = 0;
    while (result.next()){
    int id = result.getInt("id");
    int id_journ = result.getInt("id_journ");
    String sujet = result.getString("sujet");
    String etat= result.getString("etat");

    
     article articless=new article(id,id_journ, sujet, etat);
    liste_article.add(articless);
               //String output = "boutique %d : %s | %s | %s | %s | %d | %d | %s | %s  ";
    //System.out.println(String.format(output, ++count, nom, email, lien,description,num_telephone,num_fixe,governerat,ville));
    
    
    }
    
            } catch (SQLException ex) {
            System.out.println(ex);
            }

    return liste_article;
        
    }

    public void ajouter_article(article_serv art) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      



    public ObservableList<article> afficher_article(String sujet_filtre) {
    ObservableList<article> liste_article = FXCollections.observableArrayList();
    FilteredList<article> listeFiltree = new FilteredList<>(liste_article, a -> a.getSujet().equals("en cours"));


    try {
        String sql = "SELECT * FROM articles";
        if (sujet_filtre != null && !sujet_filtre.isEmpty()) {
            sql += " WHERE sujet = ?";
        }
        PreparedStatement statement = myconnexion.prepareStatement(sql);
        if (sujet_filtre != null && !sujet_filtre.isEmpty()) {
            statement.setString(1, sujet_filtre);
        }
        ResultSet result = statement.executeQuery();
        while (result.next()){
            int id = result.getInt("id");
            int id_journ = result.getInt("id_journ");
            String sujet = result.getString("sujet");
            String etat= result.getString("etat");
            article articless=new article(id,id_journ, sujet, etat);
            liste_article.add(articless);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return liste_article;
}
    
    public List<Statistiques> getStatistiques() {
    List<Statistiques> Statistique = new ArrayList<>();
    try {
        PreparedStatement prepareStatement;
        String sql = "SELECT sujet, COUNT(*) AS nombre_articles, COUNT(*) / (SELECT COUNT(*) FROM articles) * 100 AS pourcentage FROM articles GROUP BY sujet";
        prepareStatement = myconnexion.prepareStatement(sql);
        ResultSet result = prepareStatement.executeQuery();
        while (result.next()) {
            String sujet = result.getString("sujet");
            int nombreArticles = result.getInt("nombre_articles");
            double pourcentage = result.getDouble("pourcentage");
            Statistiques statistique = new Statistiques(sujet, nombreArticles, pourcentage);
            Statistique.add(statistique);
        }
    } catch (SQLException ex) {
        Logger.getLogger(article_serv.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Statistique;
}

    
    public ObservableList<article> getarticlelist()
    {
        ObservableList<article> article_list = FXCollections.observableArrayList();

        String query ="Select * FROM articles  ";

        try 
        {
         PreparedStatement ste= myconnexion.prepareStatement(query);
           ResultSet rs = ste.executeQuery();

          while (rs.next())
          {
            article  a = new article(rs.getInt(1),rs.getInt(2) ,rs.getString(3),rs.getString(4)); 
              article_list.add(a);
          }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return article_list;
    }
    public static ObservableList<article> getArticlesByJournaliste() throws SQLException {
    Connection myConn = MyConnection.getInstance().getConnexion();
    ObservableList<article> articlesList = FXCollections.observableArrayList();
    String query = "SELECT sujet, etat,id FROM articles JOIN journaliste ON articles.id_journ = journaliste.id_journaliste WHERE journaliste.id_journaliste = ?;";
    PreparedStatement preparedStatement = myConn.prepareStatement(query);
    preparedStatement.setInt(1, Login_journalisteController.getsession());
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        article article = new article();
        article.setSujet(resultSet.getString(1));
        article.setEtat(resultSet.getString(2));
        article.setId(resultSet.getInt(3));
        articlesList.add(article);
    }
    return articlesList;
}

}