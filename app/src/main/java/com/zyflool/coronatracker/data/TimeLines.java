package com.zyflool.coronatracker.data;

import java.util.List;

public class TimeLines {
    private List<TimeLine> confirmedTimeline;
    private List<TimeLine> deathTimeline;

    public TimeLines(List<TimeLine> confirmedTimeline, List<TimeLine> deathTimeline) {
        this.confirmedTimeline = confirmedTimeline;
        this.deathTimeline = deathTimeline;
    }

    public List<TimeLine> getConfirmedTimeline() {
        return confirmedTimeline;
    }

    public void setConfirmedTimeline(List<TimeLine> confirmedTimeline) {
        this.confirmedTimeline = confirmedTimeline;
    }

    public void setDeathTimeline(List<TimeLine> deathTimeline) {
        this.deathTimeline = deathTimeline;
    }

    public List<TimeLine> getDeathTimeline() {
        return deathTimeline;
    }

}
