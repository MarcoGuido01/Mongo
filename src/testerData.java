
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class testerData {
    
    
    public static void main (String[] args) throws ParseException, JsonProcessingException {
//    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        System.out.println(Locale.getDefault());
//    df.setTimeZone(TimeZone.getTimeZone("UTC"));
 
//    String toParse = "2015-08-15'T'15:45:28.000'Z'";
//    Date date = df.parse(toParse);
//    
// 
//    ObjectMapper mapper = new ObjectMapper();
    
//    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    mapper.setDateFormat(new ISO8601DateFormat());
//    mapper.addSerializer(Date.class, new IsoDateSerializer());
//    String s = mapper.writeValueAsString(date);
    
    
   
                
                
//    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
// 
//    String toParse = "20-12-2014 02:30:00";
//    Date date = df.parse(toParse);
//    ClasseProva cp = new ClasseProva(date);
// 
//    ObjectMapper mapper = new ObjectMapper();
//     MongoClient mongo = new MongoClient( "localhost" , 27017 );
//                DB db = mongo.getDB("FITBIT");
//                DBCollection collection = db.getCollection("SleepDailySummary"); 
//                DBObject dbObject = (DBObject)JSON.parse(mapper.writeValueAsString(cp));
//                collection.insert(dbObject);
    }
}
