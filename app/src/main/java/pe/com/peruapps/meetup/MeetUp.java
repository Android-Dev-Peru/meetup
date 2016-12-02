package pe.com.peruapps.meetup;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MeetUp extends MultiDexApplication {
    private static MeetUp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public static MeetUp getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }
}
