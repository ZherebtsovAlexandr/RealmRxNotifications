package mansonheart.com.realmrxnotifications.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Zherebtsov Alexandr on 08.01.2016.
 */
public class EventFactory {

    private static final int COUNT_DEFAULT_CATEGORIES = 3;
    private static final int COUNT_DEFAULT_EVENTS = 3;

    public static JSONObject getEvent() {
        JSONObject eventJsonObject = null;
        try {
            Random r = new Random();
            int eventId = r.nextInt(99999) + COUNT_DEFAULT_EVENTS;
            int categoryId = r.nextInt(10) + COUNT_DEFAULT_CATEGORIES;
            eventJsonObject = new JSONObject("{" +
                    "      \"eventId\":" + eventId + " ," +
                    "      \"title\": \"Event #" + eventId + "\"," +
                    "      \"category\": {" +
                    "        \"categoryId\": " + categoryId + "," +
                    "        \"name\": \"Category #" + categoryId + "\"" +
                    "      }" +
                    "    }");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventJsonObject;
    }
}
