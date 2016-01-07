package mansonheart.com.realmrxnotifications;

import android.app.Application;

import mansonheart.com.realmrxnotifications.di.components.AppComponent;
import mansonheart.com.realmrxnotifications.di.module.AppModule;
import mansonheart.com.realmrxnotifications.di.module.RealmModule;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */
public class App extends Application {

    private static App instance = null;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;
        injectorInit();
    }

    public static App getInstance() {
        return instance;
    }

    private void injectorInit() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(getInstance()))
                .realmModule(new RealmModule(getInstance()))
                .build();
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
