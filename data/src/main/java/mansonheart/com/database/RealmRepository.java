package mansonheart.com.database;

import org.json.JSONArray;
import org.json.JSONObject;

import io.realm.RealmQuery;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */
public interface RealmRepository {
    <T> Observable<T> get(Class clazz, Func1<RealmQuery, RealmQuery> predicate);
    void storeObject(Class clazz, JSONObject jsonObject);
    void storeObjects(Class clazz, JSONArray jsonArray);
    <T> Observable<T> update(Class clazz, Action0 action);
}