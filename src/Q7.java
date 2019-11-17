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
        JTextArea t1 = new JTextArea("CREATE OR REPLACE TRIGGER ENREGISTREMENTAUDITER\n" +
                "AFTER\n" +
                "INSERT ON COMMANDE\n" +
                "DECLARE\n" +
                "    v_nbeuro NUMBER(6, 2);\n" +
                "    v_numserv SERVEUR.NUMSERV%TYPE;\n" +
                "BEGIN\n" +
                "    SELECT SUM(PRIXUNIT * QUANTITE)\n" +
                "    INTO v_nbeuro\n" +
                "    FROM PLAT\n" +
                "        INNER JOIN CONTIENT ON PLAT.NUMPLAT = CONTIENT.NUMPLAT\n" +
                "    WHERE NUMCOM = :NEW.NUMCOM;\n" +
                "\n" +
                "    SELECT DISTINCT SERVEUR.NUMSERV\n" +
                "    INTO v_numserv\n" +
                "    FROM SERVEUR\n" +
                "        INNER JOIN AFFECTER ON SERVEUR.NUMSERV = AFFECTER.NUMSERV\n" +
                "    WHERE SERVEUR.GRADE = 'maitre d''hotel'\n" +
                "      AND AFFECTER.NUMTAB = :NEW.NUMTAB;\n" +
                "\n" +
                "    IF ((v_nbeuro / :NEW.NBPERS) < 15 AND v_numserv = 1) THEN\n" +
                "        INSERT INTO AUDITER VALUES (NEW.NUMCOM, NEW.NUMTAB, NEW.DATCOM, NEW.NBPERS, NEW.DATPAIE, v_nbeuro);\n" +
                "    END IF;\n" +
                "END;");
        t1.setEditable(false);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(t1);
        JLabel l2 = new JLabel("Trigger 2 :");
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(l2);
        JTextArea t2 = new JTextArea("CREATE OR REPLACE TRIGGER LIMITEQUANTITEPLAT\n" +
                "BEFORE\n" +
                "INSERT ON CONTIENT\n" +
                "DECLARE\n" +
                "\tv_nbperso COMMANDE.NBPERS%TYPE;\n" +
                "BEGIN\n" +
                "\tSELECT NBPERS\n" +
                "\tINTO v_nbperso\n" +
                "\tFROM COMMANDE\n" +
                "\tWHERE :NEW.NUMCOM = COMMANDE.NUMCOM;\n" +
                "\t\n" +
                "    IF (:NEW.QUANTITE > v_nbperso) THEN\n" +
                "    \t:NEW.QUANTITE := v_nbperso;\n" +
                "    END IF;\n" +
                "END;");
        t2.setEditable(false);
        t2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(t2);
        add(p, BorderLayout.CENTER);
    }

}
