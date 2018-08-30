package com.dani_chuks.andeladeveloper.starwars.data.db;

import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    public static int getLastPathFromUrl(String url){
//        Uri uri = Uri.parse("http://base_path/some_segment/id");
//        String lastPathSegment = uri.getLastPathSegment();
//        return Integer.parseInt(lastPathSegment);
        String[] arr = url.split("\\/");
        int length = arr.length;
        int id = Integer.parseInt(arr[length - 1]);
//        Log.e("LasssPPPP", "getLastPathFromUrl: " + id );
        return id;
    }

    public static List<Integer> convertUrlListToIntList(List<String> urlList){
        ArrayList<Integer> resultList = new ArrayList<>();
        for(String url: urlList){
            int lastPath = getLastPathFromUrl(url);
            resultList.add(lastPath);
        }
        return  resultList;
    }
}