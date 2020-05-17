package br.pro.hashi.ensino.desagil.aps.model;

import br.pro.hashi.ensino.desagil.aps.model.model.Emitter;

public class EmitterTrue implements Emitter {
    @Override
    public boolean read() {
        return true;
    }
}
