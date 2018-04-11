/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleSleepSummary;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class SingleSleep {
    
    private int id, duration, efficiency, minutesAfterWakeup, minutesAsleep, minutesAwake, minutesToFallAsleep, timeInBed, infoCode;
    private Date startTime, endTime;
    private boolean isMainSleep;
    private dataType type;
    private String SingleSleepAsString;
    private SingleSleepSummary summary;

    public SingleSleep(int id, Date startTime, Date endTime, int duration, int efficiency, int minutesAfterWakeup, int minutesAsleep, int minutesAwake, int minutesToFallAsleep, int timeInBed, int infoCode, boolean isMainSleep, dataType type, SingleSleepSummary summary) {
        this.id = id;
        this.duration = duration;
        this.efficiency = efficiency;
        this.minutesAfterWakeup = minutesAfterWakeup;
        this.minutesAsleep = minutesAsleep;
        this.minutesAwake = minutesAwake;
        this.minutesToFallAsleep = minutesToFallAsleep;
        this.timeInBed = timeInBed;
        this.infoCode = infoCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isMainSleep = isMainSleep;
        this.type = type;
        this.summary = summary;
    }

    public String getSingleSleepAsString() {
        return SingleSleepAsString;
    }

    public void setSingleSleepAsString(String SingleSleepAsString) {
        this.SingleSleepAsString = SingleSleepAsString;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getMinutesAfterWakeup() {
        return minutesAfterWakeup;
    }

    public void setMinutesAfterWakeup(int minutesAfterWakeup) {
        this.minutesAfterWakeup = minutesAfterWakeup;
    }

    public int getMinutesAsleep() {
        return minutesAsleep;
    }

    public void setMinutesAsleep(int minutesAsleep) {
        this.minutesAsleep = minutesAsleep;
    }

    public int getMinutesAwake() {
        return minutesAwake;
    }

    public void setMinutesAwake(int minutesAwake) {
        this.minutesAwake = minutesAwake;
    }

    public int getMinutesToFallAsleep() {
        return minutesToFallAsleep;
    }

    public void setMinutesToFallAsleep(int minutesToFallAsleep) {
        this.minutesToFallAsleep = minutesToFallAsleep;
    }

    public int getTimeInBed() {
        return timeInBed;
    }

    public void setTimeInBed(int timeInBed) {
        this.timeInBed = timeInBed;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isIsMainSleep() {
        return isMainSleep;
    }

    public void setIsMainSleep(boolean isMainSleep) {
        this.isMainSleep = isMainSleep;
    }

    public dataType getType() {
        return type;
    }

    public void setType(dataType type) {
        this.type = type;
    }

    public SingleSleepSummary getSummary() {
        return summary;
    }

    public void setSummary(SingleSleepSummary summary) {
        this.summary = summary;
    }
    
}
