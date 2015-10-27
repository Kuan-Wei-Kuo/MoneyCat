package com.kuo.moneycat.view.fragment;

import android.annotation.TargetApi;
import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.view.activity.MainActivity;

/**
 * Created by User on 2015/10/25.
 */
public class FragmentDetail extends Fragment {

    private Toolbar toolbar;
    private ImageButton floatButton;
    private ImageView iconImage;

    private MainActivity mainActivity;

    private String string;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_money_detail, container, false);

        initView(view);

        return view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView(View view) {

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        floatButton = (ImageButton) view.findViewById(R.id.floatButton);
        iconImage = (ImageView) view.findViewById(R.id.iconImage);
        mainActivity = (MainActivity) getActivity();

        iconImage.setTransitionName(string);

        floatButton.setOnClickListener(floatButtonClickListener);

        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainActivity.setActionBarDrawerToggle(toolbar);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setIconImageTransitionName(String string) {
        this.string = string;
    }

    private ImageButton.OnClickListener floatButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            FragmentEditCost fragmentEditCost = new FragmentEditCost();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                fragmentEditCost.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.trans_move));
                fragmentTransaction.addSharedElement(floatButton, floatButton.getTransitionName());

            }

            fragmentTransaction.replace(R.id.frameLayout, fragmentEditCost, "fragmentTransaction");
            fragmentTransaction.addToBackStack("fragmentTransaction");
            fragmentTransaction.commit();

        }
    };
}
