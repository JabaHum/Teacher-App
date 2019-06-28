package glowsomecomputingsolutions.com.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import glowsomecomputingsolutions.com.Activities.LoginActivity;
import glowsomecomputingsolutions.com.Model.Users;

/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "PREFS_NAME";
    private static final String KEY_NAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_SEX = "keysex";
    private static final String KEY_ID = "keyid";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_SCHOOLTYPE = "keyschooltype";
    private static final String KEY_LEVEL = "keylevel";
    private static final String KEY_PAPER = "keypaper";
    private static final String KEY_SUBJECTS = "keypaper";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(Users user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_SEX, user.getSex());
        editor.putString(KEY_PASSWORD, user.getSex());
        editor.putString(KEY_SCHOOLTYPE, user.getSex());
        editor.putString(KEY_LEVEL, user.getSex());
        editor.putString(KEY_SUBJECTS, user.getSex());
        editor.putString(KEY_PAPER, user.getSex());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null) != null;
    }

    //this method will give the logged in user
    public Users getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Users(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_SEX, null),
                sharedPreferences.getString(KEY_PASSWORD,null),
                sharedPreferences.getString(KEY_SCHOOLTYPE,null),
                sharedPreferences.getString(KEY_SUBJECTS,null),
                sharedPreferences.getString(KEY_LEVEL,null),
                sharedPreferences.getInt(KEY_PAPER,-1)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}