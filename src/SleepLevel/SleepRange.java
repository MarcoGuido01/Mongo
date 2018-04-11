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
public class SleepRange {
    
    private Date date;
    private int id;
    private String value;
    private int seconds;
    private String sleeprangeAsString;

    public SleepRange(int id, Date date, String value, int seconds) {
        this.date = date;
        this.id = id;
        this.value = value;
        this.seconds = seconds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setSleeprangeAsString(String sleeprangeAsString) {
        this.sleeprangeAsString = sleeprangeAsString;
    }

    public String getSleeprangeAsString() {
        return sleeprangeAsString;
    }
}
