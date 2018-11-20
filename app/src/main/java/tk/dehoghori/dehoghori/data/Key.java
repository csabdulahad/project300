package tk.dehoghori.dehoghori.data;

import android.net.Uri;

/*
 * this class holds all the important keys & constants that will be frequently used throughout the
 * entire app. so register those frequently keys and constants here to avoid typo & misunderstanding.
 * this class will  prove to be a great of deal as you write more & more code.
 * */

public class Key {

    public static final String KEY_APP_NAME = "kAppN";
    public static final String FIRST_RUN = "firstRun";

    public static final int SEX_FEMALE = 0;
    public static final int SEX_MALE = 1;

    // User keys
    public static final String USER_DOB_DAY = "userDobDay";
    public static final String USER_DOB_MONTH = "userDobMonth";
    public static final String USER_DOB_YEAR = "userDobYear";
    public static final String USER_SEX = "userSex";
    public static final String USER_NAME = "userName";
    public static final String USER_BLOOD_GROUP = "userBloodGroup";

    // content provider constants and URIs
    public static final String AUTHORITY = "tk.dehoghori";
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

}
