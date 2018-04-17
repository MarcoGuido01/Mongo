
import HR.HRDailySummary;
import QueryObject.HRwhenSleep;
import QueryObject.SleepMinutes;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public List<HRwhenSleep> findHRinSleep (int id, String startTime, String endTime) {
        long intervalloInizio = castDate(startTime);
        long intervalloFine = castDate(endTime) + 86400000;   //altrimenti mi prenderebbe solo fino alle 00.00 della data impostata come endTime, invece io voglio fino alle 23.59 incluse di quella data, aggiungo quindi l'equivalente in millisec
        
        DBCollection collectionSingleSleepSummary = db.getCollection("SingleSleepSummary");
        BasicDBObject idpaziente = new BasicDBObject("id", id);
        BasicDBObject intervalloStart = new BasicDBObject();
        intervalloStart.put("startTime", new BasicDBObject("$gte", intervalloInizio).append("$lt", intervalloFine));
        BasicDBObject intervalloEnd = new BasicDBObject();
        intervalloEnd.put("endTime", new BasicDBObject("$gte", intervalloInizio).append("$lt", intervalloFine));
        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
        query.add(idpaziente);
        query.add(intervalloStart);
        query.add(intervalloEnd);
        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", query);
        List<DBObject> listaSingleSleepSummary = collectionSingleSleepSummary.find(andQuery).toArray();
        
//        intervalloScelto.put("date", new BasicDBObject("$gte", intervalloInizio).append("$lt", intervalloFine));
//        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
//        query.add(idpaziente);
//        query.add(intervalloScelto);
//        andQuery.put("$and", query);
//        
//        List<DBObject> listaSleep = collectionSleepIntraday.find(andQuery).toArray();
//        
        DBCollection collectionHRIntraday = db.getCollection("HRIntraday");
        DBCollection collectionSleepIntraday = db.getCollection("SleepIntraday");
        query.remove(intervalloStart);
        query.remove(intervalloEnd);
        
        
        List<DBObject> listaHRObj= new ArrayList<>();
        List<DBObject> listaSleepObj = new ArrayList<>();
        for(int i=0; i<listaSingleSleepSummary.size(); i++) {
            long dataInizio = (long) listaSingleSleepSummary.get(i).get("startTime");
            long dataFine = (long) listaSingleSleepSummary.get(i).get("endTime");
            BasicDBObject dataQuery = new BasicDBObject();
            dataQuery.put("date", new BasicDBObject("$gte", dataInizio).append("$lte", dataFine));
            query.add(dataQuery);
            BasicDBObject and = new BasicDBObject();
            and.put("$and", query);
            listaHRObj = collectionHRIntraday.find(and).toArray();
            
            listaSleepObj = collectionSleepIntraday.find(and).toArray();
            
        }
        List<HRwhenSleep> queryObjectList = new ArrayList<>();
        for(int j=0; j<listaHRObj.size() && j<listaSleepObj.size(); j++) {
            long dataValue = (long) listaSleepObj.get(j).get("date");
            String sleepValue = (String) listaSleepObj.get(j).get("value");
            int HRvalue = (int) listaHRObj.get(j).get("value");
            HRwhenSleep queryObject = new HRwhenSleep(dataValue, sleepValue, HRvalue);
            queryObjectList.add(queryObject);  
        }
        return queryObjectList;
    }
            
