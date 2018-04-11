/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DailySleepSummary;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class SleepDailySummaryNoStages {
    
    private int id, totalMinutesAsleep, totalSleepRecords, totalTimeInBed;
    private Date date;

    public SleepDailySummaryNoStages(int id, Date date, int totalminutesasleep, int totalsleeprecords, int totaltimeinbed) {
        this.id = id;
        this.totalMinutesAsleep = totalminutesasleep;
        this.totalSleepRecords = totalsleeprecords;
        this.totalTimeInBed = totaltimeinbed;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalMinutesAsleep() {
        return totalMinutesAsleep;
    }

    public void setTotalMinutesAsleep(int totalMinutesAsleep) {
        this.totalMinutesAsleep = totalMinutesAsleep;
    }

    public int getTotalSleepRecords() {
        return totalSleepRecords;
    }

    public void setTotalSleepRecords(int totalSleepRecords) {
        this.totalSleepRecords = totalSleepRecords;
    }

    public int getTotalTimeInBed() {
        return totalTimeInBed;
    }

    public void setTotalTimeInBed(int totalTimeInBed) {
        this.totalTimeInBed = totalTimeInBed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
