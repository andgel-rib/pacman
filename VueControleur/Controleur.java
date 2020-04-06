package VueControleur;

import java.util.Observable;
import java.util.Observer;

import modele.Direction;
import modele.Entite;
import modele.Jeu;


public class Controleur implements Observer {
	
	  private Vue vue;
	  
	  private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

	  public Controleur(Jeu jeu) {
		  this.jeu = jeu;
		  
		  this.vue = new Vue(this.jeu.SIZE_X,this.jeu.SIZE_Y,this);
		  
	      this.vue.setVisible(true);
	  }
	  
	  public void deplacerPacMan(Direction d) {
		  this.jeu.getPacman().changerDirection(d);
	  }
	  
	  public Entite getEntite(int x,int y) {
		  return this.jeu.getEntiteAvecPosition(x, y);
	  }

	@Override
	public void update(Observable o, Object arg) {
		this.vue.mettreAJourAffichage();	
	}		
}
