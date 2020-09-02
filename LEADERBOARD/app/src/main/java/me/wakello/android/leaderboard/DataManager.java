package me.wakello.android.leaderboard;

import java.util.ArrayList;

public class DataManager {
    private static DataManager ourInstance = null;
    private ArrayList<Leader> mLearningLeaders = new ArrayList<>();
    private ArrayList<Leader> mSkillIqLeaders = new ArrayList<>();

    public static DataManager getInstance(){
        if(ourInstance == null){
            ourInstance = new DataManager();

        }
        return ourInstance;
    }

    public ArrayList<Leader> getLearningLeaders() {
        return mLearningLeaders;
    }

    public void setLearningLeaders(ArrayList<Leader> mLearningLeaders) {
        this.mLearningLeaders = mLearningLeaders;
    }

    public ArrayList<Leader> getSkillIqLeaders() {
        return mSkillIqLeaders;
    }

    public void setSkillIqLeaders(ArrayList<Leader> mSkillIqLeaders) {
        this.mSkillIqLeaders = mSkillIqLeaders;
    }

}
