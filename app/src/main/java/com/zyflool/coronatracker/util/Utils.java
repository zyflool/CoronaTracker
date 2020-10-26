package com.zyflool.coronatracker.util;

import com.zyflool.coronatracker.data.TimeLine;
import com.zyflool.coronatracker.data.TimeLines;
import com.zyflool.coronatracker.net.OverallResultResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String paserTime(long time) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String times = format.format(new Date(time * 1000L));
        System.out.print("日期格式---->" + times);
        return times;
    }

    public static TimeLines getDataFromJson(String json) throws JSONException {

        JSONObject jsonRoot = new JSONObject(json);
        JSONObject timelines = new JSONObject(jsonRoot.getJSONArray("locations")
                .get(0).toString()).getJSONObject("timelines");

        String confirmedTl = timelines.getJSONObject("confirmed").getJSONObject("timeline").toString();
        confirmedTl = confirmedTl.substring(1,confirmedTl.length()-1);

        String[] confirmedTimeline = confirmedTl.split(",");
        List<TimeLine> confirmedTlList = new ArrayList<>();
        for ( String timeline : confirmedTimeline ) {
            String[] t = timeline.split("\"");
            t[2] = t[2].substring(1);
            confirmedTlList.add(new TimeLine(t[1].substring(5, 10), Integer.parseInt(t[2])));
        }


        String deathTl = timelines.getJSONObject("deaths").getJSONObject("timeline").toString();
        deathTl = deathTl.substring(1, deathTl.length()-1);

        String[] deathTimeline = deathTl.split(",");
        List<TimeLine> deathTlList = new ArrayList<>();
        for ( String timeline : deathTimeline ) {
            String[] t = timeline.split("\"");
            t[2] = t[2].substring(1);
            deathTlList.add(new TimeLine(t[1].substring(5, 10), Integer.parseInt(t[2])));
        }

        TimeLines timeLines = new TimeLines(confirmedTlList, deathTlList);
        return timeLines;
    }

    public static void putDataToSp(SPUtils spUtils, OverallResultResponse.ResultsBean t) {

        spUtils.put("InlandCurrentConfirmedCount", t.getCurrentConfirmedCount());
        spUtils.put("InlandConfirmedCount", t.getConfirmedCount());
        spUtils.put("InlandCuredCount",t.getCuredCount());
        spUtils.put("InlandDeadCount", t.getDeadCount());
        spUtils.put("InlandImportedCount", t.getSuspectedCount());
        spUtils.put("InlandAsymptomaticCount", t.getSeriousCount());

        spUtils.put("InlandCurrentConfirmedIncr", t.getCurrentConfirmedIncr());
        spUtils.put("InlandConfirmedIncr", t.getConfirmedIncr());
        spUtils.put("InlandCuredIncr", t.getCuredIncr());
        spUtils.put("InlandDeadIncr", t.getDeadIncr());
        spUtils.put("InlandImportedIncr", t.getSuspectedIncr());
        spUtils.put("InlandAsymptomaticIncr", t.getSeriousIncr());

        spUtils.put("WorldCurrentConfirmedCount", t.getGlobalStatistics().getCurrentConfirmedCount());
        spUtils.put("WorldConfirmedCount", t.getGlobalStatistics().getConfirmedCount());
        spUtils.put("WorldCuredCount",t.getGlobalStatistics().getCuredCount());
        spUtils.put("WorldDeadCount", t.getGlobalStatistics().getDeadCount());

        spUtils.put("WorldCurrentConfirmedIncr", t.getGlobalStatistics().getCurrentConfirmedIncr());
        spUtils.put("WorldConfirmedIncr", t.getGlobalStatistics().getConfirmedIncr());
        spUtils.put("WorldCuredIncr", t.getGlobalStatistics().getCuredIncr());
        spUtils.put("WorldDeadIncr", t.getGlobalStatistics().getDeadIncr());
    }

}
