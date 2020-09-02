package me.wakello.android.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeadersRecyclerAdapter extends RecyclerView.Adapter<LeadersRecyclerAdapter.LeaderViewHolder> {
    private ArrayList<Leader> leaders;      //The array list of leaders to be displayed in the RecyclerView
    private String leaderType;              //Type of leader (learning or skill IQ)
    private int defaultBadgeUrl;            //Resource to be used as badge in case we are not able to download the badge from the web

    public LeadersRecyclerAdapter(ArrayList<Leader> leaders, String leaderType, int defaultBadgeUrl){
        this.leaders = leaders;
        this.leaderType = leaderType;
        this.defaultBadgeUrl = defaultBadgeUrl;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.leader_item, parent, false);
        return new LeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        holder.bind(leader, leaderType, defaultBadgeUrl);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }


    public static class LeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvDetails;
        public ImageView imgBadge;

        public LeaderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetails = itemView.findViewById(R.id.tv_details);
            imgBadge = itemView.findViewById(R.id.badgeUrl);
        }

        public void bind(Leader leader, String leaderType, int defaultBadgeUrl) {
            tvName.setText(leader.getName());
            tvDetails.setText(leader.getDetails(leaderType));
            if(leader.getBadgeUrl().isEmpty())
                imgBadge.setImageResource(defaultBadgeUrl);
            else
                Picasso.get().load(leader.getBadgeUrl()).placeholder(defaultBadgeUrl).into(imgBadge);
        }
    }
}
