package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public abstract class RxBasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    CompositeSubscription subscription;

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        unsubscribe();
    }
}
