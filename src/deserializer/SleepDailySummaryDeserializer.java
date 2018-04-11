/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;
import DailySleepSummary.SleepStages;
import DailySleepSummary.SleepDailySummary;
import DailySleepSummary.SleepDailySummaryNoStages;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.util.TimeZone;

/**
 *
 * @author Marco
 */
public class SleepDailySummaryDeserializer {
    
    private String SleepDailySummaryDeserializerAsString;
    
    public void deserialize (File json, int id) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        JsonNode datejson = root.findValue("dateOfSleep");
        if(datejson!=null) {        
            Date date = castDate(datejson.asText());
            JsonNode summary = root.get("summary");
            JsonNode stages = summary.findValue("stages");
            int totalMinutesAsleep = summary.findValue("totalMinutesAsleep").asInt();
            int totalSleepRecords = summary.findValue("totalSleepRecords").asInt();
            int totalTimeInBed = summary.findValue("totalTimeInBed").asInt();
        
            if(stages==null) {
                SleepDailySummaryNoStages sleepsummary = new SleepDailySummaryNoStages(id, date, totalMinutesAsleep, totalSleepRecords, totalTimeInBed);
                this.SleepDailySummaryDeserializerAsString = mapper.writeValueAsString(sleepsummary);
            }
            else {
                SleepStages sleepstages = mapper.readValue(stages.toString(), SleepStages.class);
                SleepDailySummary sleepsummary = new SleepDailySummary(id, date, totalMinutesAsleep, totalSleepRecords, totalTimeInBed, sleepstages);
                this.SleepDailySummaryDeserializerAsString = mapper.writeValueAsString(sleepsummary);
            }
        }
    }
    
    public void deserialize (String json, int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode datejson = root.findValue("dateOfSleep");
        if(datejson!=null) {        
            Date date = castDate(datejson.asText());
            JsonNode summary = root.get("summary");
            JsonNode stages = summary.findValue("stages");
            int totalMinutesAsleep = summary.findValue("totalMinutesAsleep").asInt();
            int totalSleepRecords = summary.findValue("totalSleepRecords").asInt();
            int totalTimeInBed = summary.findValue("totalTimeInBed").asInt();
        
            if(stages==null) {
                SleepDailySummaryNoStages sleepsummary = new SleepDailySummaryNoStages(id, date, totalMinutesAsleep, totalSleepRecords, totalTimeInBed);
                this.SleepDailySummaryDeserializerAsString = mapper.writeValueAsString(sleepsummary);
            }
            else {
                SleepStages sleepstages = mapper.readValue(stages.toString(), SleepStages.class);
                SleepDailySummary sleepsummary = new SleepDailySummary(id, date, totalMinutesAsleep, totalSleepRecords, totalTimeInBed, sleepstages);
                this.SleepDailySummaryDeserializerAsString = mapper.writeValueAsString(sleepsummary);
            }
        }
    }
    
    private java.util.Date castDate (String datastringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            java.util.Date date = sdf.parse(datastringa);
            
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(HRDailySummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getSleepDailySummaryDeserializerAsString() {
        return SleepDailySummaryDeserializerAsString;
    }
    
    
}