////            dataQuery.put("$eq", dataValue);
//            DBObject obj = collectionHRIntraday.find(dataQuery).one();
//            if(obj==null) {
//                continue;
//            } else
//                listaHRprova.add(obj);
//        }
     
    public List<SleepMinutes> valueSleepMinutes(int id, String date) {
        long data = castDate(date)+ 86400000;
        DBCollection collectionSingleSleepSummary = db.getCollection("SingleSleepSummary");
        BasicDBObject idpaziente = new BasicDBObject("id", id);
        BasicDBObject dataQuery = new BasicDBObject();
        dataQuery.put("endTime", new BasicDBObject("$lt", data));
        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
        query.add(idpaziente);
        query.add(dataQuery);
        BasicDBObject isMainSleep = new BasicDBObject();
        isMainSleep.put("isMainSleep", new BasicDBObject("$eq", true));
        query.add(isMainSleep);
        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", query);
        List<DBObject> listaSingleSleepSummary = collectionSingleSleepSummary.find(andQuery).toArray();
        List<SleepMinutes> queryObjectList= new ArrayList<>();
        for(int i=0; i<listaSingleSleepSummary.size();i++) {
            if(listaSingleSleepSummary.get(i).get("type").toString().equals("classic")) {
                DBObject obj = (DBObject) listaSingleSleepSummary.get(i).get("summary");
                int a=0;
            }
        }
        return null;
        
    }
        
    
    
    private long castDate (String datastringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        
        try {
            java.util.Date date = sdf.parse(datastringa);
            
            
            return date.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(HRDailySummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
//    public void findHRWhenSleep (int id) {
//        DBCollection collection1 = db.getCollection("SleepIntraday");
//        DBCollection collection2 = db.getCollection("HRIntraday");
//        
//        List<BasicDBObject> listquery = new ArrayList<BasicDBObject>();
//        List<String> list = new ArrayList<String>();
//        list.add("asleep");
//        list.add("deep");
//        list.add("rem");
//        list.add("light");
//        BasicDBObject querysleep = new BasicDBObject("value", new BasicDBObject("$in",list));
//        BasicDBObject idpaziente = new BasicDBObject("id", id);
//        listquery.add(idpaziente);
//        listquery.add(querysleep);
//        BasicDBObject andQuery = new BasicDBObject();
//        andQuery.put("$and", listquery);
//        
//        BasicDBObject idquery = new BasicDBObject();
//        idquery.put("id", id);
////        DBCursor cursor = collection2.find(idquery);
////        List<DBObject> listaHR = collection2.find(idquery).toArray();
//        
//        
//        List<DBObject> listaSleep = collection1.find(andQuery).toArray();
//        
//        List<Long> listaDate = new ArrayList<Long>();
//        for (DBObject obj : listaSleep) {
//            long l = (long) obj.get("date");
//            listaDate.add(l);
//        }
//        
//        BasicDBObject longQuery = new BasicDBObject("date", new BasicDBObject("$in", listaDate));
////così ho le date del sonno in 30sec e quelle dell'hr al min, quindi non combacieranno tutti e perdo il livello di dettaglio di 30sec
//        List<BasicDBObject> query = new ArrayList<BasicDBObject>();
//        query.add(idquery);
//        query.add(longQuery);
//        BasicDBObject andQueryLong = new BasicDBObject();
//        andQueryLong.put("$and", query);
//        List<DBObject> listaHR = collection2.find(andQueryLong).toArray();
//        
//        for(DBObject obj : listaHR) {
//            System.out.println(obj.toString());
//        
//        }
        
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
                
    
    
    
            
////            long datainizio = (long) listaSleep.get(i).get("startTime");
////            long datafine = (long) listaSleep.get(i).get("endTime");
//            query.add(idpaziente);
//            BasicDBObject gteQuery = new BasicDBObject();
////            gteQuery.put("date", new BasicDBObject("$gte", datainizio).append("$lt", datafine));
//            query.add(gteQuery);
//            andQuery.put("$and", query);
//            List<DBObject> listaHR = collectionHRIntraday.find(andQuery).toArray();
            
//            
//            List<Long> listaDate = new ArrayList<Long>();
//            for (DBObject obj : listaHR) {
//                long l = (long) obj.get("date");
//                listaDate.add(l);
//            }
//            
//            BasicDBObject longQuery = new BasicDBObject("date", new BasicDBObject("$in", listaDate));
//            List<BasicDBObject> querylist = new ArrayList<BasicDBObject>();
//            query.add(idpaziente);
//            query.add(longQuery);
//            BasicDBObject andQueryLong = new BasicDBObject();
//            andQueryLong.put("$and", query);
//            List<DBObject> listaSleepIntraday = collectionSleepIntraday.find(andQueryLong).toArray();
//            
//            List<DBObject> listaSleepIntraday = collection3.find(andQuery).toArray();
            
//            for(int j=0; j<listaSleepIntraday.size(); j++) {
//                listaHR.get(j).put("valueSleep", listaSleepIntraday.get(j).get("value"));
//                System.out.println(listaHR.get(j).toString());    
//            }
            
//        }
    
    
    
}

    

