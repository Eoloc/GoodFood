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
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.setPreferredSize(new Dimension(800,600));

        VueConsole output = new VueConsole("console");

        JPanel question1 = new Q1(output);
        JPanel question2 = new Q2(output);


        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Question 1", question1);
        tabs.addTab("Question 2", question2);


        main.add(tabs, BorderLayout.NORTH);
        main.add(output, BorderLayout.SOUTH);

        f.setContentPane(main);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
