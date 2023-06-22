package tranhph26979.fpoly.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="PNLIB";
    private static final int DB_VERSION=1;
    static final String CREATE_TABLE_THUTHU="create table ThuThu (" +
            "maTT TEXT PRIMARY KEY, " +
            "hoTen TEXT NOT NULL, " +
            "matKhau TEXT NOT NULL)";

    static final String CREATE_TABLE_THANHVIEN="CREATE TABLE ThanhVien ("+ "maTV INTEGER PRIMARY KEY AUTOINCREMENT,"+ "hoTen TEXT ,"+ "namSinh TEXT NOT NULL,"+"gioitinh TEXT NOT NULL)";

    static final String CREATE_TABLE_PHIEUMUON="CREATE TABLE PhieuMuon ("+ "maPM INTEGER PRIMARY KEY AUTOINCREMENT,"+ "maTT TEXT REFERENCES ThuThu (maTT),"+
            "maTV INTEGER REFERENCES ThanhVien (maTV),"+
            "maSach INTEGER REFERENCES Sach (maSach),"+
            "tienThue INTEGER NOT NULL,"+
            "ngay DATE NOT NULL,"+
            "traSach INTEGER NOT NULL)";

    static final String CREATE_TABLE_LOAISACH="CREATE TABLE LoaiSach ("+ "maLoai INTEGER PRIMARY KEY AUTOINCREMENT,"+ "tenLoai TEXT NOT NULL)" ;

    static final String CREATE_TABLE_SACH="CREATE TABLE Sach ("+ "maSach INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "tenSach TEXT NOT NULL,"+
            "giaThue INTEGER NOT NULL,"+
            "maLoai INTEGER REFERENCES LoaiSach (maLoai) )";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_TABLE_THUTHU);
db.execSQL(CREATE_TABLE_THANHVIEN);
db.execSQL(CREATE_TABLE_LOAISACH);
db.execSQL(CREATE_TABLE_SACH);
db.execSQL(CREATE_TABLE_PHIEUMUON);
db.execSQL(Data_SQlite.INSERT_LOAISACH);
db.execSQL(Data_SQlite.INSERT_THUTHU);
db.execSQL(Data_SQlite.INSERT_SACH);
db.execSQL(Data_SQlite.INSERT_THANHVIEN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
String droptableThuThu="drop table if exists ThuThu";
db.execSQL(droptableThuThu);
        String droptableThanhVien="drop table if exists ThanhVien";
        db.execSQL(droptableThanhVien);
        String droptablePhieuMuon="drop table if exists PhieuMuon";
        db.execSQL(droptablePhieuMuon);
        String droptableLoaiSach="drop table if exists LoaiSach";
        db.execSQL(droptableLoaiSach);
        String droptableSach="drop table if exists Sach";
        db.execSQL(droptableSach);
        onCreate(db);
    }
}
