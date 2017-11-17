package com.blogspot.ramannada.quizapp.question;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blogspot.ramannada.quizapp.App;

/**
 * Created by ramannada on 11/15/2017.
 */

public class FragmentPagerManager extends FragmentPagerAdapter {
    public FragmentPagerManager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("page", position + 1);
        Fragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return App.db.getQuestionSize();
    }
}
