package kr.co.woobi.imyeon.realmexam;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config2=new RealmConfiguration.Builder()
                .name("person.realm")
                .build();
        Realm.setDefaultConfiguration(config2);
//        Realm = Realm.getInstance(config2);

        Stetho.initializeWithDefaults(this);
    }
}
