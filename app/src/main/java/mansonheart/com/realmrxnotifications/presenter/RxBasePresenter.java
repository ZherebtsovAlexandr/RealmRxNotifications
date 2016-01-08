package mansonheart.com.realmrxnotifications.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public abstract class RxBasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    CompositeSubscription compositeSubscription;

    protected void unsubscribe() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        compositeSubscription = null;
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        unsubscribe();
    }
}
