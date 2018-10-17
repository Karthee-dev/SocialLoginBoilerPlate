package arivista.login.utils;

import android.content.Context;
import android.preference.PreferenceManager;

public final class PreferenceUtils {

  private PreferenceUtils() {
  }


  public static void saveBoolean(Context context, String key, boolean value) {
    PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())
      .edit()
      .putBoolean(key, value)
      .apply();
  }

  public static boolean getBoolean(Context context, String key) {
    return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())
      .getBoolean(key, false);
  }




  public static void clear(Context context) {
    PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())
      .edit().clear().apply();
  }
}