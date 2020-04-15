/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.HashSet;

/** La classe Jeu a deux fonctions 
 *  (1) Gérer les aspects du jeu : condition de défaite, victoire, nombre de vies
 *  (2) Gérer les coordonnées des entités du monde : déplacements, collisions, perception des entités, ... 
 *
 *
 */
public class Jeu extends Observable implements Runnable {
	private int ticks = 0; // le temps qui passe
	
	private Map map;
    private int SIZE_X;
    private int SIZE_Y;

    private Pacman pm;
    private int score = 0;
    
    private Entite[][] grilleEntites; // permet de récupérer une entité à partir de ses coordonnées
    
    // TODO : ajouter les murs, couloir, PacGums, et adapter l'ensemble des fonctions (prévoir le raffraichissement également du côté de la vue)
    
    
    public Jeu(Map map) {
    	this.map = map;
    	int xMap = this.map.getSIZE_X();
    	int yMap = this.map.getSIZE_Y();
    	int max  = Math.max(xMap, yMap); // la map serra un carr�
    	this.SIZE_X = max;
    	this.SIZE_Y = max;
    	this.grilleEntites = new Entite[this.SIZE_X][this.SIZE_Y];
        initialisationDesEntites();
    }

    public Entite getEntiteAvecPosition(int x, int y) {
        if(this.contenuDansGrille(new Point(x,y)))
            return this.grilleEntites[x][y];
        else
            return null;
    }

    public void setEntiteAvecPosition(Entite e, int x, int y) {
        this.grilleEntites[x][y] = e;
    }

    public int getScore() {
        return score;
    }

    public Pacman getPacman() {
        return pm;
    }
    
    private void initialisationDesEntites() {
        for(int x = 0; x < this.SIZE_X; x++) { // placer pacgum partout
            for(int y = 0; y < this.SIZE_Y; y++) {
                Pacgum pg = new Pacgum(this, new Point(x, y));
                this.grilleEntites[x][y] = pg;
            }
        };

        for(Point p : this.map.getExeptionsVides()) {
            if(this.contenuDansGrille(p)) {
                this.grilleEntites[p.x][p.y] = null;
            }
        }

        for(Point p : this.map.getWalls()) {
            if(this.contenuDansGrille(p)) {
                this.grilleEntites[p.x][p.y] = new Wall(this,p);
            }
        }
        
        for(int x = this.map.SIZE_X; x < this.SIZE_X; x++) {
        	for(int y = 0; y < this.SIZE_Y; y++) {
        		this.grilleEntites[x][y] = new Wall(this,new Point(x,y));
        	}
        }
        for(int y = this.map.SIZE_Y; y < this.SIZE_Y; y++) {
        	for(int x = 0; x < this.SIZE_Y; x++) {
        		this.grilleEntites[x][y] = new Wall(this,new Point(x,y));
        	}
        }

    	
        pm = new Pacman(this,new Point(12,17));
        this.grilleEntites[12][17] = pm;

        for (Point spawnFantome: this.map.getSpawnFantomes()){
            Fantome f = new Fantome(this,spawnFantome);
            this.grilleEntites[spawnFantome.x][spawnFantome.y] = f;
        }
        /*Fantome f = new Fantome(this,new Point(1,1));
        this.grilleEntites[1][1] = f;*/
    }
    
    
	public HashSet<Direction> getAvailableDirections(Point position){
		HashSet<Direction> availableDirections = new HashSet<Direction>();
		for(Direction d : Direction.values()) {
			if(this.checkDirectionWithPosition(position, d))
			availableDirections.add(d);
		}
		return availableDirections;
	}
	
	public HashSet<Point> getAvailablePoints(Point position){
		HashSet<Point> availablePoints = new HashSet<Point>();
		for(Direction d : Direction.values()) {
			if(this.checkDirectionWithPosition(position, d))
			availablePoints.add(this.calculerPointCible(position, d));
		}
		return availablePoints;
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
    public synchronized boolean deplacerEntite(Entite e, Direction d) {
    	Point positionOfPacMan = this.getPacman().getPosition();
        boolean retour;
        Point pCourant = e.getPosition();
        Point pCible = calculerPointCible(pCourant, d);
        HashMap<Point,Point> tpTrigger = this.map.getTpTrigger();
        if(tpTrigger.containsKey(pCible)) {
        	pCible = tpTrigger.get(pCible);
        }
        if(objetALaPosition(pCourant) instanceof Pacman && objetALaPosition(pCible) instanceof Pacgum){
            this.score += Pacgum.getValeur();
        }
        if (objetALaPosition(pCible) instanceof Fantome && e instanceof Pacman) {
            this.grilleEntites[positionOfPacMan.x][positionOfPacMan.y] = null;
            retour = true;
        }
        else if (contenuDansGrille(pCible) && (objetALaPosition(pCible) == null ||
                objetALaPosition(pCible) instanceof Pacgum ||
                objetALaPosition(pCible) instanceof Pacman)) { // Pour manger pacman / pour perdre
        	deplacerEntite(pCourant, pCible, e);
            retour = true;
        }
        else {
            retour = false;
        }
        return retour;
    }
    
    
    public Point calculerPointCible(Point pCourant, Direction d) {
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
    private HashSet<Entite> getMovableEntities(){
    	HashSet<Entite> listeEntites = new HashSet<Entite>();
        for(int x = 0; x < this.SIZE_X; x++) {
            for(int y = 0; y < this.SIZE_Y; y++) {
                if(this.grilleEntites[x][y] != null && this.grilleEntites[x][y].getMouvable()){
                    listeEntites.add(this.grilleEntites[x][y]);
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
    
	public int getTicks() {
		return this.ticks;
	}
	
	public boolean checkDirectionWithPosition(Point position,Direction direction) {
		Point target = this.calculerPointCible(position, direction);
		if(!this.contenuDansGrille(target) ||
			this.grilleEntites[target.x][target.y] instanceof Wall	) return false;
		return true;
	}

    @Override
    public void run() {
        while (!gameFinished()) {
        	this.ticks+=1;
            for (Entite entite: this.getMovableEntities())
            {
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

    public boolean gameFinished() {
        if (gameLost() || gameWin())
            return true;
        else
            return false;
    }

    public boolean gameWin()
    {
        for(int x = 0; x < this.SIZE_X; x++) {
            for(int y = 0; y < this.SIZE_Y; y++) {
                if(this.grilleEntites[x][y] instanceof Pacgum || (this.grilleEntites[x][y] instanceof Fantome && ((Fantome) this.grilleEntites[x][y]).getEntiteARestorer() instanceof Pacgum)){
                    return false;
                }
            }
        }
        //gagné
        return true;
    }

    private boolean gameLost()
    {
        for (Entite entite: getMovableEntities())
        {
            if (entite instanceof Pacman){
                return false;
            }
        }
        //perdu
        return true;
    }
    

	public int getSIZE_X() {
		return SIZE_X;
	}

	public int getSIZE_Y() {
		return SIZE_Y;
	}

}
