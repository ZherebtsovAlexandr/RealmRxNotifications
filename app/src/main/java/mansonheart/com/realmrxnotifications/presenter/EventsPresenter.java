package mansonheart.com.realmrxnotifications.presenter;
import javax.inject.Inject;

import mansonheart.com.realmrxnotifications.interactor.EventProvider;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */

public class EventsPresenter extends RxBasePresenter<EventsView> {

    EventProvider eventProvider;

    @Inject
    EventsPresenter(EventProvider eventProvider) {
        this.eventProvider = eventProvider;
    }

    public void loadEvents() {

    }

    public void addEvent() {

    }
}
