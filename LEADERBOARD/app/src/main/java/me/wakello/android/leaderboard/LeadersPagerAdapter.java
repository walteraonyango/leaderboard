package me.wakello.android.leaderboard;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LeadersPagerAdapter extends FragmentPagerAdapter {
    //private Context mContext;
    private int mTabCount;
    private Fragment[] leadersFragments;

    public LeadersPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mTabCount = behavior;
        leadersFragments = new Fragment[] {new LearningLeaders(), new SkillIQLeaders()};
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return leadersFragments[position];
    }

    @Override
    public int getCount() {
        return leadersFragments.length;
    }

    @Nullable
    @Override
    //Update titles for the various TABS
    public CharSequence getPageTitle(int position) {
        String title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
