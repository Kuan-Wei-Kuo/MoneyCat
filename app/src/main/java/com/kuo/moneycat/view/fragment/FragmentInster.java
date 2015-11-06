package com.kuo.moneycat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.sqlite.SQLiteManager;
import com.kuo.moneycat.view.common.WrapContentHeightViewPager;
import com.kuo.moneycat.view.dialog.EditDialog;
import com.kuo.moneycat.view.dialog.RecyclerDialog;
import com.kuo.samplecalculator.TestCustom;

/**
 * Created by Kuo on 2015/11/4.
 */
public class FragmentInster extends Fragment {

    private TextView moneyText, accountText, contentText;
    private TestCustom testCustom;
    private ImageButton floatButton;
    private WrapContentHeightViewPager viewPager;

    private LinearLayout accountLayout, contentLayout;

    private String flagAnswer = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inster, container, false);

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
        contentLayout = (LinearLayout) view.findViewById(R.id.contentLayout);

        floatButton = (ImageButton) view.findViewById(R.id.floatButton);
    }

    private void initView() {

        testCustom.setAlpha(0);
        testCustom.setOnItemCliclListener(onItemClickListener);

        moneyText.setClickable(true);
        moneyText.setOnClickListener(moneyTextClickListener);

        accountLayout.setOnClickListener(accountClickListener);
        contentLayout.setOnClickListener(accountClickListener);

        floatButton.setOnClickListener(floatButtonClickListener);

    }

    private View.OnClickListener moneyTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = ViewCompat.animate(testCustom);
            viewPropertyAnimatorCompat.alpha(1).x(0).start();
        }
    };

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
            switch (view.getId()) {
                case R.id.accountLayout:
                    RecyclerDialog recyclerDialog = new RecyclerDialog();
                    recyclerDialog.setOnEnterClickListener(onEnterClickListener);
                    recyclerDialog.show(getFragmentManager(), "recyclerDialog");
                    break;
                case R.id.contentLayout:
                    EditDialog editDialog = new EditDialog();
                    editDialog.show(getFragmentManager(), "editDialog");
                    break;
            }
        }
    };

    private ImageButton.OnClickListener floatButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //SQLiteManager sqLiteManager = new SQLiteManager(getActivity());
            //sqLiteManager.onOpen(sqLiteManager.getWritableDatabase());

        }
    };

    private RecyclerDialog.OnEnterClickListener onEnterClickListener = new RecyclerDialog.OnEnterClickListener() {
        @Override
        public void onClick(String text) {
            accountText.setText(text);
        }
    };
}
