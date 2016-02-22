package mansonheart.com.realmrxnotifications.ui;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void navigateToEventDetails(Context context, int eventId) {
        if (context != null) {
            Intent intentToLaunch = EventDetailsActivity.getIntent(context, eventId);
            context.startActivity(intentToLaunch);
        }
    }
}
