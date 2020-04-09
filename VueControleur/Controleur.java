package VueControleur;

import java.util.Observable;
import java.util.Observer;

import modele.Direction;
import modele.Entite;
import modele.Jeu;


public class Controleur implements Observer {
	
	  private Vue vue;
	  
	  private Jeu jeu; // r�f�rence sur une classe de mod�le : permet d'acc�der aux donn�es du mod�le pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

	  public Controleur(Jeu jeu) {
		  this.jeu = jeu;
		  
		  this.vue = new Vue(this.jeu.getSIZE_X(),this.jeu.getSIZE_Y(),this);
		  
	      this.vue.setVisible(true);
	  }
	  
	  public void deplacerPacMan(Direction d) {
		  this.jeu.getPacman().changerDirection(d);
		  this.vue.turnPacmanView(d);
	  }
	  
	  public Entite getEntite(int x,int y) {
		  return this.jeu.getEntiteAvecPosition(x, y);
	  }

	@Override
	public void update(Observable o, Object arg) {
		this.vue.mettreAJourAffichage();	
	}		
}
