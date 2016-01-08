package mansonheart.com.realmrxnotifications.data;

import org.json.JSONObject;

import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public interface DataSource {
    Observable<JSONObject> getRemoteJson(String relativePath);
}
