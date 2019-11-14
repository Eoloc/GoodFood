package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele();
        Requete requete = new Requete(modele);
        ControleurBouton controleb = new ControleurBouton(modele);
        modele.addObserver(requete);
        requete.setPreferredSize(new Dimension(400,450));

        // JPanel au centre de l'IG contenant les 4 boutons
        JPanel panelBouton= new JPanel(new GridLayout(1,4));
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


        /*************************************
         * Construction de l'IG dans une JFrame
         *************************************/
        JFrame frame=new JFrame("Mastermind");
        frame.add(requete,BorderLayout.NORTH);
        frame.add(panelBouton,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
