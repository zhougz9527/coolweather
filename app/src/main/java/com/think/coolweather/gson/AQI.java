package com.think.coolweather.gson;

/**
 * Created by Think on 2016/12/28.
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
