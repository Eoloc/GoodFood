import javax.swing.*;
import java.awt.*;

public class Q7 extends JPanel{

    public Q7() {
        super();
        setLayout(new BorderLayout());
        JLabel titre = new JLabel("Triggers", SwingConstants.CENTER);
        add(titre, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("Trigger 1 :");
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(l1);
        JTextArea t1 = new JTextArea("CREATE OR REPLACE TRIGGER auditer_maitre_hotel\n" +
                "  AFTER INSERT\n" +
                "  ON COMMANDE\n" +
                "  FOR EACH ROW\n" +
                "  --REFERENCING new as NEW\n" +
                "  --when ( MONTCOM / NBPERS < 15 )\n" +
                "DECLARE\n" +
                "  v_monCom COMMANDE.MONTCOM%TYPE;\n" +
                "BEGIN\n" +
                "  v_monCom := update_montCom(:NEW.NUMCOM);\n" +
                "  IF (v_monCom / :NEW.NBPERS < 15) THEN\n" +
                "    INSERT INTO AUDITER VALUES (:NEW.NUMCOM, :NEW.NUMTAB, :NEW.DATCOM, :NEW.NBPERS, :NEW.DATPAIE, v_monCom);\n" +
                "  END IF;\n" +
                "END;");
        t1.setEditable(false);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(t1);
        JLabel l2 = new JLabel("Trigger 2 :");
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(l2);
        JTextArea t2 = new JTextArea("CREATE OR REPLACE TRIGGER quantite_plat\n" +
                "  BEFORE INSERT\n" +
                "  ON CONTIENT\n" +
                "  FOR EACH ROW\n" +
                "DECLARE\n" +
                "  v_nbpers COMMande.nbpers%TYPE;\n" +
                "BEGIN\n" +
                "  SELECT nbpers INTO v_nbpers FROM COMMANDE WHERE :NEW.numcom = COMMANDE.NUMCOM;\n" +
                "  IF (:NEW.quantite > v_nbpers) THEN\n" +
                "    :NEW.quantite := v_nbpers;\n" +
                "  END IF;\n" +
                "END;");
        t2.setEditable(false);
        t2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(t2);
        add(p, BorderLayout.CENTER);
    }

}
