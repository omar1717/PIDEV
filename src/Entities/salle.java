/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class salle {
    public String nbresalle;
  public String type_stuff;

  public String cin_stuff;

    public salle(String nbresalle, String type_stuff,  String cin_stuff) {
        this.nbresalle = nbresalle;
        this.type_stuff = type_stuff;
        
        this.cin_stuff = cin_stuff;
    }

    public salle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public salle(String nbre_salle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNbresalle() {
        return nbresalle;
    }

    public String getType_stuff() {
        return type_stuff;
    }

   

    public String getCin_stuff() {
        return cin_stuff;
    }

    public void setNbresalle(String nbresalle) {
        this.nbresalle = nbresalle;
    }

    public void setType_stuff(String type_stuff) {
        this.type_stuff = type_stuff;
    }

    
    public void setCin_stuff(String cin_stuff) {
        this.cin_stuff = cin_stuff;
    }
  
   @Override
public String toString() {
    return String.valueOf(nbresalle);
} 
}
