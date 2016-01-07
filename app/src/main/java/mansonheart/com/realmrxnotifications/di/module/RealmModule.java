package mansonheart.com.realmrxnotifications.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import mansonheart.com.realmrxnotifications.di.scope.PerApplication;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */

@Module
public class RealmModule {
    Application application;

    public RealmModule(Application application) {
        this.application = application;
        initRealm();
    }

    @PerApplication
    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    private void initRealm() {
        RealmConfiguration realmConfiguration
                = new RealmConfiguration.Builder(application.getApplicationContext())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
