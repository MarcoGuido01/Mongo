/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import HR.HRDailySummary;
import SleepLevel.FitbitSleepIntra;
import SleepLevel.SleepRange;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class SleepIntradayDeserializerNOSHORT {
    
    private List<FitbitSleepIntra> lista;
    
    public void deserialize(String json, int id) throws IOException {
        
        this.lista = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        ArrayNode arraysonno = (ArrayNode) root.findValue("sleep");
        
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        for(JsonNode node : arraysonno) {
            ArrayNode array = (ArrayNode) node.findValue("data");
            
            for(int i=0; i<array.size(); i++) {
                String date1string = array.get(i).findValue("dateTime").asText();
                Date date = castDate(date1string);
                int date1 = (int) (date.getTime()/1000);
                int seconds = array.get(i).findValue("seconds").asInt();
                int ref = seconds/60;
                String value = array.findValue("level").asText();
                
                for(int j=0; j<ref; j++) {
                    int a = date1 + (60*j);
                    Date d = secondsToDate(a);
                    FitbitSleepIntra s30= new FitbitSleepIntra(id,d,value);
                    s30.setSleep30secondsAsString(mapper.writeValueAsString(s30));   
                    lista.add(s30);
                }
            }
        }
    }
    
    private java.util.Date castDate (String datastringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        try {
            java.util.Date date = sdf.parse(datastringa);
            
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(HRDailySummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private java.util.Date secondsToDate (int a) {
        long seconds = a;
        long millis = seconds*1000;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String formattedDate = sdf.format(date);
        
        return castDate(formattedDate);
  
    }

    public List<FitbitSleepIntra> getLista() {
        return lista;
    }

}
