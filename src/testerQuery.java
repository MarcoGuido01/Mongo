
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class testerQuery {
    
    public static void main (String[] args) {
        Query q = new Query();
//        q.findHRWhenSleep(4);
        q.valueSleepMinutes(4,"2016-05-17");
        
    }
}
