package tranhph26979.fpoly.duanmau.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.PhieuMuon;
import model.Sach;
import model.ThanhVien;
import model.Top10;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonDao(Context context){
        this.context=context;
        DbHelper dbHelper=new DbHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues values=new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("ngay",sdf.format(obj.getNgay()));
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTrasach());
        return db.insert("PhieuMuon",null,values);
    }
    public int updateP(PhieuMuon obj){
        ContentValues values=new ContentValues();
        values.put("maTT",obj.getMaTT());
        values.put("maTV",obj.getMaTV());
        values.put("maSach",obj.getMaSach());
        values.put("ngay",sdf.format(obj.getNgay()));
        values.put("tienThue",obj.getTienThue());
        values.put("traSach",obj.getTrasach());
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.getMaPM())});
    }
    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj=new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            obj.setMaTT(c.getString(c.getColumnIndex("maTT")));
            try {
                obj.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTrasach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            list.add(obj);

        }
        return list;
    }
    public List<PhieuMuon> getAll(){
        String sql="SELECT*FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql="SELECT*FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list=getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    public List<Top10> getTop(){
        String sqlTop="SELECT maSach,count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC lIMIT 10";
        List<Top10> list=new ArrayList<>();
        SachDao sachDao=new SachDao(context);
        Cursor c=db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top10 top10=new Top10();
            @SuppressLint("Range") Sach sach=sachDao.getID(c.getString(c.getColumnIndex("maSach")));
            top10.setTenSach(sach.getTenSach());
            top10.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top10);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu="SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
