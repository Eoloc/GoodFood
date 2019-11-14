package main;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Requete extends JPanel implements Observer {
    private Modele modele;

    public Requete(Modele mod)
    {
        super();
        this.modele = mod;
    }

    public void paintComponent(Graphics g) {
        // dessine le fond en gris
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }


    @Override
    public void update(Observable o, Object arg) {
        modele = (Modele)o;
        repaint();
    }
}
