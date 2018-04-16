/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryObject;

/**
 *
 * @author Marco
 */
public class HRwhenSleep {
    
    private long date;
    private String valueSleep;
    private int valueHR;

    public HRwhenSleep(long date, String valueSleep, int valueHR) {
        this.date = date;
        this.valueSleep = valueSleep;
        this.valueHR = valueHR;
    }

    public long getDate() {
        return date;
    }

    public String getValueSleep() {
        return valueSleep;
    }

    public int getValueHR() {
        return valueHR;
    }
    
    
}
