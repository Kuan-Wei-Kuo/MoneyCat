package com.kuo.moneycat.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.cost.CostAdapter;
import com.kuo.moneycat.mode.cost.CostItem;

import java.util.ArrayList;

/**
 * Created by Kuo on 2015/10/23.
 */
public class FragmentCost extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CostAdapter costAdapter;
    private ArrayList<CostItem> costItems = new ArrayList<>();

    private int year;
    private int month;
    private int day;

    public static FragmentCost newIntance(int year, int month, int day) {

        FragmentCost fragmentCost = new FragmentCost();

        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        fragmentCost.setArguments(bundle);

        return fragmentCost;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        initCostItem();
        initView(view);

        return view;
    }

    private void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        costAdapter = new CostAdapter(costItems);
        costAdapter.setOnClickListener(onClickListenerAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(costAdapter);
    }

    private void initCostItem() {

        year = getArguments().getInt("year", 0);
        month = getArguments().getInt("month", 0);
        day = getArguments().getInt("day", 0);

        int[] costs = {year, month, day, 150, 50};
        String[] titles = {"掛包", "珍奶", "麵包", "點數", "其他"};

        costItems.clear();

        for(int i = 0 ; i < 5 ; i ++) {
            CostItem costItem = new CostItem();
            costItem.setYear(year);
            costItem.setMonth(month);
            costItem.setDay(day);
            costItem.setIconImage(R.mipmap.ic_launcher);
            costItem.setTitleText(titles[i]);
            costItem.setCost(costs[i]);
            costItems.add(costItem);
        }

    }

    private CostAdapter.OnClickListener onClickListenerAdapter = new CostAdapter.OnClickListener() {
        @Override
        public void onClick(View view, CostItem costItem) {

            FragmentMain fragmentMain = (FragmentMain) getActivity().getSupportFragmentManager().findFragmentByTag("fragmentMain");
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            FragmentDetail fragmentDetail = FragmentDetail.newIntance(costItem);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                ImageView iconImage = (ImageView) view.findViewById(R.id.iconImage);

                fragmentDetail.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.trans_move));
                fragmentDetail.setIconImageTransitionName(iconImage.getTransitionName());
                fragmentTransaction.addSharedElement(iconImage, iconImage.getTransitionName());
                fragmentTransaction.addSharedElement(fragmentMain.floatButton, fragmentMain.floatButton.getTransitionName());

            }

            fragmentTransaction.replace(R.id.frameLayout, fragmentDetail, "fragmentDetail");
            fragmentTransaction.addToBackStack("fragmentDetail");
            fragmentTransaction.commit();

        }
    };

}
