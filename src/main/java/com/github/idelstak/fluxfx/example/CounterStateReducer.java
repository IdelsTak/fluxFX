package com.github.idelstak.fluxfx.example;

import static com.github.idelstak.fluxfx.example.CounterAction.Type.DECREMENT;
import static com.github.idelstak.fluxfx.example.CounterAction.Type.INCREMENT;
import java.util.function.Function;

public class CounterStateReducer implements Function<CounterAction, CounterState> {

    private final CounterState state;

    public static CounterState reduce(CounterState state, CounterAction action) {
        return new CounterStateReducer(state).apply(action);
    }

    private CounterStateReducer(CounterState state) {
        this.state = state;
    }

    @Override
    public CounterState apply(CounterAction action) {
        return switch (action.getType()) {
            case INCREMENT ->
                CounterState.from(incremented(state));
            case DECREMENT ->
                CounterState.from(decremented(state));
            default ->
                CounterState.copy(state);
        };
    }

    private static int incremented(CounterState state) {
        return state.getCount() + 1;
    }

    private static int decremented(CounterState state) {
        return state.getCount() - 1;
    }
}
