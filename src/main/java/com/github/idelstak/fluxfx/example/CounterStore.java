package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Action;
import com.github.idelstak.fluxfx.api.Store;

public class CounterStore extends Store {

    private CounterState state;

    public CounterStore(CounterState state) {
        this.state = state;
    }

    @Override
    public void onAction(Action action) {
        if (action instanceof CounterAction counterAction) {
            state = ModifiableCounterState.modify(state, counterAction);
        }

        emitChange(state);
    }

}
