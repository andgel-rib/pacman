/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


/**
 *
 * @author fred
 */
public class Pacgum extends Entite {

    public Pacgum(Jeu _jeu) {
        super(_jeu);
        d = Direction.none;

    }
    
    public void setDirection(Direction _d) {
        d = _d;
    }

    @Override
    public void choixDirection() {
        
    }

}
