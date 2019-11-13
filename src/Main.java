import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("GoodFood");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(800,600));

        JLabel output = new JLabel("test");

        panel.add(output, BorderLayout.SOUTH);

        f.setContentPane(panel);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
