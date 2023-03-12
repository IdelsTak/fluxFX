package com.github.idelstak.fluxfx.api;

import java.util.ArrayList;
import java.util.List;

public abstract class Store {

    private final List<View> views;

    public Store() {
        this.views = new ArrayList<>();
    }

    public void register(View view) {
        views.add(view);
    }

    public void unregister(View view) {
        views.remove(view);
    }

    protected void emitChange(State state) {
        for (View view : views) {
            view.update(state);
        }
    }

    public abstract void onAction(Action action);
}
