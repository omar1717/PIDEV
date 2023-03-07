/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.image.ImageView;


/**
 *
 * @author LENOVO
 */
public class event {
    
    public String nom_event;
  public String type_event;

 public String nbre_salle;
 public Date date_deb;
 public Date date_fin;
 public int nbr_participants;
 //public ImageView photo;
 

    public event( String nom_event ,String type_event,  String nbre_salle, Date date_deb, Date date_fin  ,int nbr_participants) {
        this.nom_event = nom_event;
        this.type_event = type_event;
 
        this.nbre_salle = nbre_salle;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nbr_participants = nbr_participants;
        // this.photo = photo;
    }

    public event( String nom_event ,String type_event,  String nbre_salle, Date date_deb, Date date_fin  ) {
        this.nom_event = nom_event;
        this.type_event = type_event;
 
        this.nbre_salle = nbre_salle;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        
    }

    

   

   

   

    public String getNom_event() {
        return nom_event;
    }

    public String getType_event() {
        return type_event;
    }

   

    public String getNbre_salle() {
        return nbre_salle;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }


    public int getNbr_participants() {
        return nbr_participants;
    }

    public void setNbr_participants(int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }
    
   public void setNom_event(String id_event) {
        this.nom_event = id_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

  
    

    public void setNbre_salle(String nbre_salle) {
        this.nbre_salle = nbre_salle;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
 
 
     /*public void setPhoto(ImageView photo) {
        this.photo = photo;
    }*/
    public String toString() {
    return nom_event;
}
    

}
