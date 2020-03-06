package com.dani_chuks.andeladeveloper.starwars.data.db

import java.util.*

object DbUtils {

    fun getLastPathFromUrl(url: String): Int {
        //        Uri uri = Uri.parse("http://base_path/some_segment/id");
        //        String lastPathSegment = uri.getLastPathSegment();
        //        return Integer.parseInt(lastPathSegment);
        val arr = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val length = arr.size
        val id = Integer.parseInt(arr[length - 1])
        //        Log.e("LasssPPPP", "getLastPathFromUrl: " + id );
        return id
    }

    fun convertUrlListToIntList(urlList: List<String>): List<Int> {
        val resultList = ArrayList<Int>()
        for (url in urlList) {
            val lastPath = getLastPathFromUrl(url)
            resultList.add(lastPath)
        }
        return resultList
    }
}