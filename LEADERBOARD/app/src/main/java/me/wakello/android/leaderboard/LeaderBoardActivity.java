package me.wakello.android.leaderboard;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class LeaderBoardActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    LeadersPagerAdapter leadersPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        tabs = findViewById(R.id.tabs);
        Resources res = getResources();
        String[] tabTitles = res.getStringArray(R.array.tab_titles);

        //leadersPagerAdapter = new LeadersPagerAdapter(getSupportFragmentManager(), tabs.getTabCount(), tabTitles);
        //viewPager = findViewById(R.id.view_pager);
        //viewPager.setAdapter(leadersPagerAdapter);
        //tabs.setupWithViewPager(viewPager);
        int[] defaultIcon = {R.drawable.ic_top_learner, R.drawable.ic_skill_iq_trimmed};
        leadersPagerAdapter = new LeadersPagerAdapter(getSupportFragmentManager(), tabs.getTabCount(), tabTitles, defaultIcon);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(leadersPagerAdapter);
        tabs.setupWithViewPager(viewPager);




        Button btnSubmit = findViewById(R.id.submitButton);

        //Add onclick listener to the button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open the submit activity when submit button is clicked
                Intent intent=new Intent(LeaderBoardActivity.this,SubmitActivity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        finishAffinity();
    }*/
}