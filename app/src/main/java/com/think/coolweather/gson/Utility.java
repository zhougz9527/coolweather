package com.think.coolweather.gson;

import android.text.TextUtils;

import com.think.coolweather.db.City;
import com.think.coolweather.db.County;
import com.think.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Think on 2016/12/27.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i <allProvince.length() ; i++) {
                    JSONObject provinceObject = allProvince.optJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.optString("name"));
                    province.setProvinceCode(provinceObject.optInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i <allCity.length() ; i++) {
                    JSONObject cityObject = allCity.optJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.optString("name"));
                    city.setCityCode(cityObject.optInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i <allCounties.length() ; i++) {
                    JSONObject countyObject = allCounties.optJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.optString("name"));
                    county.setWeatherId(countyObject.optString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
