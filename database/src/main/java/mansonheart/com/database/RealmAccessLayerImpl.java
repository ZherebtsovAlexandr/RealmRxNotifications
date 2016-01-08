package mansonheart.com.database;

import org.json.JSONArray;
import org.json.JSONObject;

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
public class RealmAccessLayerImpl implements RealmAccessLayer {

    Realm realm;
    RealmQueryableCollection realmQueryCollection;

    public RealmAccessLayerImpl(Realm realm) {
        this.realm = realm;
        this.realmQueryCollection = new RealmQueryableCollection();
    }

    @Override
    public <T> Observable<T> get(Class clazz, Func1<RealmQuery, RealmQuery> predicate) {
        BehaviorSubject<T> behaviorSubject = BehaviorSubject.create((T) getInner(clazz, predicate));
        realmQueryCollection.add(clazz, predicate, behaviorSubject);
        return behaviorSubject;
    }

    public <T extends RealmObject> RealmResults<T> getInner(Class clazz, Func1<RealmQuery, RealmQuery> predicate) {
        RealmQuery query = realm.where(clazz);
        if (predicate != null)
            query = predicate.call(query);
        return query.findAll();
    }

    @Override
    public void storeObject(Class clazz, JSONObject jsonObject) {
        realm.beginTransaction();
        realm.createOrUpdateObjectFromJson(clazz, jsonObject);
        realm.commitTransaction();
        callObservers(clazz);

    }

    @Override
    public void storeObjects(Class clazz, JSONArray jsonArray) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(clazz, jsonArray);
        realm.commitTransaction();
        callObservers(clazz);
    }


    @Override
    public <T> Observable<T> update(Class clazz, Action0 action) {
        return (Observable<T>) Observable.create(subscriber -> {
            realm.beginTransaction();
            action.call();
        }).doOnNext(o -> {
            realm.commitTransaction();
            callObservers(clazz);
        });
    }

    private void callObservers(Class clazz) {
        Observable.from(realmQueryCollection.getQuerables(clazz))
                .subscribe(realmQuerable ->
                        realmQuerable.getSubject().onNext(getInner(clazz, realmQuerable.getPredicate())));
    }
}
