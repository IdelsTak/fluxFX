package com.github.idelstak.fluxfx.example;

import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import org.junit.Test;
import static org.testfx.assertions.api.Assertions.assertThat;
import org.testfx.framework.junit.ApplicationTest;

public class CounterAppTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new CounterApp().start(stage);
    }

    @Test
    public void testCounter() {
        clickOn("#incrementButton");
        Labeled label = lookup("#countLabel").query();

        assertThat(label).hasText("1");

        clickOn("#decrementButton");
        assertThat(label).hasText("0");

        for (int i = 0; i < 10; i++) {
            clickOn("#incrementButton");
        }

        assertThat(label).hasText("10");

        clickOn("#decrementButton");
        assertThat(label).hasText("9");
    }
}
