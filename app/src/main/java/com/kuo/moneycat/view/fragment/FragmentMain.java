package com.kuo.moneycat.view.fragment;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.main.MainPagerAdapter;
import com.kuo.moneycat.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Kuo on 2015/10/23.
 */
public class FragmentMain extends Fragment {

    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    private Toolbar toolbar;
    private MainActivity mainActivity;

    public ImageButton floatButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("MoneyCat");

        mainActivity = (MainActivity) getActivity();
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.setActionBarDrawerToggle(toolbar);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        initAdapter();

        floatButton = (ImageButton) view.findViewById(R.id.floatButton);
        floatButton.setOnClickListener(floatButtonClickListener);

    }

    private void initAdapter() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int count = 0;
        int focusCount = 0;

        for(int i = 0 ; i < 12 ; i++) {

            calendar.set(Calendar.MONTH, i);

            for(int j = 0 ; j < getLastDayOfMonth(calendar.get(Calendar.YEAR), i) ; j++) {
                count++;
                fragments.add(FragmentCost.newIntance(calendar.get(Calendar.YEAR), i, j+1));
                titles.add("" + calendar.get(Calendar.YEAR) + (i+1) + (j+1));

                if(year == calendar.get(Calendar.YEAR) && i == month && day == (j+1)) {
                    focusCount = count;
                }
            }
        }

        mainPagerAdapter = new MainPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setVerticalScrollbarPosition(focusCount);

    }

    private int getLastDayOfMonth(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private ImageButton.OnClickListener floatButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            FragmentInster fragmentInster = new FragmentInster();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragmentInster, "fragmentInster");
            fragmentTransaction.addToBackStack("fragmentTransaction");
            fragmentTransaction.commit();

        }
    };
}
