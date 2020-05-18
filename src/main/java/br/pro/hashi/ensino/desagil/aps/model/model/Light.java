package br.pro.hashi.ensino.desagil.aps.model.model;

import java.awt.*;

public class Light implements Receiver {
    private Color onColor;
    private Color offColor;
    private Emitter emitter;

    public Light(int onR, int onG, int onB, int offR, int offG, int  offB) {
        onColor = new Color(onR, onG, onB);
        emitter = null;
        offColor = new Color(offR, offG, offB);
    }

    public Color getColor() {
        if (emitter != null && emitter.read()) {
            return onColor;
        }
        return offColor;
    }

    public void setOnColor(Color color) { this.onColor = color; }
    public void setOffColor(Color color) { this.offColor = color; }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex != 0) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        this.emitter = emitter;
    }
}
