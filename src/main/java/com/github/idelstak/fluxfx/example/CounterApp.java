package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Dispatcher;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CounterApp extends Application {

    private final Dispatcher dispatcher;
    private final CounterStore counterStore;

    public CounterApp() {
        dispatcher = new Dispatcher();
        counterStore = new CounterStore();
        // Add the store to the dispatcher
        // so that it can be called when
        // the dispatcher receives actions
        dispatcher.register(counterStore);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createView(), 200, 100);

        primaryStage.setTitle("Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createView() {
        var counterView = new CounterView(dispatcher);
        // Add the view to the store so that
        // it can be updated when changes occur
        counterStore.register(counterView);
        // Set the initial state of the view
        counterView.update(counterStore.getState());

        return counterView;
    }
}
