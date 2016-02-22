package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpView;

import mansonheart.com.realmrxnotifications.model.Event;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public interface EventDetailsView extends MvpView {
    void showLoading();
    void hideLoading();
    void showMessage(String message);
    void eventLoaded(Event event);
}
