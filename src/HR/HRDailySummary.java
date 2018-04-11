package HR;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class HRDailySummary {
    
    private int id;
    private double value;
    private Date dateTime;
    private List<HRZone> HRZoneList;

    public HRDailySummary(int id, Date dateTime, List<HRZone> HRZoneList, double value) {
        this.id = id;
        this.value = value;
        this.dateTime = dateTime;
        this.HRZoneList = HRZoneList;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date castDate (String datastringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(datastringa);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(HRDailySummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int getId() {
        return id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    

    public List<HRZone> getHRZoneList() {
        return HRZoneList;
    }

    public void setHRZoneList(List<HRZone> HRZoneList) {
        this.HRZoneList = HRZoneList;
    }

    @Override
    public String toString() {
        return "HRDailySummary{" + "id=" + id + ", value=" + value + ", dateTime=" + dateTime + ", HRZoneList=" + HRZoneList + '}';
    }

    
    
    
}
