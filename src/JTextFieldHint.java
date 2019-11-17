import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * ajoute un text indicatif dans les JTextField quand ils sont vides et sans focus du curseur
 */
class JTextFieldHint extends JTextField implements FocusListener {

    /** texte indice à afficher */
    private final String hint;
    /** indique si l'incide doit être afficher ou non */
    private boolean showHint;

    public JTextFieldHint(final String hint) {
        super(hint);
        this.hint = hint;
        this.showHint = true;
        super.setForeground(Color.GRAY);
        super.addFocusListener(this);
    }

    /**
     * supprime l'indice si la case est vide et a le focus
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            super.setForeground(Color.BLACK);
            showHint = false;
        }
    }

    /**
     * affiche l'indice si la case est vide et perd le focus
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
            super.setForeground(Color.GRAY);
            showHint = true;
        }
    }

    /**
     * retourne une chaine vide si l'incide est affiché
     */
    @Override
    public String getText() {
        return showHint ? "" : super.getText();
    }
}
