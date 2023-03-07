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
public class facture {
    String Mail;
    String Nom_Achat;
    int Ref_Achete;
    Double PrixU_Achat;

    public facture(String Mail, String Nom_Achat, int Ref_Achete, Double PrixU_Achat) {
        this.Mail = Mail;
        this.Nom_Achat = Nom_Achat;
        this.Ref_Achete = Ref_Achete;
        this.PrixU_Achat = PrixU_Achat;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getMail() {
        return Mail;
    }

    public facture(String nomAchat, String Ref_Achete, double prixU_Achat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  

    public facture( String Nom_Achat, int Ref_Achete, Double PrixU_Achat) {
       
        this.Nom_Achat = Nom_Achat;
        this.Ref_Achete = Ref_Achete;
        this.PrixU_Achat = PrixU_Achat;
    }

    

    public String getNom_Achat() {
        return Nom_Achat;
    }

    public int getRef_Achete() {
        return Ref_Achete;
    }

    public Double getPrixU_Achat() {
        return PrixU_Achat;
    }

    

    public void setNom_Achat(String Nom_Achat) {
        this.Nom_Achat = Nom_Achat;
    }

    public void setRef_Achete(int Ref_Achete) {
        this.Ref_Achete = Ref_Achete;
    }

    public void setPrixU_Achat(Double PrixU_Achat) {
        this.PrixU_Achat = PrixU_Achat;
    }


    
    
    
}
