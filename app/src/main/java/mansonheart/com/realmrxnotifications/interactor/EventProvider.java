package mansonheart.com.realmrxnotifications.interactor;

import mansonheart.com.database.RealmAccessLayer;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class EventProvider {

    private RealmAccessLayer realmAccessLayer;

    public EventProvider(RealmAccessLayer realmAccessLayer) {
        this.realmAccessLayer = realmAccessLayer;
    }

}
