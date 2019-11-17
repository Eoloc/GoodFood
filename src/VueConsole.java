import javax.swing.*;

/**
 * affiche la console
 */
public class VueConsole extends JTextArea {

    public VueConsole() {
        super();
    }

    public VueConsole(String text) {
        super(text);
        setEditable(false);
    }

}
