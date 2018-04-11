
import deserializer.HRIntradayDeserializer;
//import deserializer.HRDailySummaryDeserializer;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class testerHRDailySummary {
    
    public static void main (String[] args) throws IOException {
        
        File json = new File("outputHEARTRATE.json");
        int id = 666;
        
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(HRDailySummary.class, new HRDailySummaryDeserializer());
//        mapper.registerModule(module);
// 
//        HRDailySummary readValue = mapper.readValue(json, HRDailySummary.class);
        
        HRIntradayDeserializer hrid = new HRIntradayDeserializer();
        hrid.deserialize(json, id);
        
       
        
        
//        Data1 data = new Data1(data1);
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        DB db = mongo.getDB("provadata");
//        DBCollection collection = db.getCollection("data1"); 
        
//        DataObject dataobject = new DataObject(d);
//        String s = mapper.writeValueAsString(dataobject);
//        DBObject dbObject = (DBObject)JSON.parse(s);
//			
//	collection.insert(dbObject);
        
//        JsonNode prova = root.findValue("activities-heart");
//        JsonNode activities = root.at("/activities-heart");
////        JsonNode inter = activities.get(0);
//        ArrayNode inter = (ArrayNode) prova.findValue("heartRateZones");
////        System.out.println(inter2.toString());
//        List<HRZone> array = Arrays.asList(mapper.readValue(inter.toString(), HRZone[].class));
//        double value = activities.findValue("value").asDouble();
//        String date = activities.findValue("dateTime").asText();
//        Data format = new Data();
//        Date dateTime = format.castDate(date);
//        
//        int id = 5;
//        HRDailySummary hrds = new HRDailySummary(id, dateTime, array, value);
//        System.out.println(hrds.toString());
//        
////        File f = new File("HRDailySummary.json");
//        
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        DB db = mongo.getDB("FITBIT");
//        DBCollection collection = db.getCollection("HRDailySummary"); 
//        
//        String stringa = mapper.writeValueAsString(hrds);
//        DBObject dbObject = (DBObject)JSON.parse(stringa);
//			
//	collection.insert(dbObject);
        
//        JacksonDBCollection<HRDailySummary, String> coll = JacksonDBCollection.wrap(collection, HRDailySummary.class,
//        String.class);
        
        
        
//       WriteResult<HRDailySummary, String> result = coll.insert(hrds);
        
//        JsonNode custom = inter.findPath("/customHeartRateZones");
        

        
    }
    
    private java.util.Date castDate (String datastringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS");
        
        try {
            java.util.Date date = sdf.parse(datastringa);
            
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(HRDailySummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
