package tk.dehoghori.dehoghori.data;

/*
 * this class holds all the important keys & constants that will be frequently used throughout the
 * entire app. so register those frequently keys and constants here to avoid typo & misunderstanding.
 * this class will  prove to be a great of deal as you write more & more code.
 * */

import android.net.Uri;

public class Key {

    public static final String KEY_APP_NAME = "kAppN";

    // content provider constants and URIs
    public static final String AUTHORITY = "tk.dehoghori";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

}
