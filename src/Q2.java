import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Q2 extends JPanel{
    private VueConsole console;

    public Q2(VueConsole vc) {
        super();
        console = vc;
        setLayout(new BorderLayout());
        JLabel titre = new JLabel("Liste des plats jamais servi au cours de cette période", SwingConstants.CENTER);
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

        add(input, BorderLayout.CENTER);
        add(exe, BorderLayout.SOUTH);
    }

    public void executer(String dateDeb, String dateFin) {
        try {
            Connection co = DB.getConnection();
            Statement stmt = co.createStatement();
            String req = "SELECT numplat, libelle\n" +
                    "FROM plat\n" +
                    "WHERE numplat NOT IN (SELECT DISTINCT plat.numplat\n" +
                    "                      FROM plat\n" +
                    "                               INNER JOIN contient ON plat.numplat = contient.numplat\n" +
                    "                               INNER JOIN commande ON contient.numcom = commande.numcom\n" +
                    "                      WHERE datcom BETWEEN to_date(?, 'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy'))\n";
            PreparedStatement ps = co.prepareStatement(req);
            ps.setString(1, dateDeb); // 10/09/2016
            ps.setString(2, dateFin); // 11/09/2016
            ResultSet rs = ps.executeQuery();
            String res = "NumPlat   Libelle\n";
            while (rs.next()) {
                res += rs.getInt(1) + " | " + rs.getString(2) + "\n";
            }
            rs.close();
            stmt.close();
            console.setText(res.replaceFirst("\\s++$", ""));
        } catch (SQLDataException e) {
            console.setText("Erreur : paramètres non valides");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
