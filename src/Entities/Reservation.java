/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author MSI
 */
public class Reservation {

    private  int Id_Res;
    private  String Nom_Ev;
    private  double Prix_Res;
    private  Date Date_Res;
    private  int Nbr_Place;
    private String Type_Res;
   public Reservation(int Id_Res, String Nom_Ev, double Prix_Res, Date Date_Res, int Nbr_Place, String Type_Res) {
    this.Id_Res = Id_Res;
    this.Nom_Ev = Nom_Ev;
    this.Prix_Res = Prix_Res;
    this.Date_Res = Date_Res;
    this.Nbr_Place = Nbr_Place;
    this.Type_Res = Type_Res;
}

public int getId_Res() {
    return Id_Res;
}

public void setId_Res(int Id_Res) {
    this.Id_Res = Id_Res;
}

public String getNom_Ev() {
    return Nom_Ev;
}

public void setNom_Ev(String Nom_Ev) {
    this.Nom_Ev = Nom_Ev;
}

public double getPrix_Res() {
    return Prix_Res;
}

public void setPrix_Res(double Prix_Res) {
    this.Prix_Res = Prix_Res;
}

public Date getDate_Res() {
    return Date_Res;
}

public void setDate_Res(Date Date_Res) {
    this.Date_Res = Date_Res;
}

public int getNbr_Place() {
    return Nbr_Place;
}

public void setNbr_Place(int Nbr_Place) {
    this.Nbr_Place = Nbr_Place;
}

public String getType_Res() {
    return Type_Res;
}

public void setType_Res(String Type_Res) {
    this.Type_Res = Type_Res;
}

    

}