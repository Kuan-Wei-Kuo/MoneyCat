package com.kuo.moneycat.view.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.drawer.DrawerAdapter;
import com.kuo.moneycat.mode.drawer.DrawerItem;
import com.kuo.moneycat.mode.sqlite.SQLiteManager;

import java.util.ArrayList;

/**
 * Created by User on 2015/11/1.
 */
public class FragmentAccount extends Fragment {

    private RecyclerView recyclerView;
    private DrawerAdapter drawerAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        getSQLiteAccount();
        initView(view);

        return view;
    }


    private void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        drawerAdapter = new DrawerAdapter(drawerItems);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(drawerAdapter);

    }

    private void getSQLiteAccount() {

        SQLiteManager sqLiteManager = new SQLiteManager(getActivity());
        sqLiteManager.onOpen(sqLiteManager.getWritableDatabase());

        Cursor cursor = sqLiteManager.getAccountData();

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            for(int i = 0 ; i < cursor.getCount() ; i++){
                DrawerItem drawerItem = new DrawerItem();
                drawerItem.setIconResId(R.mipmap.account_icon);
                drawerItem.setTitleText(cursor.getString(1));
                drawerItems.add(drawerItem);
                cursor.moveToNext();
            }
        }


    }

}
