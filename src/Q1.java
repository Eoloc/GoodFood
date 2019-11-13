import java.sql.Connection;

public class Q1 {

    public Q1(VueConsole console) {
        Connection co = DB.getConnection();

        console.setText("requete 1");
    }
}
