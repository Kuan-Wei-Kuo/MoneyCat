package com.kuo.moneycat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.view.common.WrapContentHeightViewPager;
import com.kuo.moneycat.view.dialog.RecyclerDialog;
import com.kuo.samplecalculator.TestCustom;

/**
 * Created by Kuo on 2015/11/4.
 */
public class FragmentInster extends Fragment {

    private TextView moneyText, accountText, contentText;
    private WrapContentHeightViewPager viewPager;
    private TestCustom testCustom;

    private LinearLayout accountLayout;

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

        testCustom = (TestCustom) view.findViewById(R.id.testCustom);
        accountLayout = (LinearLayout) view.findViewById(R.id.accountLayout);
    }

    private void initView() {
        testCustom.setAlpha(0);
        testCustom.setOnItemCliclListener(onItemClickListener);

        moneyText.setClickable(true);
        moneyText.setOnClickListener(moneyTextClickListener);

        accountLayout.setOnClickListener(accountClickListener);
    }

    private View.OnClickListener moneyTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(testCustom);
            viewPropertyAnimatorCompat.alpha(1).x(0).start();
        }
    };

    private String flagAnswer = "";

    private TestCustom.OnItemCliclListener onItemClickListener = new TestCustom.OnItemCliclListener() {
        @Override
        public void onClick(String text) {
            moneyText.setText(text);
            if(flagAnswer.equals(text)) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(testCustom);
                viewPropertyAnimatorCompat.alpha(0).x(0).start();
            } else {
                flagAnswer = text;
            }
        }
    };

    private LinearLayout.OnClickListener accountClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerDialog recyclerDialog = new RecyclerDialog();
            recyclerDialog.show(getFragmentManager(), "recyclerDialog");
        }
    };
}
