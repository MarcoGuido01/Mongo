
import deserializer.SleepIntradayDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;
import SingleSleepSummary.SingleSleep;
import SingleSleepSummary.SingleSleepSummaryClassic;
import SingleSleepSummary.SingleSleepSummaryStages;
import SingleSleepSummary.dataType;
import SleepLevel.FitbitSleepIntra;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class testerSleep {
    
    public static void main (String[] args) throws IOException {
        File json = new File("sleepshort.json");
        int id = 10;
        SleepIntradayDeserializer tsi = new SleepIntradayDeserializer();
        tsi.deserialize(json.toString(), id);
        for(FitbitSleepIntra s30 : tsi.getLista()) {
            System.out.println(s30.getSleep30secondsAsString());
        }
//        for(Sleep30seconds s30: tsi.getListashort()) {
//            System.out.println(s30.getSleep30secondsAsString());
//        }
    }
}
        
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(json);
//        
//        ArrayNode arraysonno = (ArrayNode) root.findValue("sleep");
//        if(arraysonno.size()!=0) {
//            for(JsonNode j : arraysonno) {
//                int duration = j.findValue("duration").asInt();
//                int efficiency = j.findValue("efficiency").asInt();
//                int minutesafterwakeup = j.findValue("minutesAfterWakeup").asInt();
//                int minutesasleep = j.findValue("minutesAsleep").asInt();
//                int minutesawake = j.findValue("minutesAwake").asInt();
//                int minutestofallasleep = j.findValue("minutesToFallAsleep").asInt();
//                int timeinbed = j.findValue("timeInBed").asInt();
//                int infocode = j.findValue("infoCode").asInt();
//                String startime = j.findValue("startTime").asText();
//                String endtime = j.findValue("endTime").asText();
//                Boolean ismainsleep = j.findValue("isMainSleep").asBoolean();
//                dataType type = dataType.valueOf(j.findValue("type").asText());
//                JsonNode summary = j.findPath("summary");
//            
//                if(type.toString().equals("classic")) {
//                    SingleSleepSummaryClassic ssc = mapper.readValue(summary.toString(), SingleSleepSummaryClassic.class);
//                    SingleSleep single = new SingleSleep(id, castDate(startime), castDate(endtime), duration, efficiency, minutesafterwakeup, minutesasleep, minutesawake,minutestofallasleep,timeinbed,infocode, ismainsleep, type, ssc);
//                    single.setSingleSleepAsString(mapper.writeValueAsString(single));
//                    
//                }
//                else {
//                    SingleSleepSummaryStages sss = mapper.readValue(summary.toString(), SingleSleepSummaryStages.class);
//                    SingleSleep single = new SingleSleep(id, castDate(startime), castDate(endtime), duration, efficiency, minutesafterwakeup, minutesasleep, minutesawake, minutestofallasleep, timeinbed, infocode, ismainsleep,type, sss);
//                    single.setSingleSleepAsString(mapper.writeValueAsString(single));
//                    
//                }
//            }
//        }
//    }
//        File json = new File("outputNEWSLEEP.json");
//        int id = 10;
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(json);
//        List<SingleSleep> listasinglesleep = new ArrayList<>();
//        ArrayNode arraysonno = (ArrayNode) root.findValue("sleep");
//        for(JsonNode j : arraysonno) {
//            int duration = j.findValue("duration").asInt();
//            int efficiency = j.findValue("efficiency").asInt();
//            int minutesafterwakeup = j.findValue("minutesAfterWakeup").asInt();
//            int minutesasleep = j.findValue("minutesAsleep").asInt();
//            int minutesawake = j.findValue("minutesAwake").asInt();
//            int minutestofallasleep = j.findValue("minutesToFallAsleep").asInt();
//            int timeinbed = j.findValue("timeInBed").asInt();
//            int infocode = j.findValue("infoCode").asInt();
//            String startime = j.findValue("startTime").asText();
//            String endtime = j.findValue("endTime").asText();
//            Boolean ismainsleep = j.findValue("isMainSleep").asBoolean();
//            dataType type = dataType.valueOf(j.findValue("type").asText());
//            SingleSleep single = new SingleSleep(id, castDate(startime), castDate(endtime), duration, efficiency, minutesafterwakeup, minutesasleep, minutesawake,minutestofallasleep,timeinbed,infocode,ismainsleep,type);
//            single.setSingleSleepAsString(mapper.writeValueAsString(single));
//            listasinglesleep.add(single);
//        }
//    
    
    
    

