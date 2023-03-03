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
import javax.swing.JOptionPane;
import user.LoginController;


public class userServices implements Iuser {

Connection myconn =MyConnection.getInstance().getConnexion();


@Override
    public void ajouterPersonne(user p) {
      Connection myconn =MyConnection.getInstance().getConnexion();
        try {
      
        
             
            String sql="insert into user(nom,prenom,age,email,mdp,role) values(?,?,?,?,?,?)"; 
            PreparedStatement ste= myconn.prepareStatement(sql);
            ste.setString(1,p.getNom());
            ste.setString(2,p.getPrenom());
            ste.setObject(3,p.getAge());
            
             ste.setString(4,p.getEmail());
              ste.setString(5,p.getMdp());
               ste.setString(6,"Client");
            
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null , "Added");
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


  
    public void modifierPersonne(user p) {
        String sql="UPDATE user SET nom = ?, prenom = ?, age = ?,Email = ?,mdp = ? WHERE id = ? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            
                 ste.setString(1,p.getNom());
            ste.setString(2,p.getPrenom());
            ste.setObject(3,p.getAge());
             ste.setString(4,p.getEmail());
              ste.setString(5,p.getMdp());
               ste.setInt(6,p.getId());
                ste.executeUpdate();
              
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
        public void supprimerUser() {
       
         
         
         String sql="delete FROM user WHERE id = ?;";
         
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setInt(1, LoginController.getsession());
                ste.executeUpdate();
               
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
    }

  
    
     public boolean validate(String email , String mdp ) throws SQLException {
     String query ="Select * FROM user WHERE email = ? and Mdp = ? and Role = ? ;";
     try{
         PreparedStatement ste= myconn.prepareStatement(query); {
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
      public boolean validateAdmin(String email , String mdp ) throws SQLException {
     String query ="Select * FROM user WHERE email = ? and Mdp = ? and Role = ? ;";
     try{
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setString(1,email);
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
     

 
     public ObservableList<user> getuserList()
    {
        ObservableList<user> userlist = FXCollections.observableArrayList();
        
        String query ="Select * FROM user  ";
        
        try 
        {
         PreparedStatement ste= myconn.prepareStatement(query);
           ResultSet rs = ste.executeQuery();
          
          while (rs.next())
          {
            user  u = new user(rs.getInt(1),rs.getString(2) ,rs.getString(3),rs.getDate(4).toLocalDate(),rs.getString(5),rs.getString(6),rs.getString(7)); 
              userlist.add(u);
          }         
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userlist;
    }
     
     
     public boolean updatepwd(String pwd) {
       boolean  result = false ;
           Connection myconn =MyConnection.getInstance().getConnexion();
         
        String sql="UPDATE user SET mdp = ? WHERE id = ? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            
                 
           
            ste.setString(1,pwd);
            ste.setInt(2,LoginController.getsession());
            ste.executeUpdate();
              

            
} catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
}
  public boolean validateEmail(String email  ) throws SQLException {
     String query ="Select * FROM user WHERE email = ? and Role = ? ;";
     try{
         PreparedStatement ste= myconn.prepareStatement(query); {
            ste.setString(1,email);
          
                 ste.setString(2, "Client");
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


    /*
@Override
        public List<user> afficherLP() {

         List<user> list=new ArrayList<user>();  
          String sql="select * from Personne ;";
        try{  
            
             PreparedStatement ste=myconn.prepareStatement(sql); 
        ResultSet rs =   ste.executeQuery();
           
               
            while(rs.next()){  
                user p=new user();  
                p.setId(rs.getInt(1));  
                p.setNom(rs.getString(2));  
                p.setPrenom(rs.getString(3));  
          
                
                list.add(p); 
    
     
            }  
    
          
        }catch (SQLException ex) {
            System.out.println(ex);
            
        }
        System.out.println(list.toString());
        
        return list;  
    }  
}  
    */
    
    
    



