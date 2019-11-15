import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Q6 extends JPanel {
    private VueConsole console;

    public Q6(VueConsole vc) {
        super();
        console = vc;
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Calcul du montant total de cette commande et mise à jour de la table Commande", SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        JPanel input = new JPanel(new GridLayout(1,4));
        JTextField commande = new JTextField();

        input.add(new JLabel("Commande n°", SwingConstants.RIGHT));
        input.add(commande);

        JButton exe = new JButton("Executer");
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executer(commande.getText());
            }
        });

        top.add(titre, BorderLayout.NORTH);
        top.add(input, BorderLayout.SOUTH);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(top, BorderLayout.NORTH);
        panel.add(exe, BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);
    }

    public void executer(String commande) {
        try {
            Connection co = DB.getConnection();
            Statement stmt = co.createStatement();
            // Calcul du montant total de la commande
            String req = "SELECT sum(prixunit * quantite) AS montant\n" +
                    "FROM contient INNER JOIN plat ON contient.numplat = plat.numplat\n" +
                    "WHERE numcom = ?\n" +
                    "GROUP BY numcom";
            PreparedStatement ps = co.prepareStatement(req);
            ps.setInt(1, Integer.parseInt(commande)); // 100
            ResultSet rs = ps.executeQuery();
            String res;
            double montant;
            if (rs.next()) {
                montant = rs.getDouble(1); // ameliroer si commmande existe pas
                res = "Montant\n" + montant + "\n";
            } else {
                console.setText("Commande n°" + commande + " introuvable");
                return;
            }
            rs.close();
            stmt.close();

            // Update montant
            stmt = co.createStatement();
            req = "UPDATE commande\n" +
                    "SET montcom = ?\n" +
                    "WHERE numcom = ?";
            ps = co.prepareStatement(req);
            ps.setDouble(1, montant);
            ps.setInt(2, Integer.parseInt(commande));
            int resUpdate = ps.executeUpdate();
            if (resUpdate < 1) {
                res += "Update fail";
            } else if (resUpdate == 1) {
                res += "Commmande mise à jour";
            }

            console.setText(res.replaceFirst("\\s++$", ""));
        } catch (SQLDataException e) {
            console.setText("Erreur : paramètres non valides");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
