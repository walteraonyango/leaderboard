package me.wakello.android.leaderboard;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LeadersPagerAdapter extends FragmentPagerAdapter {
    //private Context mContext;
    private int mTabCount;
    private Fragment[] leadersFragments;
    private String[] tabTitles;

    public LeadersPagerAdapter(@NonNull FragmentManager fm, int behavior, String[] tabTitles, int[] defaultIcon) {
        super(fm, behavior);
        mTabCount = behavior;
        this.tabTitles = tabTitles;

        leadersFragments = new Fragment[] {
                new LeadersFragment(Constants.LEARNING_LEADERS, defaultIcon[Constants.LEARNING_LEADERS]),
                new LeadersFragment(Constants.SKILL_IQ_LEADERS, defaultIcon[Constants.SKILL_IQ_LEADERS])
        };
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
        //String title = getItem(position).getClass().getName();
        String title = tabTitles[position];
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
