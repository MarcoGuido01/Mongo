
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import deserializer.HRDailySummaryDeserializer;
//import deserializer.HRDailySummaryDeserializer;
import java.io.File;
import java.io.IOException;
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
public class testerDeserializerHRDaily {

    public static void main (String[] args) {
        File json = new File("outputHEARTRATE.json");
        int id=10;
        HRDailySummaryDeserializer deserializer = new HRDailySummaryDeserializer();
        try {
            deserializer.deserialize(json, id);
        } catch (IOException ex) {
            Logger.getLogger(testerDeserializerHRDaily.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("FITBIT");
        DBCollection collection = db.getCollection("HRDailySummary"); 
        
        String stringa = deserializer.getHRDailySummaryAsString();
        DBObject dbObject = (DBObject)JSON.parse(stringa);
			
	collection.insert(dbObject);
    }
}
