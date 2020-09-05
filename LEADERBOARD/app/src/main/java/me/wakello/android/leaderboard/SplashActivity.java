package me.wakello.android.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.SplashTheme);
        setTheme(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        //Execute Async task to get Data from the web API while splash screen is displayed and is doing nothing.
        new LeadersQueryTask().execute();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public class LeadersQueryTask extends AsyncTask<Void, Void, ArrayList<String>>{
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            //The two API paths.
            String learningApiPath = "/api/hours";
            String skillIqApiPath = "/api/skilliq";

            //Array list to get the two JSON strings we expect (One for Learning leaders and the other for Skill IQ leaders)
            ArrayList<String> jsonResult = new ArrayList<>();
            try {
                //Build the twu URLs (For Learning and Skill IQ leaders APIs)
                URL learningApiUrl = ApiUtil.buildUrl(learningApiPath);
                URL skillIqApiUrl = ApiUtil.buildUrl(skillIqApiPath);

                //Get the JSON result from the two APIs. Learning leaders will be the first hence index 0 while Skill IQ will be index 1
                jsonResult.add(ApiUtil.getJson(learningApiUrl));
                jsonResult.add(ApiUtil.getJson(skillIqApiUrl));

            }
            catch (Exception e){
                Log.d("LeaderBoard Error: ", e.toString());
            }

            //Return the result
            return jsonResult;
        }

        //After successfully getting the data from web API, we need to update our DataManager
        protected void onPostExecute(ArrayList<String> result){
            String learningLeaderString = "hours";
            String skillIqLeaderString = "score";

            //Convert the JSON string array to Leader array and use the same to update DataManager fields
            ArrayList<Leader> learningLeaders = ApiUtil.getLeadersFromJson(result.get(0), learningLeaderString);
            DataManager.getInstance().setLearningLeaders(learningLeaders);
            ArrayList<Leader> skillIqLeaders = ApiUtil.getLeadersFromJson(result.get(1), skillIqLeaderString);
            DataManager.getInstance().setSkillIqLeaders(skillIqLeaders);

            //After successfully getting the data from web API and updating DataManager, we now need to call the MainActivity.
            Intent intent=new Intent(SplashActivity.this, LeaderBoardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}