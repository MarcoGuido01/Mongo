/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SleepLevel;

import java.util.Date;

/**
 *
 * @author Marco
 */
public class FitbitSleepIntra {
    
    private int id;
    private Date date;
    private String value;
    private String sleep30secondsAsString;

    public FitbitSleepIntra(int id, Date date, String value) {
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSleep30secondsAsString() {
        return sleep30secondsAsString;
    }

    public void setSleep30secondsAsString(String sleep30secondsAsString) {
        this.sleep30secondsAsString = sleep30secondsAsString;
    }
    
    
}
