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
public class Statistique {
    private String type_event;
    private int nombreevents;
    private double pourcentage;

    public Statistique(String type_event, int nombreevents, double pourcentage) {
        this.type_event = type_event;
        this.nombreevents = nombreevents;
        this.pourcentage = pourcentage;
    }

    public String getType_event() {
        return type_event;
    }

    public int getNombreevents() {
        return nombreevents;
    }

    public double getPourcentage() {
        return pourcentage;
    }
}   