/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;
import java.util.HashSet;
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
        this.direction = Direction.droite;
    }

	@Override
	public void seDeplacer() {
		this.PacManHuntingMode();
	}
	
	private void PacManHuntingMode() { // on va essayer de chasser pac man en choisissant une direction qui nous rapproche de lui
		Point manPosition = this.jeu.getPacman().getPosition();
		HashSet<Direction> availableDirections = this.getAvailableDirections();
		int randomTired = this.r.nextInt(20);
		Point pTest = new Point(this.position.x - manPosition.x,this.position.y - manPosition.y); // on soustrait la position du fantôme à celle de pacman pour décider de la direction
		if(randomTired != 0 && pTest.x > 0 && availableDirections.contains(Direction.gauche) )
			this.direction = Direction.gauche;
		else if(randomTired != 0 && pTest.y < 0 && availableDirections.contains(Direction.bas))
			this.direction = Direction.bas;
		else if(randomTired != 0 && pTest.x < 0 && availableDirections.contains(Direction.droite))
			this.direction = Direction.droite;
		else if(randomTired != 0 && pTest.y > 0 && availableDirections.contains(Direction.haut))
			this.direction = Direction.haut;
		else {
			int random = this.r.nextInt(availableDirections.size());
			this.direction = (Direction)availableDirections.toArray()[random];
		}
		this.jeu.deplacerEntite(this, this.direction);
	}
	
	private HashSet<Direction> getAvailableDirections(){
		HashSet<Direction> availableDirections = new HashSet<Direction>();
		for(Direction d : Direction.values()) {
			if(this.jeu.checkDirectionWithPosition(this.position, d))
			availableDirections.add(d);
		}
		return availableDirections;
	}


	public Direction getDirection() {
		return direction;
	}
}
