package com.dani_chuks.andeladeveloper.starwars.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


public class SharedPreferenceManager {

    private final SharedPreferences sharedPreferences;
    private final Context context;

    public SharedPreferenceManager(@NonNull final SharedPreferences sharedPreferences, @NonNull final Context context) {
        this.sharedPreferences = sharedPreferences;
        this.context = context;
    }


    public void setDataTypeFetchedOnce(String resourceName){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(resourceName, true);
        editor.apply();
    }

    public boolean isDataTypeFetchedOnce(String resourceName){
        return sharedPreferences.getBoolean(resourceName, false);
    }

    public void setResourceNextPage(String resourceName, int nextPage){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(resourceName, nextPage);
        editor.apply();
    }

    public int getResourceNextPage(String resourceName){
        return sharedPreferences.getInt(resourceName, 1);
    }
}
