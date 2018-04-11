
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class tester {
    
//    public static void main(String[] args) throws IOException {
//        
//        byte[] jsonData = Files.readAllBytes(Paths.get("outputNEWSLEEP.json"));
//
//        //create ObjectMapper instance
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        //read JSON like DOM Parser
//        JsonNode rootNode = objectMapper.readTree(jsonData);
//        JsonNode summary = rootNode.at("/summary");  //CON QUESTO PRENDE SOLO LA CHIAVE CHE TROVA, SENZA CONSIDERARE SOTTONODI O ARRAY
//        System.out.println(summary);
//        //JsonNode dateOfSleep = rootNode.findPath("dateOfSleep");
//        ArrayNode tutto = (ArrayNode) rootNode.path("sleep");
//        //JsonNode tutto2 = rootNode.get(0);
//        
//        for (JsonNode singoladormita: tutto) {
//            ObjectNode singoladormitaobject = (ObjectNode) singoladormita;
//            List<String> lista = new ArrayList<>();
//            lista.add("dateOfSleep");
//            lista.add("startTime");
//            lista.add("endTime");
//            lista.add("summary");
//            singoladormitaobject.retain(lista);
//            JsonNode s = (JsonNode) singoladormitaobject;
//            System.out.println(s);
//        }
//        
//        
//        
//        
////        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        
//        /**** Get database ****/
//	// if database doesn't exists, MongoDB will create it for you
////        DB db = mongo.getDB("databasepazienti");
//        
//        /**** Get collection / table from 'testdb' ****/
//	// if collection doesn't exists, MongoDB will create it for you
////        DBCollection sonno_summary = db.getCollection("sonno_summary");
//        
//        
//        
////        for(JsonNode singoladormita: tutto) {
////               System.out.println(singoladormita.);
//////            DBObject bson = ( DBObject ) JSON.parse(singoladormita.asText());
////            
//////                sonno_summary.insert(bson);
////        }
//            
////            JsonNode dateOfSleep = singoladormita.findPath("dateOfSleep");
////            JsonNode startTime = singoladormita.findValue("startTime");
////            JsonNode endTime = singoladormita.findValue("endTime");
////            System.out.println("dateOfSleep = "+dateOfSleep.asText());
////            System.out.println("startTime = "+startTime.asText());
//        
////          JsonNode summary= rootNode.findParent("summary");         CON QUESTO PRENDE IL PRIMO SUMMARY CHE TROVA E PRENDE ANCHE IL SUO NODO DA CUI DISCENDE, CIOè DATA CON TUTTI I LIVELLI
////        List<JsonNode> summary = rootNode.findValues("summary");  CON QUESTO PRENDE TUTTI I SUMMARY CHE LEGGE
////        System.out.println("summary = "+summary.asText());
////        System.out.println("dateOfSleep = "+summary.asText());
//    }
}
