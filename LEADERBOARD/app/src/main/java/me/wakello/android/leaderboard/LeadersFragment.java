package me.wakello.android.leaderboard;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeadersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private int mLeaderType;
    private int mDefaultIcon;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public LeadersFragment(int leaderType, int defaultIcon) {
        mLeaderType = leaderType;
        mDefaultIcon = defaultIcon;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningLeaders.
     */
    // TODO: Rename and change types and number of parameters
    public static LeadersFragment newInstance(int leaderType, int defaultIcon) {
        LeadersFragment fragment = new LeadersFragment(leaderType, defaultIcon);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, leaderType);
        args.putInt(ARG_PARAM2, defaultIcon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLeaderType = getArguments().getInt(ARG_PARAM1);
            mDefaultIcon = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(mLayoutResource, container, false);
        View view = inflater.inflate(R.layout.fragment_leaders, container, false);
        mRecyclerView = view.findViewById(R.id.rvLeaders);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager leadersLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(leadersLayoutManager);
        //String leaderString = "learning hours";         //display based on learning hours

        Resources res = getResources();
        String[] displayDetails = res.getStringArray(R.array.display_details);

        String leaderString = displayDetails[mLeaderType];         //display based on learning hours
        ArrayList<Leader> leaders;
        if(mLeaderType == Constants.LEARNING_LEADERS)
            leaders = DataManager.getInstance().getLearningLeaders();     //Get array list of learning leaders from DataManager
        else
            leaders = DataManager.getInstance().getSkillIqLeaders();     //Get array list of skill iq leaders from DataManager

        //TODO drawable icon
        mRecyclerView.setAdapter(new LeadersRecyclerAdapter(leaders, leaderString, mDefaultIcon));
        return view;
    }
}