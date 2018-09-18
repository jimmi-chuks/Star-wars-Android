package com.dani_chuks.andeladeveloper.starwars.data.db;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class IntegerListConverter {
    @TypeConverter
    public List<Integer> gettingListFromString(String urlIds) {
        List<Integer> list = new ArrayList<>();

        String[] array = urlIds.split(",");

        for (String s : array) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    @TypeConverter
    public String writingStringFromList(List<Integer> list) {
        String urlId = "";
        for (int i : list) {
            urlId += "," + i;
        }
        return urlId;
    }
}
