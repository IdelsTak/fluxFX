package com.github.idelstak.fluxfx.api;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private static final Dispatcher INSTANCE = new Dispatcher();
    private final List<Store> stores;

    private Dispatcher() {
        this.stores = new ArrayList<>();
    }

    public static void register(Store store) {
        INSTANCE.add(store);
    }
    
    public static void dispatch(Action action) {
        INSTANCE.dispatchForEachStore(action);
    }

    private void add(Store store) {
        stores.add(store);
    }

    private void dispatchForEachStore(Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
