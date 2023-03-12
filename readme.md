# FluxFX

Flux is a pattern for managing state in an application.

The Flux architecture has four main components:

1. **Actions** - These are payloads of information that send data from your application to your store.
2. **Dispatcher** - This is a central hub that manages all data flow in a Flux application. It receives actions and dispatches them to the appropriate stores.
3. **Stores** - They respond to actions and update their state accordingly.
4. **States** - These contain the application state and logic
5. **Views** - These are the user interfaces of your application. They receive updates from the stores and render the appropriate UI.

The Flux UI pattern API has been defined in this repo as follows:

### Action

```java
public interface Action {

}
```

### Dispatcher

```java
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
```

### Store

```java
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
```

### View

```java
public interface View {

    void update(State state);
}
```

This repo contains concrete implementations of the API

The application's starting point:

```java
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
```

The `Action` implementation:

```java
public class CounterAction implements Action {

    public enum Type {
        INCREMENT, DECREMENT
    }

    private final Type type;

    public CounterAction(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
```

The `Store` implementation:

```java
public class CounterStore extends Store {

    private CounterState state;

    public CounterStore(CounterState state) {
        this.state = state;
    }

    @Override
    public void onAction(Action action) {
        if (action instanceof CounterAction counterAction) {
            switch (counterAction.getType()) {
                case INCREMENT -> {
                    state = CounterState.from(state.getCount() + 1);
                    emitChange(state);
                }
                case DECREMENT -> {
                    state = CounterState.from(state.getCount() - 1);
                    emitChange(state);
                }
            }
        }
    }

}
```

The application's state:

```java
public class CounterState implements State {

    private static CounterState state;
    private int count;

    private CounterState(int count) {
        this.count = count;
    }

    public static CounterState copy(CounterState state) {
        return from(state.getCount());
    }

    public static CounterState from(int count) {
        if (state == null) {
            state = new CounterState(count);
        } else {
            state.count = count;
        }

        return state;
    }

    public static CounterState increment() {
        return from(state.count + 1);
    }

    public static CounterState decrement() {
        return from(state.count - 1);
    }

    public int getCount() {
        return count;
    }

}
```

The `View` implementation:

```java
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
```

### Tests

A TestFX test case:

```java
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
```

