package mansonheart.com.realmrxnotifications;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mansonheart.com.realmrxnotifications.di.components.EventsComponent;
import mansonheart.com.realmrxnotifications.ui.EventsAdapter;

public class MainActivity extends AppCompatActivity {

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
