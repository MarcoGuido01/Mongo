/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noSQLConstants;

import constant.FitbitConstants;

/**
 *
 * @author Marco
 */
public class Constants extends FitbitConstants {
    
    public static final String requestSleep = "https://api.fitbit.com/1.2/user/-/sleep/date/%s.json";
    public static final String requestHRmin = "https://api.fitbit.com/1/user/-/activities/heart/date/%s/1d/1min/time/%s/%s.json";

}
