package jeudelavie;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel {
    private Grille grille;
    private int point_size;

    public GamePanel(Grille g, int size) {
        grille = g;
        point_size = size;
    }


    /**
     * On dessine chaque point de la grille dans le component.  Si la
     * cellule est vivante, on affiche le point en noir, sinon on l'affiche
     * en blanc.
     */
    public void paintComponent(Graphics g) {
        for (int i = 0; i < grille.getSize(); ++i) {
            for (int j = 0; j < grille.getSize(); ++j) {
                if (grille.isAlive(i, j))
                    g.setColor(Color.BLACK);
                else
                    g.setColor(Color.WHITE);
                g.fillOval(i*point_size, j*point_size, point_size, point_size);
            }
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 150, 10);
        g.setColor(Color.RED);
        g.drawString("Génération: " + grille.getGeneration(), 1, 10); // Poor man's string conversion
    }
}
