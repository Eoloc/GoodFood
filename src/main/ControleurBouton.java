package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurBouton extends JButton implements ActionListener {
    private Modele modele;
    public ControleurBouton(Modele mod) {
        this.modele = mod;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Masquer")){

        }

    }
}
