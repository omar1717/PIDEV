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
public class article {
     public int id;
    private int id_journ;
    private String sujet;
    private String etat;

    public article() {
    }

    public article(int id, int id_journ, String sujet, String etat) {
        this.id = id;
        this.id_journ = id_journ;
        this.sujet = sujet;
        this.etat = etat;
    }
    
    public article(int id_journ, String sujet, String etat) {
        this.id_journ = id_journ;
        this.sujet = sujet;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_journ() {
        return id_journ;
    }

    public void setId_journ(int id_journ) {
        this.id_journ = id_journ;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "article{" + "id=" + id + ", id_journ=" + id_journ + ", sujet=" + sujet + ", etat=" + etat + '}';
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
        final article other = (article) obj;
        return this.id == other.id;
    }
    
}
