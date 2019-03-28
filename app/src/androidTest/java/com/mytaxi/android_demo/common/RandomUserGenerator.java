package com.mytaxi.android_demo.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RandomUserGenerator {
    private static final String SEED = "a1f30d446f820665";
    private String username;
    private String password;

    public RandomUserGenerator() {
        try {
            create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void create() throws Exception{
        URL url = new URL("https://randomuser.me/api/?seed="+SEED);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println("\nResponse content : " + content);

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(content.toString()).getAsJsonObject();
        JsonArray resultsArray = json.get("results").getAsJsonArray();
        JsonObject resultsObject = resultsArray.get(0).getAsJsonObject();
        JsonObject loginObject = resultsObject.get("login").getAsJsonObject();
        this.username = loginObject.get("username").getAsString();
        this.password = loginObject.get("password").getAsString();

        System.out.println("\nResponse credentials : " + this.username + " : " + this.password);
    }

}
