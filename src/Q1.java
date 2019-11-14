import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q1 {

    public Q1(VueConsole console) {
        try {
            Connection co = DB.getConnection();
            Statement stmt = co.createStatement();
            String req = "SELECT DISTINCT plat.numplat, libelle\n" +
                    "FROM commande\n" +
                    "       INNER JOIN contient ON commande.numcom = contient.numcom\n" +
                    "       INNER JOIN plat ON contient.numplat = plat.numplat\n" +
                    "WHERE datcom BETWEEN to_date('10/09/2016', 'dd/mm/yyyy') AND to_date('11/09/2016', 'dd/mm/yyyy')\n" +
                    "ORDER BY numplat";
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
