/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SingleSleepSummary;

import DailySleepSummary.SleepStatus;

/**
 *
 * @author Marco
 */
public class SingleSleepSummaryClassic extends SingleSleepSummary {
    
    private SleepStatus asleep;
    private SleepStatus awake;
    private SleepStatus restless;

    public SleepStatus getAsleep() {
        return asleep;
    }

    public void setAsleep(SleepStatus asleep) {
        this.asleep = asleep;
    }

    public SleepStatus getAwake() {
        return awake;
    }

    public void setAwake(SleepStatus awake) {
        this.awake = awake;
    }

    public SleepStatus getRestless() {
        return restless;
    }

    public void setRestless(SleepStatus restless) {
        this.restless = restless;
    }
    
    
}
