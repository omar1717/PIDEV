/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class journaliste {
      private int id_journaliste;
    private String nom;
    private String prenom;
    private int nbr_article;
    private int avertissement;
    private String mail;
    private String mdp;

    public journaliste() {
    }

    public journaliste(int id_journaliste, String nom,String prenom ,int nbr_article, int avertissement, String mail, String mdp) {
        this.id_journaliste = id_journaliste;
        this.nom = nom;
        this.prenom = prenom;
        this.nbr_article = nbr_article;
        this.avertissement = avertissement;
        this.mail = mail;
        this.mdp = mdp;
    }

    public int getId_journaliste() {
        return id_journaliste;
    }
    public String getMail() {
        return mail;
    }
    public String getMdp() {
        return mdp;
    }

        public void setMail(String mail) {
        this.mail = mail;
    }
        
       public void setMdp(String mdp) {
        this.mdp = mdp;
    } 
        
    public void setId_journaliste(int id_journaliste) {
        this.id_journaliste = id_journaliste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNbr_article() {
        return nbr_article;
    }

    public void setNbr_article(int nbr_article) {
        this.nbr_article = nbr_article;
    }
    public int getAvertissement() {
        return avertissement;
    }

    public void setAvertissement(int avertissement) {
        this.avertissement = avertissement;
    }
    

    @Override
    public String toString() {
        return "Personne{" + "id_journaliste=" + id_journaliste + ", nom=" + nom + ", prenom=" + prenom + ", nbr_article=" + nbr_article + ", avertissement=" + avertissement + '}';
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
        final journaliste other = (journaliste) obj;
        return this.id_journaliste == other.id_journaliste;
    }
}
