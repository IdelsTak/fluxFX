package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Dispatcher;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CounterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        CounterState counterState = CounterState.from(0);
        CounterStore counterStore = new CounterStore(counterState);
        Dispatcher.register(counterStore);
        CounterView counterView = new CounterView();
        counterStore.register(counterView);
        counterView.update(counterState);

        Scene scene = new Scene(counterView, 200, 100);

        primaryStage.setTitle("Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
