package mansonheart.com.realmrxnotifications.di.module;

import android.content.Context;


import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import mansonheart.com.database.RealmRepository;
import mansonheart.com.database.RealmRepositoryImpl;
import mansonheart.com.realmrxnotifications.App;
import mansonheart.com.realmrxnotifications.di.scope.PerApplication;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */
@Module(includes = {RealmModule.class})
public class AppModule {

    protected final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @PerApplication
    @Provides
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @PerApplication
    @Provides
    RealmRepository provideRealmLocalService(Provider<Realm> realmProvider) {
        return new RealmRepositoryImpl(realmProvider);
    }
}
