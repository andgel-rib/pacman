/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;

/**
 *
 * @author freder
 */
public abstract class Entite implements Runnable {

    protected Jeu jeu;
    protected Point position;
    protected final boolean mouvable;
    
    public Entite(Jeu jeu, boolean mouvable, Point position) {
        this.jeu = jeu;
        this.mouvable = mouvable;
        this.position = position;
    }
    
    public Entite(Jeu jeu, Point position) {
        this.jeu = jeu;
        this.mouvable = false;
        this.position = position;
    }
    
    public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public boolean getMouvable() {
		return this.mouvable;
	}
	
	protected void seDeplacer() {
	}

	@Override
    public void run() {
		if(this.mouvable) {
			this.seDeplacer();
		}
    }

}
