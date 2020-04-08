
import java.awt.Point;

import VueControleur.*;
import modele.ClassicMap;
import modele.Jeu;
import modele.Map;
import modele.Pacman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author freder
 */
public class Main {
    public static void main(String[] args) {
        
        Jeu jeu = new Jeu(new ClassicMap());
        Map map = new ClassicMap();
        
        Controleur c = new Controleur(jeu);
        
        jeu.addObserver(c);
        
        jeu.start();
    }
}
