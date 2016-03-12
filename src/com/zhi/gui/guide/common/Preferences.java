package com.zhi.gui.guide.common;

import android.content.Context;
import android.content.SharedPreferences;

public final class Preferences {
    public static final String PREFERENCE_NAME = "zhilian_app";
    public static final String KEY_USERNAME = "login_username";
    public static final String KEY_PASSWORD = "login_password";
    public static final String KEY_IS_LOGGED_IN = "is_logged_in";

    public static void setUserLoggedInState(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND).edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public static boolean getUserLoggedInState(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND);
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }


    public static void setUserName(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND).edit();
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND);
        return preferences.getString(KEY_USERNAME, "");
    }

    public static void setPassword(Context context, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND).edit();
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND);
        return preferences.getString(KEY_PASSWORD, "");
    }


}
