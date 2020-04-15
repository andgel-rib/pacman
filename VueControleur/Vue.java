package VueControleur;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction Pacman, etc.))
 *
 *
 */
public class Vue extends JFrame{

    private Controleur controleur;
	
    private int sizeX; // taille de la grille affichée
    private int sizeY;

    private ImageIcon icoPacMan; // icones affichées dans la grille
    private ImageIcon icoPacManAnime;
    private ImageIcon icoFantome;
    private ImageIcon icoCouloir;
    private ImageIcon icoPacgum;
    private ImageIcon icoWall;

    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associé à une icône, suivant ce qui est présent dans la partie modèle)


    public Vue(int sizeX, int sizeY,Controleur controleur) {
    	
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.controleur = controleur;

        chargerLesIcones();
        placerLesComposantsGraphiques();

        ajouterEcouteurClavier();

    }

    private void ajouterEcouteurClavier() {

        this.addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                
                switch(e.getKeyCode()) {  // on écoute les flèches de direction du clavier
                    case KeyEvent.VK_LEFT : controleur.deplacerPacMan(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : controleur.deplacerPacMan(Direction.droite); break;
                    case KeyEvent.VK_DOWN : controleur.deplacerPacMan(Direction.bas); break;
                    case KeyEvent.VK_UP : controleur.deplacerPacMan(Direction.haut); break;
                }
                
            }

        });

    }

    private void chargerLesIcones() {
        icoPacMan = chargerIcone("/Images/Pacman.png");
        icoPacManAnime = icoPacMan;
        icoCouloir = chargerIcone("/Images/Couloir.png");
        icoFantome = chargerIcone("/Images/Fantome.png");
        icoPacgum = chargerIcone("/Images/Pacgum.png");
        icoWall = chargerIcone("/Images/Wall.png");
    }

    private ImageIcon chargerIcone(String path) {
    	 URL imgURL = Vue.class.getResource(path);
         if (imgURL != null) {
             return new ImageIcon(imgURL);
         } else {
             System.err.println("Couldn't find file: " + path);
             return null;
         }
    }

    private void placerLesComposantsGraphiques() {

        setTitle("PacMan");
        setSize(18*this.sizeX, 19*this.sizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(this.sizeX, this.sizeY)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        grilleJLabels.setBackground(Color.BLACK);
        this.tabJLabel = new JLabel[this.sizeX][this.sizeY];
        

        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {

                JLabel jlab = new JLabel();
                this.tabJLabel[y][x] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
                
            }

        }

        add(grilleJLabels);

    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    public void mettreAJourAffichage() {

        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
            	Entite e = this.controleur.getEntite(x, y);
            	if(e instanceof Pacman)  this.tabJLabel[x][y].setIcon(icoPacManAnime);
                else if(e instanceof Fantome) this.tabJLabel[x][y].setIcon(icoFantome);
                else if(e instanceof Pacgum) this.tabJLabel[x][y].setIcon(icoPacgum);
                else if(e instanceof Wall) this.tabJLabel[x][y].setIcon(icoWall);
            	else this.tabJLabel[x][y].setIcon(icoCouloir);
            }
        }

    }

    public void turnPacmanView(Direction d) {
        int angle = 0;
        switch (d){
            case droite: angle = 0;break;
            case bas: angle = 90;break;
            case haut: angle = -90;break;
            case gauche: angle = -180;break;
        }

        icoPacManAnime = this.rotateIcon(this.icoPacMan, angle);
        this.mettreAJourAffichage();
    }

    private ImageIcon rotateIcon(ImageIcon ico, double angle) {
        int w = ico.getIconWidth();
        int h = ico.getIconHeight();
        BufferedImage img = new BufferedImage(h, ico.getIconWidth(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(ico.getImage(), at, null);
        g2.dispose();
        return new ImageIcon(img);
    }

    public void gameFinishedAlert(boolean won, int score){
        String message = "";
        if (won){
            message = "Vous avez gagné.";
        } else {
            message = "Vous avez perdu.";
        }
        message += " Score total : " + score;
        showMessageDialog(null, message, "Fin du jeu", INFORMATION_MESSAGE, null);
    }
}
