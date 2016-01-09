package mansonheart.com.realmrxnotifications.di.components;

import android.content.Context;

import dagger.Component;
import io.realm.Realm;
import mansonheart.com.database.RealmRepository;
import mansonheart.com.realmrxnotifications.App;
import mansonheart.com.realmrxnotifications.di.module.AppModule;
import mansonheart.com.realmrxnotifications.di.scope.PerApplication;

/**
 * Created by Zherebtsov Alexandr on 07.01.2016.
 */
@PerApplication
@Component(
        modules = {AppModule.class}
)
public interface AppComponent {
    void inject(App app);
    Context context();
    Realm realm();
    RealmRepository realmAccessLayer();
}
