package mansonheart.com.realmrxnotifications.interactor;

import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.data.EventFactory;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 13.02.2016.
 */
public class AddEventInteractor extends UseCase {

    private final RealmRepository realmRepository;

    public AddEventInteractor(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    @Override
    protected Observable getUseCaseObservable() {
        realmRepository.storeObject(Event.class, EventFactory.getEvent());
        return Observable.empty();
    }

}
