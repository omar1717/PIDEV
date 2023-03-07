/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import com.mysql.cj.jdbc.Blob;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author yessine
 */
public class artist {

   
   
     private int id_Ar;
    private String nom_Ar; 
    private String prenom_Ar;
    private LocalDate date_nesAr;
    private String detail_Ar;
    private String genre_Ar;
    private SerialBlob photo_Ar;
    private String dateStr;
    
    public artist(){}

   public artist(String nom_Ar, String prenom_Ar, LocalDate date_nesAr, String detail_Ar, String genre_Ar, SerialBlob photo_Ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.date_nesAr = date_nesAr;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        this.photo_Ar = photo_Ar;
    }

    public artist(int id ,String nom, String prenom, LocalDate date , String Desc , String  genre_Ar ) {
        this.id_Ar=id;
      this.nom_Ar = nom;
        this.prenom_Ar = prenom;
        this.date_nesAr = date;
        this.detail_Ar = Desc;
        this.genre_Ar = genre_Ar;
    }

    public artist(String nom_Ar, String prenom_Ar, LocalDate date_nesAr, String detail_Ar, String genre_Ar) {
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.date_nesAr = date_nesAr;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
    }

    public artist(String text, String text0, LocalDate value, String text1, String text2, artist er) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public artist(int id_Ar, String nom_Ar, String prenom_Ar, String detail_Ar, String genre_Ar,  String dateStr) {
        this.id_Ar = id_Ar;
        this.nom_Ar = nom_Ar;
        this.prenom_Ar = prenom_Ar;
        this.detail_Ar = detail_Ar;
        this.genre_Ar = genre_Ar;
        
        this.dateStr = dateStr;
    }

    
    
    
    
    
    
    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

   
 public void setImageBytes(byte[] imageBytes) throws SQLException {
    if (imageBytes == null) {
        this.photo_Ar = null;
    } else {
        this.photo_Ar = new SerialBlob(imageBytes);
    }
}

   public byte[] getImageBytes(ResultSet rs, String photo_Ar) throws SQLException, IOException {
    Blob blob = (Blob) rs.getBlob(photo_Ar);
    if (blob == null) {
        return null;
    }
    byte[] bytes = blob.getBytes(1, (int)   blob.length());
    blob.free();
    return bytes;
}
    
    
    public int getId_Ar() {
        return id_Ar;
    }

    public String getNom_Ar() {
        return nom_Ar;
    }

    public String getPrenom_Ar() {
        return prenom_Ar;
    }

    public LocalDate getDate_nesAr() {
        return date_nesAr;
    }

    public String getDetail_Ar() {
        return detail_Ar;
    }

    public String getGenre_Ar() {
        return genre_Ar;
    }

    public SerialBlob getPhoto_Ar() {
        return photo_Ar;
    }

    public void setNom_Ar(String nom_Ar) {
        this.nom_Ar = nom_Ar;
    }

    public void setPrenom_Ar(String prenom_Ar) {
        this.prenom_Ar = prenom_Ar;
    }

    public void setDate_nesAr(LocalDate date_nesAr) {
        this.date_nesAr = date_nesAr;
    }

    public void setDetail_Ar(String detail_Ar) {
        this.detail_Ar = detail_Ar;
    }

    public void setGenre_Ar(String genre_Ar) {
        this.genre_Ar = genre_Ar;
    }

    public void setPhoto_Ar(SerialBlob photo_Ar) {
        this.photo_Ar = photo_Ar;
    }
    

}
