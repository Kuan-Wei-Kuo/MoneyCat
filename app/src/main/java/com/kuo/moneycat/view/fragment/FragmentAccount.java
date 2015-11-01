package com.kuo.moneycat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuo.moneycat.R;

/**
 * Created by User on 2015/11/1.
 */
public class FragmentAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        return view;
    }


    private void initView() {

    }

}
