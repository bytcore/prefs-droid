package com.binjar.prefsdroid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

/**
 * Created by Arif Islam on 03-Apr-17.
 */

public class Preference {
    private static SharedPreferences mPrefs;

    public static Builder load() {
        return new Builder();
    }

    private Preference(Context context, String key, int mode) {
        if (mPrefs == null) {
            synchronized (Preference.class) {
                if (mPrefs == null) {
                    mPrefs = context.getSharedPreferences(key, mode);
                }
            }
        }
    }

    private static SharedPreferences getPreference() {
        if (mPrefs == null) {
            new Builder().prepare();
        }
        return mPrefs;
    }

    public static Map<String, ?> getAll() {
        return getPreference().getAll();
    }

    public static void putBoolean(String key, boolean value) {
        getPreference().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getPreference().getBoolean(key, defValue);
    }

    public static void putInt(String key, int value) {
        getPreference().edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defValue) {
        return getPreference().getInt(key, defValue);
    }

    public static void putLong(String key, long value) {
        getPreference().edit().putLong(key, value).apply();
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defValue) {
        return getPreference().getLong(key, defValue);
    }

    public static void putFloat(String key, float value) {
        getPreference().edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    public static float getFloat(String key, float defValue) {
        return getPreference().getFloat(key, defValue);
    }

    public static void putDouble(String key, double value) {
        getPreference().edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
    }

    public static double getDouble(String key) {
        return getDouble(key, 0.0d);
    }

    public static double getDouble(String key, double defValue) {
        return Double.longBitsToDouble(getPreference()
                .getLong(key, Double.doubleToRawLongBits(defValue)));
    }

    public static void putString(String key, String value) {
        getPreference().edit().putString(key, value).apply();
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defValue) {
        return getPreference().getString(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void putStringSet(String key, Set<String> values) {
        getPreference().edit().putStringSet(key, values).apply();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String key) {
        return getStringSet(key, null);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String key, Set<String> values) {
        return getPreference().getStringSet(key, values);
    }

    public static <T> void putObject(String key, T t) {
        getPreference().edit().putString(key, new Gson().toJson(t)).apply();
    }

    public static <T> T getObject(String key, Class<T> classOfT) {
        return new Gson().fromJson(getPreference().getString(key, null), classOfT);
    }

    public static void remove(String key) {
        getPreference().edit().remove(key).apply();
    }

    public static boolean containsKey(String key) {
        return getPreference().contains(key);
    }

    public static void clear() {
        getPreference().edit().clear().apply();
    }

    public final static class Builder {
        private String mKey;
        private Context mContext;
        private int mMode = Context.MODE_PRIVATE;

        private Builder() {

        }

        public Builder using(Context context) {
            mContext = context.getApplicationContext();
            return this;
        }

        public Builder with(String KEY) {
            mKey = KEY;
            return this;
        }

        public Preference prepare() {
            if (mContext == null) {
                throw new RuntimeException("Context must not be null.");
            }

            if (TextUtils.isEmpty(mKey)) {
                mKey = mContext.getPackageName();
            }

            return new Preference(mContext, mKey, mMode);
        }

    }
}
