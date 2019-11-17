import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Onglet de la question 1
 */
public class Q1 extends JPanel {
    /** console où afficher le resultat */
    private VueConsole console;

    /**
     * constructeur du JPanel
     */
    public Q1() {
        super();
        // creéation de la console
        console = new VueConsole("console");
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Liste des plats servis au cours de cette période", SwingConstants.CENTER);

        // création des inputs
        JPanel input = new JPanel(new GridLayout(1,4));
        JTextField dateDeb = new JTextFieldHint("dd/mm/yyyy");
        JTextField dateFin = new JTextFieldHint("dd/mm/yyyy");

        // ajouts des inputs
        input.add(new JLabel("Date début :", SwingConstants.RIGHT));
        input.add(dateDeb);
        input.add(new JLabel("Date fin :", SwingConstants.RIGHT));
        input.add(dateFin);

        // création du bouton d'execution
        JButton exe = new JButton("Executer");
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executer(dateDeb.getText(), dateFin.getText());
            }
        });

        // ajout dans l'interface
        top.add(titre, BorderLayout.NORTH);
        top.add(input, BorderLayout.SOUTH);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(exe, BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);
        add(console, BorderLayout.SOUTH);
    }

    /**
     * methode qui lance la requette SQL sur la base Oracle
     * @param dateDeb date de début
     * @param dateFin date de fin
     */
    private void executer(String dateDeb, String dateFin) {
        try {
            // recupère la connection
            Connection co = DB.getConnection();
            String req = "SELECT DISTINCT plat.numplat, libelle\n" +
                    "FROM commande\n" +
                    "       INNER JOIN contient ON commande.numcom = contient.numcom\n" +
                    "       INNER JOIN plat ON contient.numplat = plat.numplat\n" +
                    "WHERE datcom BETWEEN to_date(?, 'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy')\n" +
                    "ORDER BY numplat";
            // créer la requete préparé
            PreparedStatement ps = co.prepareStatement(req);
            // ajoute les parametres
            ps.setString(1, dateDeb); // 10/09/2016
            ps.setString(2, dateFin); // 11/09/2016
            // execute la requete et recupère le resultat
            ResultSet rs = ps.executeQuery();
            String res = "NumPlat   Libelle\n";
            // lit le resultat ligne par ligne
            while (rs.next()) {
                res += rs.getInt(1) + " | " + rs.getString(2) + "\n";
            }
            // fermuture du resultat
            rs.close();
            // fermuture de la requete
            ps.close();
            // affichage dans la console
            console.setText(res.replaceFirst("\\s++$", "")); // supprime le white space à la fin
        } catch (SQLDataException e) {
            console.setText("Erreur : paramètres non valides");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
