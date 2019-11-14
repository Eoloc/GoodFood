import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q2 extends JPanel{
    private VueConsole console;
    private JTextField date1;
    private JTextField date2;

    public Q2(VueConsole vc) {
        super();
        console = vc;

        JLabel titre = new JLabel("Question 2");
        add(titre);

        JButton exe = new JButton("Executer");
        date1 = new JTextField(10);
        date2 = new JTextField(10);
        //date1.setPreferredSize(new Dimension(200, 30));
        add(date1);
        add(date2);
        exe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executer();
            }
        });
        add(exe);
    }

    public void executer() {
        try {
            String val1 = date1.getText();
            String val2 = date2.getText();
            Connection co = DB.getConnection();
            Statement stmt = co.createStatement();
            String req = "SELECT NUMPLAT, LIBELLE\n" +
                    "FROM PLAT\n" +
                    "WHERE NUMPLAT NOT IN (SELECT DISTINCT PLAT.NUMPLAT\n" +
                    "                      FROM PLAT\n" +
                    "                               INNER JOIN CONTIENT ON PLAT.NUMPLAT = CONTIENT.NUMPLAT\n" +
                    "                               INNER JOIN COMMANDE ON CONTIENT.NUMCOM = COMMANDE.NUMCOM\n" +
                    "                      WHERE DATCOM BETWEEN TO_DATE(?) AND TO_DATE(?));";
            // ajouter
            ResultSet rs = stmt.executeQuery(req);
            String res = "NumPlat   Libelle\n";
            while (rs.next()) {
                res += rs.getInt(1) + " | " + rs.getString(2) + "\n";
            }
            rs.close();
            stmt.close();
            console.setText(res.replaceFirst("\\s++$", ""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
