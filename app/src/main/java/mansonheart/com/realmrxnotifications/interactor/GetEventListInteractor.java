package mansonheart.com.realmrxnotifications.interactor;

import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class GetEventListInteractor extends UseCase {

    private final RealmRepository realmRepository;

    public GetEventListInteractor(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    @Override
    protected Observable getUseCaseObservable() {
        return realmRepository.get(Event.class, predicate -> predicate.isNotNull("category"));
    }
}
