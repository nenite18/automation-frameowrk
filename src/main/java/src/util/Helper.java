package src.util;

import src.config.Constant;

public class Helper {
    /**
     * Checks if the given latitude and longitude are within the FanCode city boundaries.
     *
     * @param lat the latitude to check
     * @param lng the longitude to check
     * @return true if the latitude and longitude are within the FanCode city boundaries, false otherwise
     */
    public boolean checkFanCodeCity(double lat, double lng) {
        return Constant.LAT_START <= lat && lat <= Constant.LAT_END && Constant.LNG_START <= lng && lng <= Constant.LNG_END;
    }

    /**
     * Calculates the percentage of num1 in num2.
     *
     * @param num1 the numerator
     * @param num2 the denominator
     * @return the percentage of num1 in num2, or 0 if num1 or num2 is 0
     */
    public double calPercentage(double num1, double num2) {
        if(num1 > 0 && num2 > 0) {
           return (num1*100)/num2;
        }
        return 0;
    }
}

