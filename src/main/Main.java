package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
        ControleurBouton controleb = new ControleurBouton(modele);
        modele.addObserver(vue);
        grille.setPreferredSize(new Dimension(400,450));
        grille.addMouseListener(controleCase);


        // JPanel au centre de l'IG contenant les 4 boutons
        JPanel panelBouton= new JPanel(new GridLayout(2,2));
        ControleurBouton controleBouton = new ControleurBouton(modele);

        JButton jbv = new JButton("Valider");
        panelBouton.add(jbv);

        JButton jb = new JButton("Masquer");
        panelBouton.add(jb);
        jb.addActionListener(controleBouton);

        JButton btnExpert = new JButton("Expert");
        panelBouton.add(btnExpert);

        JButton btnDebutant = new JButton("Debutant");
        panelBouton.add(btnDebutant);


        // JPanel au sud de l'IG dans lequel se trouve l'affichage
        // des couleurs disponibles
        ControleurCouleur controleCouleur = new ControleurCouleur(modele);
        ChoixCoul choixCoul= new ChoixCoul(modele);
        choixCoul.addMouseListener(controleCouleur);

        /*************************************
         * Construction de l'IG dans une JFrame
         *************************************/
        JFrame frame=new JFrame("Mastermind");
        frame.add(grille,BorderLayout.NORTH);
        frame.add(panelBouton,BorderLayout.CENTER);
        frame.add(choixCoul, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
