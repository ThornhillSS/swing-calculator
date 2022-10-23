package ca.thornhillss.model;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class InputModel {
    private String input;
    private ScriptEngine graalEngine;
    private final PropertyChangeSupport support;

    public InputModel() {
        this.graalEngine = new ScriptEngineManager().getEngineByName("graal.js");
        this.support = new PropertyChangeSupport(this);
    }

    public String getInput() {
        if(input == null) return "";
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        support.firePropertyChange(new PropertyChangeEvent(this, "input", null, null));
    }
    public String evaluate(){
        if(input == null) return "";
        if(input.equals("")) return "";
        try {
            return graalEngine.eval(input).toString();
        } catch (ScriptException e) {
            return "Error";
        }
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }
}
