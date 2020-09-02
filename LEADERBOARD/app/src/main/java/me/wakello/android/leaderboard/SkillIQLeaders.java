package me.wakello.android.leaderboard;

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
 * Use the {@link SkillIQLeaders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeaders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    //TextView test;

    public SkillIQLeaders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillIQLeaders.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQLeaders newInstance(String param1, String param2) {
        SkillIQLeaders fragment = new SkillIQLeaders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        recyclerView = view.findViewById(R.id.rvSkillIq);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager leadersLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(leadersLayoutManager);
        //String leaderString = "skill IQ Score";                       //display based on skill IQ score
        String leaderString = getString(R.string.skill_iq_score);       //display based on skill IQ score
        ArrayList<Leader> leaders = DataManager.getInstance().getSkillIqLeaders();     //Get array list of Skill IQ leaders from DataManager
        recyclerView.setAdapter(new LeadersRecyclerAdapter(leaders, leaderString, R.drawable.ic_skill_iq_trimmed));
        return view;
    }
}