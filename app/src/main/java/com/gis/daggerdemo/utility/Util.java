package com.gis.daggerdemo.utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
    public static boolean checkEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.trim().matches(emailPattern)) {
            return true;
        } else return false;
    }

    public static boolean isValidMobile(String target) {
        if (target.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static void putLong(Context context, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        return pref.getLong(key, 0);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        return pref.getString(key, null);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        return pref.getInt(key, 00000);
    }

    public static void clearLoginPref(Context context, String nameKey, String passKey) {
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(nameKey);
        editor.remove(passKey);

        editor.commit();
    }


    /**
     * Returns the consumer friendly device name
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    /**
     * retunrs device network operator name
     */
    public static String getDeviceNetworkOperatorName(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        return carrierName;
    }


    public static void showAlertBox(Context context,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage(msg);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void clearLoginPref(Context context,String nameKey,String passKey,String roleKey){
        SharedPreferences pref = context.getSharedPreferences(AppConstants.MY_PREF, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(nameKey);
        editor.remove(passKey);
        editor.remove(roleKey);
        editor.commit();
    }



    /*method for getting device android id*/
    public static String getDeviceId(Context context){
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return android_id;
    }


    /*method for getting device version release*/

    public static String getDeviceOSVersion(){
        return Build.VERSION.RELEASE;
    }




    public static void writeToFile(String fileName, String body)
    {
        FileOutputStream fos = null;

        try {
            String externalDirectory= Environment.getExternalStorageDirectory().toString();
             File dir = new File(externalDirectory + "/" +
                    "SyncLogs");

            if (!dir.exists())
            {
                dir.mkdirs();
//                if(!dir.mkdirs()){
//                    Log.e("ALERT","could not create the directories");
//                }
            }

             File myFile = new File(dir, fileName + ".txt");

            if (!myFile.exists())
            {
                myFile.createNewFile();
            }

            fos = new FileOutputStream(myFile);

            fos.write(body.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
