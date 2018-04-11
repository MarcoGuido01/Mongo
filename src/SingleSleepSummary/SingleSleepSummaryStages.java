/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleSleepSummary;

import SingleSleepSummary.SingleSleepSummary;
import DailySleepSummary.SleepStagesValues;

/**
 *
 * @author Marco
 */
public class SingleSleepSummaryStages extends SingleSleepSummary{
    
    private SleepStagesValues deep, light, rem, wake;

    public SleepStagesValues getDeep() {
        return deep;
    }

    public void setDeep(SleepStagesValues deep) {
        this.deep = deep;
    }

    public SleepStagesValues getLight() {
        return light;
    }

    public void setLight(SleepStagesValues light) {
        this.light = light;
    }

    public SleepStagesValues getRem() {
        return rem;
    }

    public void setRem(SleepStagesValues rem) {
        this.rem = rem;
    }

    public SleepStagesValues getWake() {
        return wake;
    }

    public void setWake(SleepStagesValues wake) {
        this.wake = wake;
    }
}
