package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import mansonheart.com.realmrxnotifications.interactor.UseCase;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Subscriber;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */

public class EventsPresenter extends MvpBasePresenter<EventsView> {

    private Subscriber<List<Event>> getEventListSubscriber;

    private final UseCase getEventListInteractor;
    private final UseCase addEventInteractor;

    @Inject
    EventsPresenter(@Named("GetEventListInteractor") UseCase getEventListInteractor,
                    @Named("AddEventInteractor") UseCase addEventInteractor) {
        this.getEventListInteractor = getEventListInteractor;
        this.addEventInteractor = addEventInteractor;
    }

    public void loadEvents() {
        if (isViewAttached()) {
            getView().showLoading();
        }
        getEventListInteractor.execute(getEventListSubscriber);
    }

    public void addEvent() {
        addEventInteractor.execute(addEventSubscriber);
    }

    public void onResume() {
        this.getEventListSubscriber = getEventListSubscriber();
    }

    public void onStop() {
        getEventListInteractor.unsubscribe();
        addEventInteractor.unsubscribe();
    }

    private Subscriber<List<Event>> getEventListSubscriber() {
        return new Subscriber<List<Event>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable error) {
                if (isViewAttached()) {
                    getView().showMessage(error.getMessage());
                    getView().hideLoading();
                }
            }

            @Override
            public void onNext(List<Event> events) {
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().eventsLoaded(events);
                }
            }
        };
    }

    private final Subscriber addEventSubscriber = new Subscriber() {
        @Override
        public void onCompleted() {
            if (isViewAttached()) {
                getView().showMessage("Event added");
            }
        }

        @Override
        public void onError(Throwable error) {
            if (isViewAttached()) {
                getView().showMessage(error.getMessage());
                getView().hideLoading();
            }
        }

        @Override
        public void onNext(Object o) {

        }

    };

}
