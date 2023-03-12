package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.State;

public class CounterState implements State {

    private final int count;

    public static CounterState copy(CounterState state) {
        return from(state.getCount());
    }

    public static CounterState from(int count) {
        return new CounterState(count);
    }

    private CounterState(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
