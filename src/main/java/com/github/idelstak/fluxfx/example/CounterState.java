package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.State;

public class CounterState implements State {

    private static CounterState state;
    private int count;

    private CounterState(int count) {
        this.count = count;
    }

    public static CounterState copy(CounterState state) {
        return from(state.getCount());
    }

    public static CounterState from(int count) {
        if (state == null) {
            state = new CounterState(count);
        } else {
            state.count = count;
        }

        return state;
    }

    public static CounterState increment() {
        return from(state.count + 1);
    }

    public static CounterState decrement() {
        return from(state.count - 1);
    }

    public int getCount() {
        return count;
    }

}
