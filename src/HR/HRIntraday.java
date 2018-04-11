/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HR;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class HRIntraday {
    
    private int id;
    private int value;
    private Date date;
    private String HRIntradayAsString;

    public HRIntraday(int id, Date date, int value) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public String getHRIntradayAsString() {
        return HRIntradayAsString;
    }

    public void setHRIntradayAsString(String HRIntradayAsString) {
        this.HRIntradayAsString = HRIntradayAsString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
