# RealmRxNotifications

Reactive layer (similar to the repository pattern) for the Realm.
Main idea is to use reactive layer for accessing the [Realm].
If you make a request to the layer it will always return an Observable<T>. 
The layer remembers entity and predicate and returns Observable<T> in the future.
If entity will be changed, the subject will find out about it.


[realm]: <https://realm.io/>

