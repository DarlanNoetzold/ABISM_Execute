/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author darlan
 */
public class JsonTreatment {
    public String getAccessToken(String token) throws JSONException{
        JSONObject jsonObject = new JSONObject();
	JSONParser parser = new JSONParser();
	String access;
        try{
            jsonObject = (JSONObject) parser.parse(token);
            access = (String) jsonObject.get("access_token");
            return access;
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
	}
        
    }
    public void createWDJson(String wd) throws IOException, ParseException{
        File file=new File("/home/darlan/testeAbism/WeatherData.json.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
            
            
        fileWriter.write(wd.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
