package jeudelavie;

/**
 * Implementation d'une grille pour le Jeu de la vie (Devoir 1, IFT1025 ete 2010) 
 *
 * @author Vincent Foley-Bourgon - FOLV08078309 - foleybov@iro.umontreal.ca
 */

import java.util.ArrayList;
import java.awt.Point;

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
    
    public void setState(int row, int col, boolean is_alive)
    {
    	grille[row][col] = is_alive;
    }
    
    public boolean isAlive(int row, int col)
    {
    	return grille[row][col];
    }
    
    public int getGeneration()
    {
    	return generation;
    }
    
    public int getSize()
    {
    	return taille;
    }
    
    public void nextGeneration()
    {
    	boolean[][] nouvelleGrille = new boolean[taille][taille];
    	for (int i = 0; i < taille; ++i) {
    		for (int j = 0; j < taille; ++j) {
    			nouvelleGrille[i][j] = nextGeneration(i, j);
    		}
    	}
    	generation++;
    	grille = nouvelleGrille;
    }
    
    private boolean nextGeneration(int x, int y) {
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

    public void initCells(ArrayList<Point> cellules)
    {
    	for (Point c: cellules) {
    		setState(c.x, c.y, true);
    	}
    }
}
