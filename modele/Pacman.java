/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;


/**
 *
 * @author fred
 */
public class Pacman extends Entite {
	private Direction direction = Direction.droite;
	
    public Pacman(Jeu jeu, Point p) {
        super(jeu,true,p);
    }
    
    public void seDeplacer() {
    	if(this.direction != null);
    		this.jeu.deplacerEntite(this, this.direction);
    }

	public void changerDirection(Direction d) {
		if(d == null) return;
		this.direction = d;
	}

	public Direction getDirection() {
		return direction;
	}
}
