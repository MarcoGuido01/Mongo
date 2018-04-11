/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;
import HR.HRZone;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author Marco
 */
public class HRDailySummaryDeserializer {

    private String HRDailySummaryAsString;
    
    public HRDailySummaryDeserializer() {
    }
    
    public void deserialize(File json, int id) throws IOException {
    
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        JsonNode prova = root.findValue("activities-heart");
        ArrayNode intermedio = (ArrayNode) prova.findValue("heartRateZones");
        if(intermedio.size()!=0) {
            List<HRZone> array = Arrays.asList(mapper.readValue(intermedio.toString(), HRZone[].class));
            double value = prova.findValue("value").asDouble();
            String date = prova.findValue("dateTime").asText();
            Date dateTime = castDate(date);
            HRDailySummary hrds = new HRDailySummary(id, dateTime, array, value);
            this.HRDailySummaryAsString = mapper.writeValueAsString(hrds);
        }
    }
        
    
    public void deserialize(String json, int id) throws IOException {
    
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode prova = root.findValue("activities-heart");
        ArrayNode intermedio = (ArrayNode) prova.findValue("heartRateZones");
        if(intermedio.size()!=0) {
            List<HRZone> array = Arrays.asList(mapper.readValue(intermedio.toString(), HRZone[].class));
            double value = prova.findValue("value").asDouble();
            String date = prova.findValue("dateTime").asText();
            Date dateTime = castDate(date);
            HRDailySummary hrds = new HRDailySummary(id, dateTime, array, value);
            this.HRDailySummaryAsString = mapper.writeValueAsString(hrds);
        }
    }
    
    public String getHRDailySummaryAsString() {
        return HRDailySummaryAsString;
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
}
