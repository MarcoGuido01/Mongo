
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
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
public class Query {

    private MongoClient mongo;
    private DB db;
    
    public Query() {
        this.mongo = new MongoClient( "localhost" , 27017 );
        this.db = mongo.getDB("FITBIT");
    }
    
    public void findHRWhenSleep (int id) {
        DBCollection collection1 = db.getCollection("SleepIntraday");
        DBCollection collection2 = db.getCollection("HRIntraday");
        
        List<BasicDBObject> listquery = new ArrayList<BasicDBObject>();
        List<String> list = new ArrayList<String>();
        list.add("asleep");
        list.add("deep");
        list.add("rem");
        list.add("light");
        BasicDBObject querysleep = new BasicDBObject("value", new BasicDBObject("$in",list));
        BasicDBObject idpaziente = new BasicDBObject("id", id);
        listquery.add(idpaziente);
        listquery.add(querysleep);
        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", listquery);
        
        BasicDBObject idquery = new BasicDBObject();
        idquery.put("id", id);
//        DBCursor cursor = collection2.find(idquery);
//        List<DBObject> listaHR = collection2.find(idquery).toArray();
        
        
        List<DBObject> listaSleep = collection1.find(andQuery).toArray();
        
        List<Long> listaDate = new ArrayList<Long>();
        for (DBObject obj : listaSleep) {
            long l = (long) obj.get("date");
            listaDate.add(l);
        }
        
        BasicDBObject longQuery = new BasicDBObject("date", new BasicDBObject("$in", listaDate));
//così ho le date del sonno in 30sec e quelle dell'hr al min, quindi non combacieranno tutti e perdo il livello di dettaglio di 30sec
        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
        query.add(idquery);
        query.add(longQuery);
        BasicDBObject andQueryLong = new BasicDBObject();
        andQueryLong.put("$and", query);
        List<DBObject> listaHR = collection2.find(andQueryLong).toArray();
        
        for(DBObject obj : listaHR) {
            System.out.println(obj.toString());
        
        }
        
//        for(DBObject obj : listaHR) {
//            
//        }
//        int a = 0;
        
//        for(int i=0; i<listaSleep.size(); i++) {
//            long dataSleep = (long) listaSleep.get(i).get("date");
//            for(int j=0; j<listaHR.size(); j++) {
//                long dataHR = (long) listaHR.get(j).get("date");
//            }
//        }
        
//        DBCursor cursor1 = collection1.find(andQuery);
        
//	while (cursor1.hasNext()) {
//		DBObject obj1 = cursor1.next();
//                int data = (int) obj1.get("date");
//                while(cursor.hasNext()) {
//                    DBObject obj2 = cursor.next();
//                    int data2 = (int) obj1.get("date");
//                    if(data==data2) {
//                        
//                    }
//                }
                
    }
    
    public void findHRinEverySingleSleep (int id) {
        DBCollection collection1 = db.getCollection("SingleSleepSummary");
        DBCollection collection2 = db.getCollection("HRIntraday");
        DBCollection collection3 = db.getCollection("SleepIntraday");
        BasicDBObject idpaziente = new BasicDBObject("id", id);
        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
        List<DBObject> listaSleep = collection1.find(idpaziente).toArray();
        for(int i=0; i<listaSleep.size(); i++) {
            long datainizio = (long) listaSleep.get(i).get("startTime");
            long datafine = (long) listaSleep.get(i).get("endTime");
            query.add(idpaziente);
            BasicDBObject gteQuery = new BasicDBObject();
            gteQuery.put("date", new BasicDBObject("$gte", datainizio).append("$lt", datafine));
            query.add(gteQuery);
            BasicDBObject andQuery = new BasicDBObject();
            andQuery.put("$and", query);
            List<DBObject> listaHR = collection2.find(andQuery).toArray();
            
            
            List<Long> listaDate = new ArrayList<Long>();
            for (DBObject obj : listaHR) {
                long l = (long) obj.get("date");
                listaDate.add(l);
            }
            
            BasicDBObject longQuery = new BasicDBObject("date", new BasicDBObject("$in", listaDate));
            List<BasicDBObject> querylist = new ArrayList<BasicDBObject>();
            query.add(idpaziente);
            query.add(longQuery);
            BasicDBObject andQueryLong = new BasicDBObject();
            andQueryLong.put("$and", query);
            List<DBObject> listaSleepIntraday = collection3.find(andQueryLong).toArray();
            
//            List<DBObject> listaSleepIntraday = collection3.find(andQuery).toArray();
            
            for(int j=0; j<listaSleepIntraday.size(); j++) {
                listaHR.get(j).put("valueSleep", listaSleepIntraday.get(j).get("value"));
                System.out.println(listaHR.get(j).toString());    
            }
            
        }
    }
}

    

