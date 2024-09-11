package testeException.exemplo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * LogToFile class
 * This class is intended to be use with the default logging class of java
 * It save the log in an XML file  and display a friendly message to the user
 * @author Ibrabel <ibrabel@gmail.com>
 */
public class MainTestLogToFileOrigim {


    public static void main(String[] args) {
        /*
            Create simple frame for the example
        */
        JFrame myFrame = new JFrame();
        myFrame.setTitle("LogToFileExample");
        myFrame.setSize(300, 100);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        JButton severe = new JButton("severe");
        pan.add(severe);
        JButton warning = new JButton("warning");
        pan.add(warning);
        JButton info = new JButton("info");
        pan.add(info);

        /*
            Create an exception on click to use the LogToFile class
        */
        severe.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int j = 20, i = 0;
                try {
                    System.out.println(j/i);
                } catch (ArithmeticException ex) {
                    LogToFile.log(ex,"severe","You can't divide anything by zero");
                }

            }

        });

        warning.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int j = 20, i = 0;
                try {
                    System.out.println(j/i);
                } catch (ArithmeticException ex) {
                    LogToFile.log(ex,"warning","You can't divide anything by zero");
                }

            }

        });

        info.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                int j = 20, i = 0;
                try {
                    System.out.println(j/i);
                } catch (ArithmeticException ex) {
                    LogToFile.log(ex,"info","You can't divide anything by zero");
                }

            }

        });

        /*
            Add the JPanel to the JFrame and set the JFrame visible
        */
        myFrame.setContentPane(pan);
        myFrame.setVisible(true);
    }
}