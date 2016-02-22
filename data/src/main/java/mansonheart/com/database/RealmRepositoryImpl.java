package mansonheart.com.database;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Provider;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */
public class RealmRepositoryImpl implements RealmRepository {

    Provider<Realm> realmProvider;
    RealmQueryableCollection realmQueryCollection;

    public RealmRepositoryImpl(Provider<Realm> realmProvider) {
        this.realmProvider = realmProvider;
        this.realmQueryCollection = new RealmQueryableCollection();
    }

    @Override
    public <T> Observable<T> get(Class clazz, Func1<RealmQuery, RealmQuery> predicate) {
        RealmResults results = getInner(clazz, predicate);
        results.load();
        BehaviorSubject<T> behaviorSubject = BehaviorSubject.create((T) getRealm().copyFromRealm(results));
        RealmChangeListener realmChangeListener = () -> {
            RealmResults realmResults = getInner(clazz, predicate);
            realmResults.load();
            behaviorSubject.onNext((T) getRealm().copyFromRealm(realmResults));
        };
        results.addChangeListener(realmChangeListener);
        realmQueryCollection.add(clazz, predicate, realmChangeListener, behaviorSubject);
        return behaviorSubject;
    }

    @Override
    public void storeObject(Class clazz, JSONObject jsonObject) {
        getRealm().beginTransaction();
        getRealm().createOrUpdateObjectFromJson(clazz, jsonObject);
        getRealm().commitTransaction();
        checkObservers(clazz);
    }

    @Override
    public void storeObjects(Class clazz, JSONArray jsonArray) {
        getRealm().beginTransaction();
        getRealm().createOrUpdateAllFromJson(clazz, jsonArray);
        getRealm().commitTransaction();
        checkObservers(clazz);
    }


    @Override
    public <T> Observable<T> update(Class clazz, Action0 action) {
        return (Observable<T>) Observable.create(subscriber -> {
            getRealm().beginTransaction();
            action.call();
        }).doOnNext(o -> {
            getRealm().commitTransaction();
            checkObservers(clazz);
        });
    }

    private <T extends RealmObject> RealmResults<T> getInner(Class clazz, Func1<RealmQuery, RealmQuery> predicate) {
        RealmQuery query = getRealm().where(clazz);
        if (predicate != null)
            query = predicate.call(query);
        return query.findAllAsync();
    }

    private void checkObservers(Class clazz) {
        Observable.from(realmQueryCollection.getQuerables(clazz))
                .subscribe(realmQuerable -> {
                    if (!realmQuerable.getSubject().hasObservers()) {
                        realmQueryCollection.queryables.remove(realmQuerable);
                    }
                });
    }

    private Realm getRealm() {
        return realmProvider.get();
    }
}
