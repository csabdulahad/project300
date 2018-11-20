package tk.dehoghori.dehoghori.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * this helper class acts as a wrapper class for SharedPreference. it reduces the amount of code
 * requires to write evey time when you need to access or modify preferences
 *
 * @author abdul ahad
 */
public class PrefHelper {

    /**
     * this method puts an integer into the preference with given key and value
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static void putIntApply(Context context, String key, int value) {
        getEditor(context).putInt(key, value).apply();
    }

    /**
     * similar to {@code putIntApply}, but this method flashes the commit immediately and returns
     * whether the preference was saved or not
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static boolean putInt(Context context, String key, int value) {
        return getEditor(context).putInt(key, value).commit();
    }

    /**
     * it returns an integer from the preference, associated with the given key. if it doesn't find
     * any associated value with the given key, then it simply returns the given default value
     *
     * @param context      the context where you are
     * @param key          the key for your value
     * @param defaultValue the return value when no associated value found with the given key
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);

        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * this method puts a boolean into the preference with given key and value
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static void putBooleanApply(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).apply();
    }

    /**
     * similar to {@code putBooleanApply}, but this method flashes the commit immediately and returns
     * whether the preference was saved or not
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        return getEditor(context).putBoolean(key, value).commit();
    }

    /**
     * it returns a boolean value from the preference, associated with the given key. if it doesn't
     * find any associated value with the given key, then it simply returns the given default value
     *
     * @param context      the context where you are from
     * @param key          the key for your value
     * @param defaultValue the return value when no associated value found with the given key
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * this method puts a string into the preference with given key and value
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static void putStringApply(Context context, String key, String value) {
        getEditor(context).putString(key, value).apply();
    }

    /**
     * similar to {@code putStringApply}, but this method flashes the commit immediately and returns
     * whether the preference was saved or not
     *
     * @param context the context where you are
     * @param key     the key for your value
     * @param value   the value you need to store
     */
    public static boolean putString(Context context, String key, String value) {
        return getEditor(context).putString(key, value).commit();
    }

    /**
     * it returns a string value from the preference, associated with the given key. if it doesn't
     * find any associated value with the given key, then it simply returns the given default value
     *
     * @param context      the context where you are
     * @param key          the key for your value
     * @param defaultValue the return value when no associated value found with the given key
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);

        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    /**
     * this method returns Editor object of SharedPreference, so that you don't need to write more code
     *
     * @param context the context where you are
     */
    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    /**
     * this method return the SharedPreference, so that you don't need to write more code
     *
     * @param context the context where you are
     */
    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
