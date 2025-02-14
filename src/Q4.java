import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Onglet de la question 4
 *
 * commentaire similaire à la question 1
 */
public class Q4 extends JPanel {
    private VueConsole console;

    public Q4() {
        super();
        console = new VueConsole("console");
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Chiffre d’affaire et nombre de commandes réalisés par chaque serveur au cours de cette période", SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        JPanel input = new JPanel(new GridLayout(1,4));
        JTextField dateDeb = new JTextFieldHint("dd/mm/yyyy");
        JTextField dateFin = new JTextFieldHint("dd/mm/yyyy");

        input.add(new JLabel("Date début :", SwingConstants.RIGHT));
        input.add(dateDeb);
        input.add(new JLabel("Date fin :", SwingConstants.RIGHT));
        input.add(dateFin);

        JButton exe = new JButton("Executer");
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executer(dateDeb.getText(), dateFin.getText());
            }
        });

        top.add(titre, BorderLayout.NORTH);
        top.add(input, BorderLayout.SOUTH);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(exe, BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);
        add(console, BorderLayout.SOUTH);
    }

    private void executer(String dateDeb, String dateFin) {
        try {
            Connection co = DB.getConnection();
            String req = "SELECT DISTINCT nomserv, sum(prixunit*quantite) AS chiffre, count(UNIQUE commande.numcom) AS nbcom\n" +
                    "FROM commande\n" +
                    "       INNER JOIN affecter ON commande.numtab = affecter.numtab\n" +
                    "       INNER JOIN contient ON commande.numcom = contient.numcom\n" +
                    "       INNER JOIN plat ON contient.numplat = plat.numplat\n" +
                    "       INNER JOIN serveur ON affecter.numserv = serveur.numserv\n" +
                    "WHERE datcom BETWEEN to_date(?, 'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy')\n" +
                    "  AND to_char(datcom, 'dd/mm/yyyy') = to_char(dataff, 'dd/mm/yyyy')\n" +
                    "GROUP BY nomserv\n" +
                    "ORDER BY chiffre DESC";
            PreparedStatement ps = co.prepareStatement(req);
            ps.setString(1, dateDeb); // 10/09/2016
            ps.setString(2, dateFin); // 11/09/2016
            ResultSet rs = ps.executeQuery();
            String res = "NomServ   Chiffre   NbCom\n";
            while (rs.next()) {
                res += rs.getString(1) + " | " + rs.getInt(2) + " | " + rs.getInt(3) + "\n";
            }
            rs.close();
            ps.close();
            console.setText(res.replaceFirst("\\s++$", ""));
        } catch (SQLDataException e) {
            console.setText("Erreur : paramètres non valides");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
