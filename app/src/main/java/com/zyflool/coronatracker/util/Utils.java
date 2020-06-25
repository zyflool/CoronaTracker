package com.zyflool.coronatracker.util;

import android.util.Log;

import androidx.annotation.Nullable;

import com.zyflool.coronatracker.data.TimeLines;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utils {

    public static String paserTime(long time) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(time * 1000L));
        System.out.print("日期格式---->" + times);
        return times;
    }



    public static List<List<TimeLines>> getDataFromJson(String json) throws JSONException {

        JSONObject jsonRoot = new JSONObject(json);
        JSONObject timelines = new JSONObject(jsonRoot.getJSONArray("locations")
                .get(0).toString()).getJSONObject("timelines");

        String confirmedTl = timelines.getJSONObject("confirmed").getJSONObject("timeline").toString();
        confirmedTl = confirmedTl.substring(1,confirmedTl.length()-1);

        String[] confirmedTimeline = confirmedTl.split(",");
        List<TimeLines> confirmedTlList = new ArrayList<>();
        for ( String timeline : confirmedTimeline ) {
            String[] t = timeline.split("\"");
            t[2] = t[2].substring(1);
            confirmedTlList.add(new TimeLines(t[1].substring(5, 10), Integer.parseInt(t[2])));
        }


        String deathTl = timelines.getJSONObject("deaths").getJSONObject("timeline").toString();
        deathTl = deathTl.substring(1, deathTl.length()-1);

        String[] deathTimeline = deathTl.split(",");
        List<TimeLines> deathTlList = new ArrayList<>();
        for ( String timeline : deathTimeline ) {
            String[] t = timeline.split("\"");
            t[2] = t[2].substring(1);
            deathTlList.add(new TimeLines(t[1].substring(5, 10), Integer.parseInt(t[2])));
        }

        List<List<TimeLines>> dataList = new ArrayList<>();
        dataList.add(confirmedTlList);
        dataList.add(deathTlList);
        return dataList;
    }
}
