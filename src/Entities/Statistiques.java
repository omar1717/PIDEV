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
public class Statistiques {
    private String sujet;
    private int nombreArticles;
    private double pourcentage;

    public Statistiques(String sujet, int nombreArticles, double pourcentage) {
        this.sujet = sujet;
        this.nombreArticles = nombreArticles;
        this.pourcentage = pourcentage;
    }

    public String getSujet() {
        return sujet;
    }

    public int getNombreArticles() {
        return nombreArticles;
    }

    public double getPourcentage() {
        return pourcentage;
    }
}