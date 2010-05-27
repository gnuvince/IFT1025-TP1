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


    public void paintComponent(Graphics g) {
        for (int i = 0; i < grille.getSize(); ++i) {
            for (int j = 0; j < grille.getSize(); ++j) {
                if (grille.isAlive(i, j))
                    g.setColor(Color.BLACK);
                else
                    g.setColor(Color.WHITE);
                g.fillRect(i*point_size,
                           j*point_size,
                           point_size,
                           point_size);
            }
        }
    }
}
