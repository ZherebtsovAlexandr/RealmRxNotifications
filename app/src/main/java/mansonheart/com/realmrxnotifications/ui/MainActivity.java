package mansonheart.com.realmrxnotifications.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mansonheart.com.realmrxnotifications.App;
import mansonheart.com.realmrxnotifications.R;
import mansonheart.com.realmrxnotifications.model.Event;
import mansonheart.com.realmrxnotifications.di.components.DaggerEventsComponent;
import mansonheart.com.realmrxnotifications.di.components.EventsComponent;
import mansonheart.com.realmrxnotifications.presenter.EventsPresenter;
import mansonheart.com.realmrxnotifications.presenter.EventsView;

public class MainActivity extends MvpActivity<EventsView, EventsPresenter>
        implements EventsView {

    EventsComponent component;
    EventsAdapter eventsAdapter;

    @InjectView(R.id.list)
    ListView listView;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        eventsAdapter = new EventsAdapter(this);
        listView.setAdapter(eventsAdapter);
        fab.setOnClickListener(view -> presenter.addEvent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadEvents();
    }

    @NonNull
    @Override
    public EventsPresenter createPresenter() {
        injectDependencies();
        return component.presenter();
    }

    private void injectDependencies() {
        component = DaggerEventsComponent.builder()
                .appComponent(App.getAppComponent())
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
    public void eventsLoaded(List<Event> events) {
        eventsAdapter.setItems(events);
    }
}
