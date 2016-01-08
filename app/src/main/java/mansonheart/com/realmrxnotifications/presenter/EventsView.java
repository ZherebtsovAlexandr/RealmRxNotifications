package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import mansonheart.com.realmrxnotifications.model.Event;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public interface EventsView extends MvpView {
    void showLoading();
    void hideLoading();
    void showMessage(String message);
    void eventsLoaded(List<Event> events);
}
