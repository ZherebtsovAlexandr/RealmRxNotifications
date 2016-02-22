package mansonheart.com.realmrxnotifications.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import mansonheart.com.realmrxnotifications.R;
import mansonheart.com.realmrxnotifications.model.Event;
import mansonheart.com.realmrxnotifications.presenter.EventDetailsPresenter;
import mansonheart.com.realmrxnotifications.presenter.EventDetailsView;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public class EventDetailsActivity extends MvpActivity<EventDetailsView, EventDetailsPresenter>
        implements EventDetailsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.inject(this);
    }

    @Override
    public void showLoading() {
        /*code show loading here*/
    }

    @Override
    public void hideLoading() {
          /*code hide loading here*/
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eventLoaded(Event event) {

    }

    @NonNull
    @Override
    public EventDetailsPresenter createPresenter() {
        return null;
    }
}
