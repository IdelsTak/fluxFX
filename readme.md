# FluxFX

Flux is a pattern for managing state in an application.

The Flux architecture has four main components:

1. **Actions** - These are payloads of information that send data from your application to your store.
2. **Dispatcher** - This is a central hub that manages all data flow in a Flux application. It receives actions and dispatches them to the appropriate stores.
3. **Stores** - These contain the application state and logic. They respond to actions and update their state accordingly.
4. **Views** - These are the user interfaces of your application. They receive updates from the stores and render the appropriate UI.
