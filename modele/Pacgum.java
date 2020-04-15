/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Point;

/**
 *
 * 
 */
public class Pacgum extends Entite {
    private static int valeur = 1;
    
    public Pacgum(Jeu jeu, Point p)
    {
        super(jeu, false, p);
    }

    public static int getValeur() {
        return valeur;
    }
}
