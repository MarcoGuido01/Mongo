package HR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco
 */
public class HRZone {
    
    private String name;
    private int max, min, minutes;
    private double caloriesOut;

    public String getName() {
        return name;
    }

    public double getCaloriesOut() {
        return caloriesOut;
    }

    public void setCaloriesOut(double caloriesOut) {
        this.caloriesOut = caloriesOut;
    }

    

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    
}
