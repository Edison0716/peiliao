package com.wenmingkeji.peiliao.model;

/**
 * Created by bevis on 2016/7/22.
 */
public class TimeItem {

    private boolean statusMorning;
    private boolean statusAfternoon;
    private boolean statusEvening;

    public TimeItem(boolean statusAfternoon, boolean statusEvening, boolean statusMorning) {
        this.statusAfternoon = statusAfternoon;
        this.statusEvening = statusEvening;
        this.statusMorning = statusMorning;
    }

    public boolean isStatusEvening() {
        return statusEvening;
    }

    public void setStatusEvening(boolean statusEvening) {
        this.statusEvening = statusEvening;
    }

    public boolean isStatusMorning() {
        return statusMorning;
    }

    public void setStatusMorning(boolean statusMorning) {
        this.statusMorning = statusMorning;
    }

    public boolean isStatusAfternoon() {

        return statusAfternoon;
    }

    public void setStatusAfternoon(boolean statusAfternoon) {
        this.statusAfternoon = statusAfternoon;
    }
}
