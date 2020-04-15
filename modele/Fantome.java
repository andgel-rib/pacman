/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Random;

/**
 *
 * @author freder
 */
public class Fantome extends Entite {
	private Direction direction;
	
    //private Random r = new Random();

    public Fantome(Jeu jeu, Point p) {
        super(jeu,true,p);
        this.direction = Direction.droite;
    }

	@Override
	public void seDeplacer() {
		this.iaBroken();
		this.jeu.deplacerEntite(this, this.direction);
	}
	
	/*private void PacManHuntingMode() { // on va essayer de chasser pac man en choisissant une direction qui nous rapproche de lui
		Point manPosition = this.jeu.getPacman().getPosition();
		HashSet<Direction> availableDirections = this.jeu.getAvailableDirections(this.position);
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
	}*/
	
	public void iaBroken() {
		System.out.println("NEW TICK");
		HashSet<Direction> availableDirections = this.jeu.getAvailableDirections(this.position);
		int minDist = 0;
		Direction direction = null;
		for(Direction d : availableDirections) {
			HashMap<Point,Integer> explored = new HashMap<Point,Integer>();
			explored.put(this.position,new Integer(0));
			if(direction == null)
				direction = d;
			int dirDist = this.explore(explored,this.jeu.calculerPointCible(this.position, d),1);
			if(dirDist != -1 && (minDist == 0 || dirDist < minDist)) {
				minDist = dirDist;
				direction = d;
			}
			System.out.print(d);
			System.out.println(" MinDist: " + minDist);
			System.out.println(" DirDist: " + dirDist);
			
		}
		this.direction = direction;
	}
	
	private int explore(HashMap<Point,Integer>explored, Point p, int distance) {
		if(p.equals(this.jeu.getPacman().getPosition()))
			return distance;
		explored.put(p,new Integer(distance));
		HashSet<Direction> availableDirections = this.jeu.getAvailableDirections(p);
		int minDist = -1;
		for(Direction d : availableDirections) {
			Point target = this.jeu.calculerPointCible(p, d);
			Integer distP = explored.get(target);
			if(distP == null) {
				int distDir = explore(explored,target,distance+1);
				if(distDir != -1 && (minDist == -1 || distDir < minDist))
					minDist = distDir;
			} else if(distP.intValue() > distance){
				explored.remove(this.jeu.calculerPointCible(p, d));
				int distDir = explore(explored, target,distance+1);
				if(distDir != -1 && (minDist == -1 || distDir < minDist))
					minDist = distDir;
			}	
			
		}
		return minDist;
	}

	public Direction getDirection() {
		return direction;
	}
}
