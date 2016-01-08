package mansonheart.com.realmrxnotifications.interactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import mansonheart.com.database.RealmAccessLayer;
import mansonheart.com.realmrxnotifications.data.Event;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class EventProvider {

    private RealmAccessLayer realmAccessLayer;

    public EventProvider(RealmAccessLayer realmAccessLayer) {
        this.realmAccessLayer = realmAccessLayer;
    }

    public Observable<List<Event>> getEvents() {
        return realmAccessLayer.get(Event.class, predicate -> predicate.isNotNull("category"));
    }

    public void addEvent() {
        Random r = new Random();
        int id = r.nextInt(99999);
        try {
            realmAccessLayer.storeObject(Event.class, new JSONObject("{eventId: " + id + ", title: \"Event" + id + "\"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
