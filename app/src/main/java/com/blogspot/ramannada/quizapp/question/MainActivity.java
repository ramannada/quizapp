package com.blogspot.ramannada.quizapp.question;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blogspot.ramannada.quizapp.App;
import com.blogspot.ramannada.quizapp.R;

public class MainActivity extends AppCompatActivity {
    NonSwipableViewPager viewPager;
    String[] storeAnswer = new String[App.db.getQuestionSize()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager_question);
        FragmentPagerManager fragmentPagerManager =
                new FragmentPagerManager(getSupportFragmentManager());

        viewPager.setAdapter(fragmentPagerManager);

    }

    @Override
    public void onBackPressed() {
        if (getPage() != 0) {
            setCurrentPage(getPage() - 1);
        }
    }

    public int getPage() {
        return viewPager.getCurrentItem();
    }

    public void setCurrentPage(int i) {
        viewPager.setCurrentItem(i, true);
    }

}
