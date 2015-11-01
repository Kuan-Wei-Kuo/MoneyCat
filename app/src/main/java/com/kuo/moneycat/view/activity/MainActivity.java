package com.kuo.moneycat.view.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kuo.moneycat.R;
import com.kuo.moneycat.mode.drawer.DrawerAdapter;
import com.kuo.moneycat.mode.drawer.DrawerItem;
import com.kuo.moneycat.mode.sqlite.SQLiteManager;
import com.kuo.moneycat.view.fragment.FragmentMain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView drawerRecyclerView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentMain fragmentMain = new FragmentMain();
        fragmentTransaction.replace(R.id.frameLayout, fragmentMain, "fragmentMain");
        fragmentTransaction.commit();

        testSQLiteManager();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawerRecyclerView);

        initDrawer();
    }

    private DrawerAdapter drawerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();

    private void initDrawer() {

        initDrawerItem();

        drawerAdapter = new DrawerAdapter(drawerItems);
        linearLayoutManager = new LinearLayoutManager(this);

        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(linearLayoutManager);
        drawerRecyclerView.setAdapter(drawerAdapter);
    }

    private void initDrawerItem() {

        String[] titles = {"帳戶", "分析", "設定"};

        for(int i = 0 ; i < titles.length ; i++) {
            DrawerItem drawerItem = new DrawerItem();
            drawerItem.setIconResId(R.mipmap.ic_launcher);
            drawerItem.setTitleText(titles[i]);
            drawerItems.add(drawerItem);
        }
    }

    public void setActionBarDrawerToggle(Toolbar toolbar) {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_open);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void testSQLiteManager() {

        SQLiteManager sqLiteManager = new SQLiteManager(this);
        sqLiteManager.onOpen(sqLiteManager.getWritableDatabase());

        sqLiteManager.insertAccountData("test", "defaultCostTable", "defaultIncomeTable", "2015-11-1");

        Cursor cursor = sqLiteManager.getAccountData();

        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            for(int i = 0 ; i < cursor.getCount() ; i++){
                Log.d("SQLiteManager", "Account:" + cursor.getString(0) + ", " + "CostTable:" + cursor.getString(1) + ", " + "IncomeTable:" + cursor.getString(2));
                cursor.moveToNext();
            }
        }


    }

}
