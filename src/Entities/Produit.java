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
public class Produit {
    private int Id_Prod;
    private String Nom_Prod;
    private String Type_Prod;
    private int Quantite_Prod;
    private Double PrixU_Prod;
    private String Categorie_Prod;
    private String Nom_Event;

    public Produit(String string, String string0, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Produit(int aInt, String string, String string0, int aInt0, double aDouble, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNom_Event() {
        return Nom_Event;
    }

  
    public Produit(String string, String string0, String string1, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


    public void setNom_Event(String Nom_Event) {
        this.Nom_Event = Nom_Event;
    }

    public Produit(int Id_Prod, String Nom_Prod, String Type_Prod, int Quantite_Prod, Double PrixU_Prod, String Categorie_Prod, String Nom_Event) {
        this.Id_Prod = Id_Prod;
        this.Nom_Prod = Nom_Prod;
        this.Type_Prod = Type_Prod;
        this.Quantite_Prod = Quantite_Prod;
        this.PrixU_Prod = PrixU_Prod;
        this.Categorie_Prod = Categorie_Prod;
        this.Nom_Event = Nom_Event;
    }


    
   
    

    public int getId_Prod() {
        return Id_Prod;
    }

    public String getNom_Prod() {
        return Nom_Prod;
    }

    public String getType_Prod() {
        return Type_Prod;
    }

    public int getQuantite_Prod() {
        return Quantite_Prod;
    }

    public Double getPrixU_Prod() {
        return PrixU_Prod;
    }

    public String getCategorie_Prod() {
        return Categorie_Prod;
    }

    public void setId_Prod(int Id_Prod) {
        this.Id_Prod = Id_Prod;
    }

    public void setNom_Prod(String Nom_Prod) {
        this.Nom_Prod = Nom_Prod;
    }

    public void setType_Prod(String Type_Prod) {
        this.Type_Prod = Type_Prod;
    }

    public void setQuantite_Prod(int Quantite_Prod) {
        this.Quantite_Prod = Quantite_Prod;
    }

    public void setPrixU_Prod(Double PrixU_Prod) {
        this.PrixU_Prod = PrixU_Prod;
    }

    public void setCategorie_Prod(String Catégorie_Prod) {
        this.Categorie_Prod = Catégorie_Prod;
    }

   
    

    
    
    
}



