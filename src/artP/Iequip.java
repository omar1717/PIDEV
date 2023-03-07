/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artP;
import Entities.equipement;
/**
 *
 * @author yessine
 */
public interface Iequip {
      public void ajoutereq(equipement eq);
    public void modifiereq(equipement eq,String str);
    public void supprimereq(String eq);
    public void affichereq();
}
