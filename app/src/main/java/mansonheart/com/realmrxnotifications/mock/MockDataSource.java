package mansonheart.com.realmrxnotifications.mock;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import mansonheart.com.realmrxnotifications.data.DataSource;
import rx.Observable;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class MockDataSource implements DataSource {

    Context context;

    public MockDataSource(Context context) {
        this.context = context;
    }

    public Observable<JSONObject> getRemoteJson(String relativePath) {

        if (relativePath.contains("events")) {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset("events.json"));
                return Observable.create(subscriber -> subscriber.onNext(obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (relativePath.contains("categories")) {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset("categories.json"));
                return Observable.create(subscriber -> subscriber.onNext(obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return Observable.create(subscriber -> subscriber.onNext(new JSONObject()));

    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
