/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import HR.HRDailySummary;
import HR.HRDataValue;
import HR.HRIntraday;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 *
 * @author Marco
 */
public class HRIntradayDeserializer {

    private List<HRIntraday> listafinale;

    public void deserialize(File json, int id) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        JsonNode root = mapper.readTree(json);
        String datestring = root.findValue("dateTime").asText();    
//        Date date = castDate(datestring);
        if(datestring!=null) {

            JsonNode array = root.findPath("dataset");
            List<HRDataValue> listavalori = Arrays.asList(mapper.readValue(array.toString(), HRDataValue[].class));
            this.listafinale = new ArrayList<>();
            mapper.setSerializationInclusion(Include.NON_NULL);
        
            for(int i=0; i<listavalori.size(); i++) {
                listavalori.get(i).setTime(componiData(listavalori.get(i).getTime(),datestring));
                Date date = castDate(listavalori.get(i).getTime());
                HRIntraday hrid = new HRIntraday(id, date, listavalori.get(i).getValue());
                hrid.setHRIntradayAsString(mapper.writeValueAsString(hrid));
                this.listafinale.add(hrid);  
            }
        }
    }

    public void deserialize(String json, int id) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        String datestring = root.findValue("dateTime").asText();    
//        Date date = castDate(datestring);
        if(datestring!=null) {

            JsonNode array = root.findPath("dataset");
            List<HRDataValue> listavalori = Arrays.asList(mapper.readValue(array.toString(), HRDataValue[].class));
            this.listafinale = new ArrayList<>();
            mapper.setSerializationInclusion(Include.NON_NULL);
        
            for(int i=0; i<listavalori.size(); i++) {
                listavalori.get(i).setTime(componiData(listavalori.get(i).getTime(),datestring));
                Date date = castDate(listavalori.get(i).getTime());
                HRIntraday hrid = new HRIntraday(id, date, listavalori.get(i).getValue());
                hrid.setHRIntradayAsString(mapper.writeValueAsString(hrid));
                this.listafinale.add(hrid);  
            }
        }
    }
    
    public List<HRIntraday> getListafinale() {
        return listafinale;
    }
    
    
    
    private String componiData(String ora, String giorno) {
        String s = giorno +"T" +ora +".000";
        return s;
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
}
