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
public class SleepDailySummary extends SleepDailySummaryNoStages {
    
    private SleepStages stages;

    public SleepDailySummary(int id, Date date, int totalminutesasleep, int totalsleeprecords, int totaltimeinbed, SleepStages stages) {
        super(id, date, totalminutesasleep, totalsleeprecords, totaltimeinbed);
        this.stages = stages;
    }
    
    public SleepStages getStages() {
        return stages;
    }

    public void setStages(SleepStages stages) {
        this.stages = stages;
    }
}
