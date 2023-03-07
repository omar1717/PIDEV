/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artP;
import Entities.artist;
/**
 *
 * @author yessine
 */
public interface Iartist {
      public void ajouterArtist(artist a);
    public void modifierArtist(artist a, String ar);
    public void supprimerArtist(String ar);
    public void afficherArtist();
    public artist getArtist(int id);
}
