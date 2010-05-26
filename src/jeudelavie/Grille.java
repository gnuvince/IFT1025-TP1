package jeudelavie;

/**
 * Implementation d'une grille pour le Jeu de la vie (Devoir 1, IFT1025 ete 2010) 
 *
 * @author Vincent Foley-Bourgon - FOLV08078309 - foleybov@iro.umontreal.ca
 * @author Éric Thivierge
 */

import java.util.ArrayList;
import java.awt.Point;

import java.util.Arrays;

public class Grille 
{
	private boolean[][] grille;
	private int taille;
	private int generation;
	
    public Grille(int taille)
    {
    	generation = 0;
		this.taille = taille;
    	grille = new boolean[taille][taille];
    }
    
    
    /**
     * Assigner si une cellule est vivante ou pas
     * @param row la rangée d'une cellule
     * @param col la colonne d'une cellule
     * @param is_alive true/false si la cellule est vivante ou pas
     */
    public void setState(int row, int col, boolean is_alive)
    {
    	grille[row][col] = is_alive;
    }
    
    
    /**
     * Retourne si une cellule est vivante.
     * @param row la rangée de la cellule
     * @param col la colonne de la cellule
     * @return true/false si la cellule est vivante ou pas
     */
    public boolean isAlive(int row, int col)
    {
    	return grille[row][col];
    }

    
    /**
     * @return la génération actuelle
     */
    public int getGeneration()
    {
    	return generation;
    }
    
    
    /**
     * @return la taille de la grille
     */
    public int getSize()
    {
    	return taille;
    }
    
    
    /**
     * Calcule l'état des cellules de la grille actuelle et met la grille à jour
     */
    public void nextGeneration()
    {
    	boolean[][] nouvelleGrille = new boolean[taille][taille];
    	for (int i = 0; i < taille; ++i) {
    		for (int j = 0; j < taille; ++j) {
    			nouvelleGrille[i][j] = nextState(i, j);
    		}
    	}
    	generation++;
    	grille = nouvelleGrille;
    }
    
    
    /**
     * Retourne le prochain état d'une cellule donnée
     * @param x la rangée de la cellule
     * @param y la colonne de la cellule
     * @return true/false si la cellule est vivante ou pas.
     */
    private boolean nextState(int x, int y) {
    	int neighbors = countNeighbors(x, y);
    	if (neighbors <= 1)
    		return false;
    	else if (neighbors == 2)
    		return grille[x][y];
    	else if (neighbors == 3)
    		return true;
    	else
    		return false;
    }
    
    
    /**
     * Compte le nombre de cellules vivantes autour d'une cellule donnée.
     * @param x la rangée de la cellule
     * @param y la colonne de la cellule
     * @return le nombre de cellules vivantes
     */
    private int countNeighbors(int x, int y) {
    	int neighbors = 0;
    	for (int i = x-1; i <= x+1; ++i) {
    		for (int j = y-1; j <= y+1; ++j) {
    			// Ne pas se vérifier soi-même.
    			if (!(i == x && j == y)) {
    				try {
    					neighbors += grille[i][j] ? 1 : 0;
    				}
    				catch (ArrayIndexOutOfBoundsException e) {}
    			}
    		}
    	}
    	return neighbors;
    }

    
    /**
     * Allume les points spécifiés dans le paramètre cellules
     * 
     * @param cellules liste des points à allumer
     */
    public void initCells(ArrayList<Point> cellules)
    {
    	for (Point c: cellules) {
    		setState(c.x, c.y, true);
    	}
    	
    }
    
    
    public String showGrille() {
    	String s = "";
    	for (boolean[] a: grille) {
    		s += Arrays.toString(a);
    		s += "\n";
    	}
    	return s;
    }
    
    /**
     * Return a string representation of the game board
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (boolean[] row: grille) {
            for (boolean cell: row) {
                out.append(cell ? "*" : ".");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
