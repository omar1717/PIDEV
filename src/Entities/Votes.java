/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author omar
 */
public class Votes {
   int id ;
  double Vote_Artiste;
  double Vote_Event;
  int id_cli;
  int idA;
  String idE;

    public Votes(){
    }
     public Votes(double Vote_Artiste ,double Vote_Event, int id_cli){
         this.Vote_Artiste=Vote_Artiste;
         this.Vote_Event=Vote_Event;
         this.id_cli=id_cli;
         
         
    }
public Votes(double Vote_Artiste ,double Vote_Event){
         this.Vote_Artiste=Vote_Artiste;
         this.Vote_Event=Vote_Event;
         
         
         
    }
 public Votes(int id ,double Vote_Artiste ,double Vote_Event, int id_cli){
     this.id=id;
         this.Vote_Artiste=Vote_Artiste;
         this.Vote_Event=Vote_Event;
         this.id_cli=id_cli;
         
         
    }
 public Votes( double Vote_Artiste ,double Vote_Event, int id_cli, int ida){
     
         this.Vote_Artiste=Vote_Artiste;
         this.Vote_Event=Vote_Event;
         this.id_cli=id_cli;
         this.idA=ida;
         
         
    }
 public Votes( double Vote_Artiste ,double Vote_Event, int id_cli, int ida, String idE){
     
         this.Vote_Artiste=Vote_Artiste;
         this.Vote_Event=Vote_Event;
         this.id_cli=id_cli;
         this.idA=ida;
         this.idE=idE;
         
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVoteA() {
        return Vote_Artiste;
    }

    public void setVoteA(double A) {
        this.Vote_Artiste = A;
    }

   public double getVoteE() {
        return Vote_Event;
    }

    public void setVoteE(double E) {
        this.Vote_Event = E;
    }
     public int getIdC() {
        return id_cli;
    }

    public void setIdC(int idc) {
        this.id_cli = idc;
    }
     public int getIdA() {
        return idA;
    }

    public void setIdA(int ida) {
        this.idA = ida;
    }
      public String getIdE() {
        return idE;
    }

    public void setIdE(String ide) {
        this.idE = ide;
    }
    

    @Override
    public String toString() {
        return "Vote {" + "id=" + id + ", Rating Artist=" + Vote_Artiste +", rating Event=" + Vote_Event+", User =" + id_cli ;
    }

    @Override
    public int hashCode() {
        int hash = 4;
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
        final Votes other = (Votes) obj;
        return this.id == other.id;
    }
    
    
}