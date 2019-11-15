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
        //main.setPreferredSize(new Dimension(800,600));

        JPanel question1 = new Q1();
        JPanel question2 = new Q2();
        JPanel question3 = new Q3();
        JPanel question4 = new Q4();
        JPanel question5 = new Q5();
        JPanel question6 = new Q6();
        JPanel question7 = new Q7();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Question 1", question1);
        tabs.addTab("Question 2", question2);
        tabs.addTab("Question 3", question3);
        tabs.addTab("Question 4", question4);
        tabs.addTab("Question 5", question5);
        tabs.addTab("Question 6", question6);
        tabs.addTab("Question 7", question7);

        main.add(tabs, BorderLayout.NORTH);

        f.setContentPane(main);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
