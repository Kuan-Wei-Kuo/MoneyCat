package com.kuo.moneycat.mode.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2015/11/1.
 */
public class SQLiteManager extends SQLiteOpenHelper{

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "MoneyCatSQLite.db";
    private final static String ACCOUNT_TABLE = "accountTable";
    private final static String DEFAULT_COST_TABLE = "defaultCostTable";
    private final static String DEFAULT_INCOME_TABLE = "defaultIncomeTable";

    private final static String ROW_ID = "rowId";
    private final static String ACCOUNT_NAME = "accountName";
    private final static String MONEY = "money";
    private final static String CONTENT = "content";
    private final static String DATE = "date";

    private final static String COST_TABLE_NAME = "costTableName";
    private final static String INCOME_TABLE_NAME = "incomeTableName";

    private Context context = null;
    private SQLiteDatabase db;

    public SQLiteManager (Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createAccountTable = "CREATE TABLE " + ACCOUNT_TABLE
                + " (" + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOUNT_NAME + " TEXT, "
                + COST_TABLE_NAME + " TEXT, "
                + INCOME_TABLE_NAME + " TEXT, "
                + DATE + " TEXT);";

        String createDefaultCostTable = "CREATE TABLE " + DEFAULT_INCOME_TABLE
                + " (" + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOUNT_NAME + " TEXT, "
                + MONEY + " INTEGER, "
                + CONTENT + " TEXT, "
                + DATE + " TEXT);";

        String createDefaultIncomeTable = "CREATE TABLE " + DEFAULT_COST_TABLE
                + " (" + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOUNT_NAME + " TEXT, "
                + MONEY + " INTEGER, "
                + CONTENT + " TEXT, "
                + DATE + " TEXT);";

        sqLiteDatabase.execSQL(createAccountTable);
        sqLiteDatabase.execSQL(createDefaultCostTable);
        sqLiteDatabase.execSQL(createDefaultIncomeTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.db = db;
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public long insertAccountData(String accountName, String costTableName, String incomeTableName, String date){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_NAME, accountName);
        contentValues.put(COST_TABLE_NAME, costTableName);
        contentValues.put(INCOME_TABLE_NAME, incomeTableName);
        contentValues.put(DATE, date);

        return db.insert(ACCOUNT_TABLE, null, contentValues);
    }

    public long insertMoneyData(String tableName, String accountName, Integer money, String content, String date){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNT_NAME, accountName);
        contentValues.put(MONEY, money);
        contentValues.put(CONTENT, content);
        contentValues.put(DATE, date);

        return db.insert(tableName, null, contentValues);
    }

    public Cursor getAccountData() {
        return db.query(ACCOUNT_TABLE, new String[] {ROW_ID, ACCOUNT_NAME, COST_TABLE_NAME, INCOME_TABLE_NAME, DATE}, null, null, null, null, null);
    }

    public Cursor getMoneyDataDescDateNotRepeat(String tableName){

        Cursor cursor = db.query(true, tableName, new String[] {DATE}, null, null, null, null, "date(" + DATE + ")" + " DESC" , null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor getMoneyDataDate(String tableName, String date){

        Cursor cursor = db.query(tableName, new String[] {ROW_ID, ACCOUNT_NAME, MONEY, CONTENT, DATE}, DATE + "=" + "'" + date + "'", null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }


}
