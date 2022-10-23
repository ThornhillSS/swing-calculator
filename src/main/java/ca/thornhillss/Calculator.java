package ca.thornhillss;

import ca.thornhillss.model.InputModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator extends JPanel {
    private InputModel model;
    private final String[] buttons = new String[]{
            "123+",
            "456-",
            "789*",
            "0.E/"
    };
    private final String buttonStr = "123+456-789*0.E/";
    public Calculator(){
        model = new InputModel();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        // add the display
        add(new Display(model));

        // controls
        JPanel row = new JPanel();
        row.setLayout(new FlowLayout());

        // add the AC button
        row.add(makeCalculatorButton("AC", ()->{
            model.setInput("");
        }));
        // add the DEL button
        row.add(makeCalculatorButton("DEL", ()->{
            String str = model.getInput();
            if(str.length() != 0){
                model.setInput(model.getInput().substring(0, str.length() - 1));
            }
        }));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        add(row);

        // add the buttons
        /**
         * 1 2 3 +
         * 4 5 6 -
         * 7 8 9 *
         * 0 . E /
         */
        JPanel keyPad = new JPanel();
        keyPad.setLayout(new GridLayout(4, 4));
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                String action = buttons[i].substring(j, j + 1);
                keyPad.add(makeCalculatorButton(action, () -> {
                    model.setInput(model.getInput() + action);
                }));
            }
        }
        add(keyPad);

        // listen to key presses
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(e -> {
                    if(e.getID() == KeyEvent.KEY_TYPED){
                        keyTyped(e);
                    }
                    return true;
                });
    }

    private JButton makeCalculatorButton(String label, Runnable action){
        JButton button = new JButton(label);
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 20));
        button.setBounds(0, 0, 20, 20);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        button.setHorizontalAlignment(JButton.CENTER);
        return button;
    }
    public void keyTyped(KeyEvent e) {
        if(buttonStr.indexOf(e.getKeyChar()) != -1){
            model.setInput(model.getInput() + e.getKeyChar());
        }
        else {
            String str = model.getInput();
            if(e.getKeyChar() == '\b'){
                // DEL Button
                if(str.length() != 0){
                    model.setInput(model.getInput().substring(0, str.length() - 1));
                }
            }
            else if(e.getKeyChar() == '\u007F'){
                // AC Button
                model.setInput("");
            }else{
                model.setInput(str + e.getKeyChar());
            }
        }
    }
}
