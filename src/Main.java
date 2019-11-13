import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        VueConsole output = new VueConsole("console");

        JPanel boutons = new JPanel();
        JButton q1 = new JButton("Question 1");
        q1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Q1(output);
            }
        });
        boutons.add(q1);


        panel.add(boutons, BorderLayout.NORTH);
        panel.add(output, BorderLayout.SOUTH);

        f.setContentPane(panel);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
