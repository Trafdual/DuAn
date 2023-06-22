package tranhph26979.fpoly.duanmau.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.LoaiSach;
import model.Sach;

public class SachDao {
    private SQLiteDatabase db;
    public SachDao(Context context) {
        DbHelper dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(Sach obj){
        ContentValues values=new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());
        return db.insert("Sach",null,values);
    }
    public int updatesach(Sach obj){
        ContentValues values=new ContentValues();
        values.put("tenSach",obj.getTenSach());
        values.put("giaThue",obj.getGiaThue());
        values.put("maLoai",obj.getMaLoai());
        return db.update("Sach",values,"maSach=?",new String[] {String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj=new Sach();
            obj.setMaLoai(c.getInt(c.getColumnIndex("maLoai")));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaThue(c.getInt(c.getColumnIndex("giaThue")));
            obj.setMaSach(c.getInt(c.getColumnIndex("maSach")));


            list.add(obj);

        }
        return list;
    }
    public List<Sach> getAll(){
        String sql="SELECT*FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql="SELECT*FROM Sach WHERE maSach=?";
        List<Sach> list=getData(sql,id);
        return list.get(0);
    }
}
