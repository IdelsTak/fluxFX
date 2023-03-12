package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Dispatcher;
import com.github.idelstak.fluxfx.api.State;
import com.github.idelstak.fluxfx.api.View;
import com.github.idelstak.fluxfx.example.CounterAction.Type;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CounterView extends VBox implements View {

    private final Label countLabel;

    public CounterView() {
        countLabel = new Label();
        countLabel.setId("countLabel");
        Button incrementButton = new Button("Increment");
        incrementButton.setId("incrementButton");
        Button decrementButton = new Button("Decrement");
        decrementButton.setId("decrementButton");

        incrementButton.setOnAction(e -> {
            Dispatcher.dispatch(new CounterAction(Type.INCREMENT));
        });

        decrementButton.setOnAction(e -> {
            Dispatcher.dispatch(new CounterAction(Type.DECREMENT));
        });

        getChildren().addAll(countLabel, incrementButton, decrementButton);
    }

    @Override
    public void update(State state) {
        if (state instanceof CounterState counterState) {
            countLabel.setText(String.valueOf(counterState.getCount()));
        }
    }

}
