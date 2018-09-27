package karthee.login;

import android.app.Application;
import android.content.Context;

public class MApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}