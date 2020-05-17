package br.pro.hashi.ensino.desagil.aps.model;

import br.pro.hashi.ensino.desagil.aps.model.model.Emitter;

public class EmitterFalse implements Emitter {
    @Override
    public boolean read() {
        return false;
    }
}
