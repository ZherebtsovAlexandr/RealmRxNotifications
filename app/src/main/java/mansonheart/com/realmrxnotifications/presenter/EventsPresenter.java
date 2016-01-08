package mansonheart.com.realmrxnotifications.presenter;

import javax.inject.Inject;

import mansonheart.com.realmrxnotifications.interactor.EventProvider;
import rx.Subscription;

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
        if (isViewAttached()) {
            getView().showLoading();
        }
        Subscription subscription = eventProvider
                .getEvents()
                .subscribe(events -> {
                    if (isViewAttached()) {
                        getView().hideLoading();
                        getView().eventsLoaded(events);
                    }
                }, error -> {
                    if (isViewAttached()) {
                        getView().showMessage(error.getMessage());
                        getView().hideLoading();
                    }
                });
        compositeSubscription.add(subscription);
    }

    public void addEvent() {
        eventProvider.addEvent();
        if (isViewAttached()) {
            getView().showMessage("Event added");
        }
    }
}
