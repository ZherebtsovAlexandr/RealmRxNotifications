package mansonheart.com.database;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Provider;

import io.realm.Realm;
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
        BehaviorSubject<T> behaviorSubject = BehaviorSubject.create((T) getInner(clazz, predicate));
        realmQueryCollection.add(clazz, predicate, behaviorSubject);
        return behaviorSubject;
    }

    public <T extends RealmObject> RealmResults<T> getInner(Class clazz, Func1<RealmQuery, RealmQuery> predicate) {
        RealmQuery query = getRealm().where(clazz);
        if (predicate != null)
            query = predicate.call(query);
        return query.findAllAsync();
    }

    @Override
    public void storeObject(Class clazz, JSONObject jsonObject) {
        getRealm().beginTransaction();
        getRealm().createOrUpdateObjectFromJson(clazz, jsonObject);
        getRealm().commitTransaction();
        notifyObservers(clazz);

    }

    @Override
    public void storeObjects(Class clazz, JSONArray jsonArray) {
        getRealm().beginTransaction();
        getRealm().createOrUpdateAllFromJson(clazz, jsonArray);
        getRealm().commitTransaction();
        notifyObservers(clazz);
    }


    @Override
    public <T> Observable<T> update(Class clazz, Action0 action) {
        return (Observable<T>) Observable.create(subscriber -> {
            getRealm().beginTransaction();
            action.call();
        }).doOnNext(o -> {
            getRealm().commitTransaction();
            notifyObservers(clazz);
        });
    }

    private void notifyObservers(Class clazz) {
        Observable.from(realmQueryCollection.getQuerables(clazz))
                .subscribe(realmQuerable ->
                        realmQuerable.getSubject().onNext(getInner(clazz, realmQuerable.getPredicate())));
    }

    private Realm getRealm() {
        return realmProvider.get();
    }
}
