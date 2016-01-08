package mansonheart.com.realmrxnotifications.interactor;

import java.util.List;

import mansonheart.com.database.RealmAccessLayer;
import mansonheart.com.realmrxnotifications.data.EventGenerator;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class EventProvider {

    private RealmAccessLayer realmAccessLayer;

    public EventProvider(RealmAccessLayer realmAccessLayer) {
        this.realmAccessLayer = realmAccessLayer;
    }

    public Observable<List<Event>> getEvents() {
        return realmAccessLayer.get(Event.class, predicate -> predicate.isNotNull("category"));
    }

    public void addEvent() {
        realmAccessLayer.storeObject(Event.class, EventGenerator.getEvent());
    }

}
