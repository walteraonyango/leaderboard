package me.wakello.android.leaderboard;

import android.content.Intent;
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
        leadersPagerAdapter = new LeadersPagerAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(leadersPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        Button btnSubmit = findViewById(R.id.submitButton);

        //Add onclick listner to the button
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