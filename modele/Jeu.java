/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/** La classe Jeu a deux fonctions 
 *  (1) Gérer les aspects du jeu : condition de défaite, victoire, nombre de vies
 *  (2) Gérer les coordonnées des entités du monde : déplacements, collisions, perception des entités, ... 
 *
 * @author freder
 */
public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 10;
    public static final int SIZE_Y = 10;

    private Pacman pm;
    
    private Entite[][] grilleEntites = new Entite[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées
    
    // TODO : ajouter les murs, couloir, PacGums, et adapter l'ensemble des fonctions (prévoir le raffraichissement également du côté de la vue)
    
    
    public Jeu() { 
        initialisationDesEntites();
    }
    
    public Entite getEntiteAvecPosition(int x, int y) {
    	if(this.contenuDansGrille(new Point(x,y)))
    		return this.grilleEntites[x][y];
    	else
    		return null;
    }
    
    public Pacman getPacman() {
        return pm;
    }
    
    private void initialisationDesEntites() {

        for (int i = 0; i<SIZE_X;i++){
            for (int y = 0; y<SIZE_Y;y++){
                Pacgum pg = new Pacgum(this,new Point(i, y));
                this.grilleEntites[i][y] = pg;
            }
        }
    	
        pm = new Pacman(this,new Point(2,0));
        this.grilleEntites[2][0] = pm;
        
        Fantome f = new Fantome(this,new Point(0,0));
        this.grilleEntites[0][0] = f;
        
    }
    
    
    /** Permet a une entité  de percevoir sont environnement proche et de définir sa strétégie de déplacement 
     * (fonctionalité utilisée dans choixDirection() de Fantôme)
     */
    public Object regarderDansLaDirection(Entite e, Direction d) {
        Point positionEntite = e.getPosition();
        return objetALaPosition(calculerPointCible(positionEntite, d));
    }
    
    /** Si le déclacement de l'entité est autorisé (pas de mur ou autre entité), il est réalisé
     */
    public boolean deplacerEntite(Entite e, Direction d) {
        boolean retour;
        Point pCourant = e.getPosition();
        Point pCible = calculerPointCible(pCourant, d);
        
        if (contenuDansGrille(pCible) && (objetALaPosition(pCible) == null || objetALaPosition(pCible) instanceof Pacgum)) { // a adapter (collisions murs, etc.)
            deplacerEntite(pCourant, pCible, e);
            retour = true;
        } else {
            retour = false;
        }
        return retour;
    }
    
    
    private Point calculerPointCible(Point pCourant, Direction d) {
        Point pCible = null;
        
        switch(d) {
            case haut: pCible       = new Point(pCourant.x, pCourant.y - 1); break;
            case bas : pCible       = new Point(pCourant.x, pCourant.y + 1); break;
            case gauche : pCible    = new Point(pCourant.x - 1, pCourant.y); break;
            case droite : pCible    = new Point(pCourant.x + 1, pCourant.y); break;

        }

        return pCible;
    }
    
    private void deplacerEntite(Point pCourant, Point pCible, Entite e) {
        this.grilleEntites[pCourant.x][pCourant.y] = null;
        this.grilleEntites[pCible.x][pCible.y] = e;
        e.setPosition(pCible);
    }
    
    /** Vérifie que p est contenu dans la grille
     */
    private boolean contenuDansGrille(Point p) {
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }
    
    private Object objetALaPosition(Point p) {
        Object retour = null;
        
        if (contenuDansGrille(p)) {
            retour = this.grilleEntites[p.x][p.y];
        }
        
        return retour;
    }

    /*
     * Obtenir un array de positions de toutes les entitées
     */
    private Entite[] getAllEntitiesPosition(){
        Entite[] listeEntites = new Entite[SIZE_X * SIZE_Y];
        int i = 0;
        for(int x = 0; x < this.SIZE_X; x++) {
            for(int y = 0; y < this.SIZE_Y; y++) {
                if(this.grilleEntites[x][y] != null){
                    listeEntites[i] = this.grilleEntites[x][y];
                }
            }
        }

        return listeEntites;
    }
    
    /**
     * Un processus est créé et lancé, celui-ci execute la fonction run()
     */
    public void start() {

        new Thread(this).start();

    }

    @Override
    public void run() {

        while (true) {

            for (Entite entite: this.getAllEntitiesPosition())
            {
                if (entite != null)
                    entite.run();
            }

            setChanged();
            notifyObservers(); // notification de l'observer pour le raffraichisssement graphique

            try {
                Thread.sleep(500); // pause de 0.5s
            } catch (InterruptedException ex) {
                Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
