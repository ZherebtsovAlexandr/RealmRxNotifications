package mansonheart.com.realmrxnotifications.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Zherebtsov Alexandr on 13.02.2016.
 */
public abstract class UseCase {

    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable getUseCaseObservable();

    public void execute(Subscriber UseCaseSubscriber) {
        this.subscription = this.getUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UseCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
