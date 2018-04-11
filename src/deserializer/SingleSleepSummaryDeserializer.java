/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;
import SingleSleepSummary.SingleSleep;
import SingleSleepSummary.SingleSleepSummaryClassic;
import SingleSleepSummary.SingleSleepSummaryStages;
import SingleSleepSummary.dataType;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author Marco
 */
public class SingleSleepSummaryDeserializer {
   
    private List<SingleSleep> listasinglesleep;
    
    public void deserialize(String json, int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        this.listasinglesleep = new ArrayList<>();
        ArrayNode arraysonno = (ArrayNode) root.findValue("sleep");
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        if(arraysonno.size()!=0) {
            for(JsonNode j : arraysonno) {
                int duration = j.findValue("duration").asInt();
                int efficiency = j.findValue("efficiency").asInt();
                int minutesafterwakeup = j.findValue("minutesAfterWakeup").asInt();
                int minutesasleep = j.findValue("minutesAsleep").asInt();
                int minutesawake = j.findValue("minutesAwake").asInt();
                int minutestofallasleep = j.findValue("minutesToFallAsleep").asInt();
                int timeinbed = j.findValue("timeInBed").asInt();
                int infocode = j.findValue("infoCode").asInt();
                String startime = j.findValue("startTime").asText();
                String endtime = j.findValue("endTime").asText();
                Boolean ismainsleep = j.findValue("isMainSleep").asBoolean();
                dataType type = dataType.valueOf(j.findValue("type").asText());
                JsonNode summary = j.findPath("summary");
            
                if(type.toString().equals("classic")) {
                    SingleSleepSummaryClassic ssc = mapper.readValue(summary.toString(), SingleSleepSummaryClassic.class);
                    SingleSleep single = new SingleSleep(id, castDate(startime), castDate(endtime), duration, efficiency, minutesafterwakeup, minutesasleep, minutesawake,minutestofallasleep,timeinbed,infocode, ismainsleep, type, ssc);
                    single.setSingleSleepAsString(mapper.writeValueAsString(single));
                    listasinglesleep.add(single);
                }
                else {
                    SingleSleepSummaryStages sss = mapper.readValue(summary.toString(), SingleSleepSummaryStages.class);
                    SingleSleep single = new SingleSleep(id, castDate(startime), castDate(endtime), duration, efficiency, minutesafterwakeup, minutesasleep, minutesawake, minutestofallasleep, timeinbed, infocode, ismainsleep,type, sss);
                    single.setSingleSleepAsString(mapper.writeValueAsString(single));
                    listasinglesleep.add(single);
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

    public List<SingleSleep> getListasinglesleep() {
        return listasinglesleep;
    }
    
    
}
