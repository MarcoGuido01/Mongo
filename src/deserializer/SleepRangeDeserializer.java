/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import HR.HRDailySummary;
import SleepLevel.SleepRange;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class SleepRangeDeserializer {
    
    private List<SleepRange> listasleep;

    public SleepRangeDeserializer() {
        this.listasleep = new ArrayList<>();
    }
    
    public void deserialize(String json, int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        ArrayNode arraysonno = (ArrayNode) root.findValue("sleep");
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        for(JsonNode j : arraysonno) {
            ArrayNode array = (ArrayNode) j.findValue("data");
            if(array.size()!=0) {
                for(JsonNode js : array) {
                    String datestring = js.findValue("dateTime").asText();
                    Date date = castDate(datestring);
                    String value = js.findValue("level").asText();
                    int seconds = js.findValue("seconds").asInt();
                    SleepRange sleeprange = new SleepRange(id,date,value,seconds);
                    sleeprange.setSleeprangeAsString(mapper.writeValueAsString(sleeprange));
                    listasleep.add(sleeprange);
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

    public List<SleepRange> getListasleep() {
        return listasleep;
    }
    
    
}
