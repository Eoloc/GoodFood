import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Q3 extends JPanel {
    private VueConsole console;

    public Q3() {
        super();
        console = new VueConsole("console");
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new BorderLayout());
        JLabel titre = new JLabel("Liste des serveurs qui ont servi cette table au cours de cette période", SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        JPanel input = new JPanel(new GridLayout(1,6));
        JTextField dateDeb = new JTextFieldHint("dd/mm/yyyy");
        JTextField dateFin = new JTextFieldHint("dd/mm/yyyy");
        JTextField table = new JTextField();

        input.add(new JLabel("Table ID :", SwingConstants.RIGHT));
        input.add(table);
        input.add(new JLabel("Date début :", SwingConstants.RIGHT));
        input.add(dateDeb);
        input.add(new JLabel("Date fin :", SwingConstants.RIGHT));
        input.add(dateFin);

        JButton exe = new JButton("Executer");
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executer(table.getText(), dateDeb.getText(), dateFin.getText());
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

    public void executer(String table, String dateDeb, String dateFin) {
        try {
            Connection co = DB.getConnection();
            Statement stmt = co.createStatement();
            String req = "SELECT DISTINCT nomserv, datcom\n" +
                    "FROM commande\n" +
                    "       INNER JOIN affecter ON commande.numtab = affecter.numtab\n" +
                    "       INNER JOIN serveur ON affecter.numserv = serveur.numserv\n" +
                    "WHERE datcom BETWEEN to_date(?, 'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy')\n" +
                    "AND affecter.numtab = ?";
            PreparedStatement ps = co.prepareStatement(req);
            ps.setString(1, dateDeb); // 10/09/2016
            ps.setString(2, dateFin); // 11/09/2016
            ps.setInt(3, Integer.parseInt(table));
            ResultSet rs = ps.executeQuery();
            String res = "NomServ   DatCom\n";
            while (rs.next()) {
                res += rs.getString(1) + " | " + rs.getString(2) + "\n";
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
