import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class JTextFieldHint extends JTextField implements FocusListener {

    private final String hint;
    private boolean showHint;

    public JTextFieldHint(final String hint) {
        super(hint);
        this.hint = hint;
        this.showHint = true;
        super.setForeground(Color.GRAY);
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            super.setForeground(Color.BLACK);
            showHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
            super.setForeground(Color.GRAY);
            showHint = true;
        }
    }

    @Override
    public String getText() {
        return showHint ? "" : super.getText();
    }
}
