/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import deserializer.SleepIntradayDeserializer;
import java.util.List;

import java.util.ArrayList;

import java.util.Date;



import backingBean.FitbitBean;
import backingDAO.FitbitDataDAO;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import constant.FitbitConstants;

import managers.QueryParameter;

import util.FitbitInfoUtil;

import managers.FitbitManager;

import managers.ManagerDB;

import constant.Query;
import deserializer.HRDailySummaryDeserializer;
import deserializer.HRIntradayDeserializer;
import deserializer.SingleSleepSummaryDeserializer;
import deserializer.SleepDailySummaryDeserializer;

import domain.FitbitApplication;
import fitbit.RequestFitbitData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import noSQLConstants.Constants;
import HR.HRIntraday;
import SingleSleepSummary.SingleSleep;
import SleepLevel.FitbitSleepIntra;
import SleepLevel.SleepRange;
import deserializer.SleepIntradayDeserializerNOSHORT;
import deserializer.SleepRangeDeserializer;

/**
 *
 * @author Marco
 */
public class FitbitTask {

    

	public static void main (String[] args) throws Exception {

//		int nPat = 35;

		FitbitApplication patient;

		ArrayList<QueryParameter> queryParameters = new ArrayList<>();

		System.out.println("------- Bot task START at " + new Date() + " -------");

		List<Object> patientsList = ManagerDB.getObjectList(Query.GET_PAT, queryParameters);

		System.out.println("*** Downloading forward data ***" +patientsList.size());

		for (int i = 0; i < patientsList.size(); i++) {

			patient = (FitbitApplication) patientsList.get(i);
			
			if(FitbitManager.getDevicesFromFitbit(patient.getId_patient())){
  
                            String token = FitbitDataDAO.getUserToken(patient.getId_patient());
                            RequestFitbitData req = new RequestFitbitData();
                            
                            String jsonHR = req.getData(String.format(Constants.requestHRmin, "2016-05-16", "00:00:00", "23:59:59"), token);
                            String jsonSleep = req.getData(String.format(Constants.requestSleep, "2016-05-16"), token);
                            
                            storeHRDailySummary(jsonHR,patient.getId_patient());
                            storeHRIntraday(jsonHR, patient.getId_patient());
                            
                            storeSleepDailySummary(jsonSleep, patient.getId_patient());
                            storeSingleSleepSummary(jsonSleep, patient.getId_patient());
                            storeSleepRange(jsonSleep, patient.getId_patient());
                            storeSleepIntraday(jsonSleep, patient.getId_patient());

                            System.out.println("* end  patient " + patient.getId_patient() + " *");

			}
		}

		
		System.out.println("------- Bot task END at " + new Date() + " -------");



	}
        
        public static void storeHRDailySummary(String json, int id) {
            HRDailySummaryDeserializer deserializer = new HRDailySummaryDeserializer();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(testerDeserializerHRDaily.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String stringa = deserializer.getHRDailySummaryAsString();
            if(stringa!=null) {
            
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("HRDailySummary"); 
                DBObject dbObject = (DBObject)JSON.parse(stringa);
			
                collection.insert(dbObject);
            }
        }

        public static void storeHRIntraday(String json, int id) {
            HRIntradayDeserializer deserializer = new HRIntradayDeserializer();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(testerDeserializerHRDaily.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            List<HRIntraday> lista = deserializer.getListafinale();
            if(!lista.isEmpty()) {
            
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("HRIntraday"); 
                    
                for(int i=0; i<lista.size(); i++) {
                    String stringa = lista.get(i).getHRIntradayAsString();
                    DBObject dbObject = (DBObject)JSON.parse(stringa);
                    collection.insert(dbObject);
                }
            }
	}
        
        public static void storeSleepDailySummary(String json, int id) {
            SleepDailySummaryDeserializer deserializer = new SleepDailySummaryDeserializer();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(FitbitTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String stringa = deserializer.getSleepDailySummaryDeserializerAsString();
            if(stringa!=null) {
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("SleepDailySummary"); 
                DBObject dbObject = (DBObject)JSON.parse(stringa);
                collection.insert(dbObject);
            }
        }
        
        public static void storeSingleSleepSummary(String json, int id) {
            SingleSleepSummaryDeserializer deserializer = new SingleSleepSummaryDeserializer();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(FitbitTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<SingleSleep> lista = deserializer.getListasinglesleep();
            if(!lista.isEmpty()) {
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("SingleSleepSummary"); 
      
                for(int i=0; i<lista.size(); i++) {
                    String stringa = lista.get(i).getSingleSleepAsString();
                    DBObject dbObject = (DBObject)JSON.parse(stringa);
                    collection.insert(dbObject);
                }
            }
        }
        
        public static void storeSleepRange(String json, int id) {
            SleepRangeDeserializer deserializer = new SleepRangeDeserializer();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(FitbitTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<SleepRange> lista = deserializer.getListasleep();
            if(!lista.isEmpty()) {
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("SleepRange"); 
                    
                for(int i=0; i<lista.size(); i++) {
                    String stringa = lista.get(i).getSleeprangeAsString();
                    DBObject dbObject = (DBObject)JSON.parse(stringa);
                    collection.insert(dbObject);
                }
            }
        }
        
        public static void storeSleepIntraday(String json, int id) {
            SleepIntradayDeserializerNOSHORT deserializer = new SleepIntradayDeserializerNOSHORT();
            try {
                deserializer.deserialize(json, id);
            } catch (IOException ex) {
                Logger.getLogger(FitbitTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<FitbitSleepIntra> lista = deserializer.getLista();
            if(!lista.isEmpty()) {
                MongoClient mongo = new MongoClient( "localhost" , 27017 );
                DB db = mongo.getDB("FITBIT");
                DBCollection collection = db.getCollection("SleepIntraday"); 
                    
                for(int i=0; i<lista.size(); i++) {
                    String stringa = lista.get(i).getSleep30secondsAsString();
                    DBObject dbObject = (DBObject)JSON.parse(stringa);
                    collection.insert(dbObject);
                }
            }
        }
}
