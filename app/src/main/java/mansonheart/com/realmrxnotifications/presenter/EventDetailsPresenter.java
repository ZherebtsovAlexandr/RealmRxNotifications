package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import mansonheart.com.realmrxnotifications.interactor.UseCase;
import mansonheart.com.realmrxnotifications.model.Event;
import rx.Subscriber;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public class EventDetailsPresenter extends MvpBasePresenter<EventDetailsView> {

    private Subscriber<Event> getEventDetailsSubscriber;

    private final UseCase getEventDetailsInteractor;

    @Inject
    EventDetailsPresenter(@Named("GetEventDetailsInteractor") UseCase getEventDetailsInteractor) {
        this.getEventDetailsInteractor = getEventDetailsInteractor;
    }

    public void loadEvent() {
        if (isViewAttached()) {
            getView().showLoading();
        }
        getEventDetailsInteractor.execute(getEventDetailsSubscriber);
    }

    private Subscriber<Event> getEventDetailsSubscriber() {
        return new Subscriber<Event>() {

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
            public void onNext(Event event) {
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().showMessage(event.getTitle());
                    getView().eventLoaded(event);
                }
            }
        };
    }

    public void onResume() {
        this.getEventDetailsSubscriber = getEventDetailsSubscriber();
    }

    public void onStop() {
        getEventDetailsSubscriber.unsubscribe();
    }

}
