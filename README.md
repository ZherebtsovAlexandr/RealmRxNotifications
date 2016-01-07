# RealmRxNotifications

Reactive layer (similar to the pattern repository) for the Realm. Main idea is using reactive layer for access to the [Realm]. If you make a request to the layer always return Observable<T>. The layer will remember the entity, the predicate and  returns Observable<T> in the future if the entity will change the subject learns about it.


[realm]: <https://realm.io/>

