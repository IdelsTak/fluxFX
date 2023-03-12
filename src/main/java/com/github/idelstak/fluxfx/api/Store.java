package com.github.idelstak.fluxfx.api;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

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
        Platform.runLater(() -> views.forEach(view -> view.update(state)));
    }

    public abstract void onAction(Action action);
}
