package com.myworld.android.myanim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myworld.android.myanim.fragment.OneFragment;
import com.myworld.android.myanim.fragment.ThreeFragment;
import com.myworld.android.myanim.fragment.TwoFragment;
import com.myworld.android.myanim.fragment.viewPagerAdapter.ViewPagerAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int radioId[] = {R.id.shouye, R.id.kecheng, R.id.geren};
    private RadioButton[] buttons = new RadioButton[radioId.length];
    private ViewPager viewPager;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager_index);
        group = (RadioGroup) findViewById(R.id.group);
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        for (int i = 0; i < radioId.length; i++) {
            buttons[i] = (RadioButton) findViewById(radioId[i]);
        }
        buttons[0].setTextColor(Color.RED);
        buttons[0].setSelected(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < radioId.length; i++) {
                    if (position == i) {
                        buttons[i].setTextColor(Color.RED);
                        buttons[i].setSelected(true);
                    } else {
                        buttons[i].setTextColor(Color.GRAY);
                        buttons[i].setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.shouye:
                        changeColor(0);
                        break;
                    case R.id.kecheng:
                        changeColor(1);
                        break;
                    case R.id.geren:
                        changeColor(2);
                        break;
                }
            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

    }

    public void changeColor(int x) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == x) {
                viewPager.setCurrentItem(i, false);
                buttons[i].setTextColor(Color.RED);
                buttons[i].setSelected(true);
            } else {
                buttons[i].setTextColor(Color.GRAY);
                buttons[i].setSelected(false);
            }
        }
    }
}
