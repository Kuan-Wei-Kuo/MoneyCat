package com.kuo.moneycat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.view.common.WrapContentHeightViewPager;
import com.kuo.samplecalculator.CalculatorView;

/**
 * Created by Kuo on 2015/11/4.
 */
public class FragmentInster extends Fragment {

    private TextView moneyText, accountText, contentText;
    private WrapContentHeightViewPager viewPager;
    private CalculatorView calculatorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inster_cost, container, false);

        findView(view);
        initView();

        return view;
    }

    private void findView(View view) {

        moneyText = (TextView) view.findViewById(R.id.moneyText);
        accountText = (TextView) view.findViewById(R.id.accountText);
        contentText = (TextView) view.findViewById(R.id.contentText);

        viewPager = (WrapContentHeightViewPager) view.findViewById(R.id.viewPager);
        calculatorView = (CalculatorView) view.findViewById(R.id.calculatorView);

    }

    private void initView() {

        moneyText.setClickable(true);
        moneyText.setOnClickListener(moneyTextClickListener);

        calculatorView.setColors(CalculatorView.NUMBER, getResources().getColor(R.color.DeepOrange_500));

    }

    private View.OnClickListener moneyTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    };
}
