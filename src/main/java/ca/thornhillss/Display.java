package ca.thornhillss;

import ca.thornhillss.model.InputModel;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private InputModel model;

    public Display(InputModel model) {
        this.model = model;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JTextArea enteredText = new JTextArea();
        enteredText.setEditable(false);
        enteredText.setFont(new Font(enteredText.getFont().getName(), Font.ITALIC, 21));
        JTextArea resultText = new JTextArea();
        resultText.setEditable(false);
        resultText.setFont(new Font(resultText.getFont().getName(), Font.PLAIN, 30));
        add(enteredText);
        add(resultText);
        model.getSupport().addPropertyChangeListener((event) -> {
            enteredText.setText(model.getInput());
            resultText.setText(model.evaluate());
            revalidate();
            repaint();
        });
        validate();
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    }
}
