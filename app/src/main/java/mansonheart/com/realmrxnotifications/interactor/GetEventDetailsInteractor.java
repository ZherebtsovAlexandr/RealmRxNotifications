package mansonheart.com.realmrxnotifications.interactor;

import java.util.List;

import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public class GetEventDetailsInteractor extends UseCase {

    private final int eventId;
    private final RealmRepository realmRepository;

    public GetEventDetailsInteractor(int eventId, RealmRepository realmRepository) {
        this.eventId = eventId;
        this.realmRepository = realmRepository;
    }

    @Override
    protected Observable getUseCaseObservable() {
        return realmRepository.get(Event.class, predicate -> predicate.equalTo("eventId", eventId))
                .map(events -> ((List<Event>) events).get(0));
    }

}
