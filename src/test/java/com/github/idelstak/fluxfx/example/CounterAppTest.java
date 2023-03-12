package com.github.idelstak.fluxfx.example;

import com.github.idelstak.fluxfx.api.Dispatcher;
import com.github.idelstak.fluxfx.example.CounterAction.Type;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.assertions.api.Assertions.assertThat;
import org.testfx.framework.junit.ApplicationTest;

public class CounterAppTest extends ApplicationTest {

    private static final Dispatcher DISPATCHER = new Dispatcher();
    private static final CounterApp APP = new CounterApp(DISPATCHER);

    @Override
    public void start(Stage stage) throws Exception {
        APP.start(stage);
    }

    @Test
    public void testCounterUI() {
        var count = APP.getState().getCount();

        clickOn("#incrementButton");
        Labeled label = lookup("#countLabel").query();
        
        count += 1;

        assertThat(label).hasText(String.valueOf(count));
        
        count -= 1;

        clickOn("#decrementButton");
        assertThat(label).hasText(String.valueOf(count));

        for (int i = 0; i < 10; i++) {
            count += 1;
            clickOn("#incrementButton");
        }

        assertThat(label).hasText(String.valueOf(count));
        
        count -= 1;

        clickOn("#decrementButton");
        assertThat(label).hasText(String.valueOf(count));
    }

    @Test
    public void testCounterActions() {
        var previousCount = APP.getState().getCount();

        DISPATCHER.dispatch(new CounterAction(Type.INCREMENT));

        assertThat(APP.getState().getCount()).isEqualTo(previousCount + 1);
    }
}
