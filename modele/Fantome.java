/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author freder
 */
public class Fantome extends Entite {
	private Direction direction;
	
    private Random r = new Random();

    public Fantome(Jeu jeu, Point p) {
        super(jeu,true,p);
    }

	@Override
	public void seDeplacer() {
		switch (r.nextInt(2)) {
			case 0:
				this.direction = Direction.droite;
				break;
			case 1:
				this.direction = Direction.bas;
				break;
		}
		this.jeu.deplacerEntite(this, this.direction);
	}

}
