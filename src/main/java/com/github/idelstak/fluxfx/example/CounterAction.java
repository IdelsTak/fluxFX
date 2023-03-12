package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Action;

public class CounterAction implements Action {

    public enum Type {
        INCREMENT, DECREMENT
    }

    private final Type type;

    public CounterAction(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
