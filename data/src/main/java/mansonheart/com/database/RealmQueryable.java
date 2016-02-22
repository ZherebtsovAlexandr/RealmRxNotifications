package mansonheart.com.database;

import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by Zherebtsov Alexandr on 02.01.2016.
 */
public class RealmQueryable {

    private Class clazz;
    private Func1<RealmQuery, RealmQuery> predicate;
    private RealmChangeListener realmChangeListener;
    private BehaviorSubject subject;

    public RealmQueryable(Class clazz,
                          Func1<RealmQuery, RealmQuery> predicate,
                          RealmChangeListener realmChangeListener,
                          BehaviorSubject subject) {
        this.clazz = clazz;
        this.predicate = predicate;
        this.realmChangeListener = realmChangeListener;
        this.subject = subject;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setPredicate(Func1<RealmQuery, RealmQuery> predicate) {
        this.predicate = predicate;
    }

    public BehaviorSubject getSubject() {
        return subject;
    }

    public void setSubject(BehaviorSubject subject) {
        this.subject = subject;
    }

    public Func1<RealmQuery, RealmQuery> getPredicate() {
        return predicate;
    }
}
