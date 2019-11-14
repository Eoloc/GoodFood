import javax.swing.*;

public class VueConsole extends JTextArea {

    public VueConsole() {
        super();
    }

    public VueConsole(String text) {
        super(text);
        setEditable(false);
    }

}
