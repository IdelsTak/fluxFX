package com.github.idelstak.fluxfx.api;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private final List<Store> stores;

    public Dispatcher() {
        this.stores = new ArrayList<>();
    }

    public void register(Store store) {
        stores.add(store);
    }

    public void dispatch(Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
