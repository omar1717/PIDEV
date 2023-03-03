/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import java.sql.Date;
import java.time.LocalDate;
/**
 *
 * @author omar
 */
public class user {
   int id ;
   String Nom;
   String Prenom;
   LocalDate Date_Naissance;
   String  Email;
   String Mdp;
   String Role;
    
    public user(){
    }
     public user(String nom, String Prenom,LocalDate Age, String email, String mdp){
         this.Nom=nom;
         this.Prenom=Prenom;
         this.Date_Naissance=Age;
         this.Email=email;
         this.Mdp=mdp;
    }
     
      public user(String nom, String prenom, LocalDate Date_Naissance,String email) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Date_Naissance = Date_Naissance;
        this.Email=email;
        
    }

    public user(String nom, String prenom, LocalDate Date_Naissance,String email,String mdp,String role) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Date_Naissance = Date_Naissance;
        this.Email=email;
        this.Mdp=mdp;
        this.Role=role;
    }
    public user(int id,String nom, String prenom, LocalDate Date_Naissance,String email,String mdp,String role) {
        this.id=id;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Date_Naissance = Date_Naissance;
        this.Email=email;
        this.Mdp=mdp;
        this.Role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
    }

    public LocalDate getAge() {
        return Date_Naissance;
    }

    public void setAge(LocalDate Date_Naissance) {
        this.Date_Naissance = Date_Naissance;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String mdp) {
        this.Mdp = mdp;
    }
    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    @Override
    public String toString() {
        return "Personne {" + "id=" + id + ", nom=" + Nom + ", prenom=" + Prenom + ", age=" + Date_Naissance + ", Email=" + Email + ",Role=" + Role ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final user other = (user) obj;
        return this.id == other.id;
    }
    
    
}