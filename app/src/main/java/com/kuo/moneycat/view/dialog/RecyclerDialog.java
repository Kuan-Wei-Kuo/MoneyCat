package com.kuo.moneycat.view.dialog;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.drawer.DrawerAdapter;
import com.kuo.moneycat.mode.drawer.DrawerItem;
import com.kuo.moneycat.mode.sqlite.SQLiteManager;

import java.util.ArrayList;

/**
 * Created by User on 2015/11/5.
 */
public class RecyclerDialog extends DialogFragment {

    private RecyclerView recyclerView;
    private DrawerAdapter drawerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();

    private OnEnterClickListener onEnterClickListener;

    private TextView cancelText, enterText;

    private String focusAccount = "";

    public interface OnEnterClickListener {
        void onClick(String text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.dialog_recycler, container, false);

        initDrawerItems();
        findView(view);
        initView();

        return view;
    }

    private void findView(View view) {

        cancelText = (TextView) view.findViewById(R.id.cancelText);
        enterText = (TextView) view.findViewById(R.id.enterText);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        drawerAdapter = new DrawerAdapter(drawerItems);
        linearLayoutManager = new LinearLayoutManager(getActivity());

    }

    private void initView() {

        cancelText.setOnClickListener(onTextViewClickListener);
        enterText.setOnClickListener(onTextViewClickListener);

        drawerAdapter.setOnItemClickListener(onItemClickListener);

        recyclerView.setBackgroundColor(getResources().getColor(R.color.Grey_50));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(drawerAdapter);
    }

    private void initDrawerItems() {

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

    private DrawerAdapter.OnItemClickListener onItemClickListener = new DrawerAdapter.OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {

            focusAccount = drawerItems.get(position).getTitleText();

        }
    };

    private TextView.OnClickListener onTextViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.cancelText:
                    getDialog().dismiss();
                    break;
                case R.id.enterText:
                    if(onEnterClickListener != null){
                        onEnterClickListener.onClick(focusAccount);
                    }
                    getDialog().dismiss();
                    break;
            }

        }
    };

    public void setOnEnterClickListener(OnEnterClickListener onEnterClickListener) {
        this.onEnterClickListener = onEnterClickListener;
    }

}
