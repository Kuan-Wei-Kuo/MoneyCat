package com.kuo.moneycat.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuo.moneycat.R;

/**
 * Created by User on 2015/11/5.
 */
public class RecyclerDialog extends DialogFragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        findView(view);

        return view;
    }

    private void findView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);



    }
}
