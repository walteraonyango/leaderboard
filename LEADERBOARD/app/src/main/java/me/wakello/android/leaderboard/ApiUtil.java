package me.wakello.android.leaderboard;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";

    //Method to build the URL that we are going to use.
    public static URL buildUrl(String path){
        String fullURL = BASE_API_URL+path;
        URL url = null;
        try{
            url = new URL(fullURL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    //Method to get JSON string from the Web API
    public static String getJson(URL url) {
        HttpsURLConnection connection;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                connection.disconnect();
                return null;
            }
        }
        catch (Exception e){
            Log.d("LeaderBoard Error: ", e.toString());
            return null;
        }
    }

    //Method to get an array of leaders from the JSON string. We got the JSON string from the web API
    public static ArrayList<Leader> getLeadersFromJson(String jsonString, String leaderType){
        //JSON string keys
        final String NAME = "name";
        final String COUNTRY = "country";
        final String BADGE_URL = "badgeUrl";
        final String HOURS = "hours";
        final String SCORE="score";

        //The array list we are building
        ArrayList<Leader> leaders = new ArrayList<>();
        try {
            //Create a JSON Array using the JSON string
            JSONArray jsonArray = new JSONArray(jsonString);

            //Get the number of elements in the JSON array
            int numberOfLeaders = jsonArray.length();

            //For each element in the array, get the required fields and update to the leaders array list
            for (int i =0; i<numberOfLeaders;i++){
                JSONObject leaderJSON = jsonArray.getJSONObject(i);
                String name = leaderJSON.getString(NAME);
                String scoreHours = leaderJSON.getString(leaderType);
                String country = leaderJSON.getString(COUNTRY);
                String badgeUrl = leaderJSON.getString(BADGE_URL);
                leaders.add(new Leader(name, scoreHours, country, badgeUrl));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //Return the array list of leaders
        return leaders;
    }
}
