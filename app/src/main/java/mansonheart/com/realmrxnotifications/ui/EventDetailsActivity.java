package mansonheart.com.realmrxnotifications.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mansonheart.com.realmrxnotifications.App;
import mansonheart.com.realmrxnotifications.R;
import mansonheart.com.realmrxnotifications.di.components.DaggerEventDetailComponent;
import mansonheart.com.realmrxnotifications.di.components.EventDetailComponent;
import mansonheart.com.realmrxnotifications.di.module.EventsModule;
import mansonheart.com.realmrxnotifications.model.Event;
import mansonheart.com.realmrxnotifications.presenter.EventDetailsPresenter;
import mansonheart.com.realmrxnotifications.presenter.EventDetailsView;

/**
 * Created by Zherebtsov Alexandr on 22.02.2016.
 */
public class EventDetailsActivity extends MvpActivity<EventDetailsView, EventDetailsPresenter>
        implements EventDetailsView {

    private static final String INTENT_EXTRA_PARAM_EVENT_ID = "EVENT_ID";

    private int eventId;
    private EventDetailComponent component;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.tv_category)
    TextView tvCategory;


    public static Intent getIntent(Context context, int eventId) {
        Intent intent = new Intent(context, EventDetailsActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.loadEvent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @NonNull
    @Override
    public EventDetailsPresenter createPresenter() {
        this.eventId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_EVENT_ID, -1);
        injectDependencies();
        return component.presenter();
    }

    private void injectDependencies() {
        component = DaggerEventDetailComponent.builder()
                .appComponent(App.getAppComponent())
                .eventsModule(new EventsModule(this.eventId))
                .build();
        component.inject(this);
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
        toolbar.setTitle(event.getTitle());
        tvCategory.setText(event.getCategory().getName());
    }
}
