package ca.thornhillss;

import javax.swing.*;

public class Main {
    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        // add the calculator to the frame
        Calculator calc = new Calculator();
        frame.getContentPane().add(calc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() ->
                createAndShowGUI()
        );
    }
}