package mansonheart.com.realmrxnotifications.interactor;

import java.util.List;

import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.data.EventFactory;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class EventProvider {

    private RealmRepository realmRepository;

    public EventProvider(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    public Observable<List<Event>> getEvents() {
        return realmRepository.get(Event.class, predicate -> predicate.isNotNull("category"));
    }

    public void addEvent() {
        realmRepository.storeObject(Event.class, EventFactory.getEvent());
    }

}
