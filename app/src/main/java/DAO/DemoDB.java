package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tranhph26979.fpoly.duanmau.database.DbHelper;

public class DemoDB {
    private SQLiteDatabase db;

    public DemoDB(Context context) {
        DbHelper dbHelper=new DbHelper(context);
       db=dbHelper.getWritableDatabase();
    }
}
