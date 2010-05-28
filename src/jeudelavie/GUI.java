
package jeudelavie;

/**
 *
 * Executable pour la visualisation de la Jeu de vie.
 * (Devoir 1, IFT1025 &eacute;t&eacute; 2010)
 *
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;
import java.awt.Point;

public class GUI
{
    /**
     * Taille d'un point pour la visualisation de la grille.
     */
    private static final int POINT_SIZE = 8;

    /**
     * Produit une grille al&eacute;atoire.
     *
     * @param taille une grille de <var>taille</var> rabg&eacute;es et colonnes
     * @param prob_alive probabilit&eacute; d'initialiser une cellule comme vivante
     * @return une grille avec des cellules vivantes choisies au hasard
     */
    private static Grille random(int taille, double prob_alive)
    {
        ArrayList<Point> cellules = new ArrayList<Point>();
        for (int row=0; row<taille; row++)
            for (int col=0; col<taille; col++)
                if (Math.random() <= prob_alive)
                    cellules.add(new Point(row,col));
        Grille G = new Grille(taille);
        G.initCells(cellules);
        return G;
    }

    /**
     * Initialise une grille d'un fichier.
     *
     * @param file_name nom du fichier
     * @return une grille sp&eacute;cifi&eacute;e dans le fichier
     */
    private static Grille readFile(String file_name)
    {
        ArrayList<Point> cellules = null;
        String ligne = null; // contient une ligne à la fois pendant la lecture
        Grille grille = null;
        try
        {
            BufferedReader BR = new BufferedReader(new FileReader(file_name)); // pour la lecture de R
            do
            {
                ligne = BR.readLine(); // lire une ligne
                if (ligne == null)
                    continue; // arrive a la derniere ligne (fin-de-fichier)
                if (grille == null)
                {
                    int taille = Integer.parseInt(ligne);
                    grille = new Grille(taille);
                    cellules = new ArrayList<Point>();
                } else
                {
                    String[] champs = ligne.split("\\s+"); // coupe par sequences d'espace blanc (tab, espace, ...)
                    int rangee = Integer.parseInt(champs[0]);
                    int colonne = Integer.parseInt(champs[1]);
                    Point c = new Point(rangee,colonne);
                    cellules.add(c);
                }
            } while (ligne != null); // avant d'arriver à la fin de la lecture
            BR.close();
        } catch (IOException E)
        { // erreur de lecture de fichier
            System.err.println("Erreur: "+E);
            E.printStackTrace(System.err);
            System.exit(2010);
        }
        grille.initCells(cellules);
        return grille;
    }

    /**
     * Affichage de l'interface graphique au jeu.
     *
     * @param G la grille du jeu
     */
    private static void showFrame(final Grille G)
    {

        // A IMPLEMENTER:
        // au lieu de JPanel, utiliser
        // la sous-classe qui affiche la grille

        final GamePanel grille_affichee = new GamePanel(G, POINT_SIZE);
        // initialiser l'objet de visualisation:
        // on doit utiliser le mot de clé <code>final</code> ici,
        // parce qu'il y a une référence dans la méthode
        // <code>actionPerformed</code> plus tard.
        int taille = G.getSize();

        // ici on doit assurer que le JFrame prend la taille souhaitée
        Dimension D = new Dimension(taille*POINT_SIZE, taille*POINT_SIZE);
        grille_affichee.setPreferredSize(D);
        // on définit le titre
        JFrame frame = new JFrame("Jeu de la vie");
        // l'exécution est finie quand la fenêtre est fermée
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // on place l'affichage de la grille
        frame.add(grille_affichee, BorderLayout.CENTER);
        JButton B = new JButton("Prochaine génération");
        B.addActionListener(new ActionListener()
        {
            // si l'usager presse le bouton...
            public void actionPerformed(ActionEvent ignored)
            {
                G.nextGeneration();
                grille_affichee.repaint();
            }
        });

        // on place le bouton en bas
        frame.add(B, BorderLayout.SOUTH);
        // faire calculer les dimensions du JFrame:
        // grille_affichee, comme il est au centre (BorderLayout.CENTER),
        // prend la dimension préferée (setPreferredSize);
        // le bouton prend sa dimension minimale parce qu'il est
        // en bas (BorderLayout.SOUTH)
        frame.pack();
        // montrer le JFrame
        frame.setVisible(true);
    }


    public static void main(String[] args)
    {

        if (args.length <2 || args.length>3) // mauvais nombre d'arguments
            throw new IllegalArgumentException("Arguments: -rnd n p ou -in fichier.");
        Grille G = null;
        if (args[0].equals("-rnd"))
        {
            if (args.length != 3)
                throw new IllegalArgumentException("Il faut appeler par -rnd n p");
            int n = Integer.parseInt(args[1]);
            double p = Double.parseDouble(args[2]);
            G = random(n, p);
        } else if (args[0].equals("-in"))
        {
            if (args.length != 2)
                throw new IllegalArgumentException("Il faut appeler par -in fichier");
            G = readFile(args[1]);
        } else
        {
            throw new IllegalArgumentException("Le premier argument doit etre -rnd ou -in");
        }

        showFrame(G);
    }
}
