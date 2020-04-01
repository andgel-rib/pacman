package VueControleur;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import modele.Direction;
import modele.Entite;
import modele.Fantome;
import modele.Jeu;
import modele.Pacman;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction Pacman, etc.))
 *
 * @author freder
 */
public class Vue extends JFrame{

    private Controleur controleur;
	
    private int sizeX; // taille de la grille affichée
    private int sizeY;

    private ImageIcon icoPacMan; // icones affichées dans la grille
    private ImageIcon icoFantome;
    private ImageIcon icoCouloir;

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
                    case KeyEvent.VK_LEFT : controleur.setPacManDirection(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : controleur.setPacManDirection(Direction.droite); break;
                    case KeyEvent.VK_DOWN : controleur.setPacManDirection(Direction.bas); break;
                    case KeyEvent.VK_UP : controleur.setPacManDirection(Direction.haut); break;
                }
                
            }

        });

    }

    private void chargerLesIcones() {
        icoPacMan = chargerIcone("Images/Pacman.png");
        icoCouloir = chargerIcone("Images/Couloir.png");
        icoFantome = chargerIcone("Images/Fantome.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {

        setTitle("PacMan");
        setSize(200, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JComponent grilleJLabels = new JPanel(new GridLayout(this.sizeX, this.sizeY)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

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
            	Entite e = (this.controleur.getEntite(x, y));
            	if(e instanceof Pacman)  this.tabJLabel[x][y].setIcon(icoPacMan);
            	else if(e instanceof Fantome) this.tabJLabel[x][y].setIcon(icoFantome);
            	else this.tabJLabel[x][y].setIcon(icoCouloir);
            }
        }

    }
}
