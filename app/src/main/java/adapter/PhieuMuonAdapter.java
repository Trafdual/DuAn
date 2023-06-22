package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fragment.PhieuMuonFragment;
import model.PhieuMuon;
import model.Sach;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.SachDao;
import tranhph26979.fpoly.duanmau.database.ThanhVienDao;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvmapm,tvtentv,tvtensach,tvtienthue,tvngay,tvtrasach;
    ImageView imgdel;
    SachDao sachDao;
    ThanhVienDao thanhVienDao;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.phieumuon_item,null);

        }
        final PhieuMuon item=list.get(position);
        if(item!=null){
            tvmapm=v.findViewById(R.id.tvmaPM);
            tvmapm.setText("Mã Phiếu: "+item.getMaPM());
            sachDao=new SachDao(context);
            Sach sach=sachDao.getID(String.valueOf(item.getMaSach()));
            tvtensach=v.findViewById(R.id.tvtenSach);
            tvtensach.setText("Tên Sách: "+sach.getTenSach());
            thanhVienDao=new ThanhVienDao(context);
            ThanhVien thanhVien=thanhVienDao.getID(String.valueOf(item.getMaTV()));
            tvtentv=v.findViewById(R.id.tvtenTV);
            tvtentv.setText("Thành Viên: "+thanhVien.getHoTen());
            tvtienthue=v.findViewById(R.id.tvtienthue);
            tvtienthue.setText("Tiền thuê: "+item.getTienThue());
          tvngay=v.findViewById(R.id.tvngaypm);
          tvngay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
          tvtrasach=v.findViewById(R.id.tvtrasach);
          if (item.getTrasach()==1){
              tvtrasach.setTextColor(Color.BLUE);
              tvtrasach.setText("Đã trả sách");
          }
          else {
              tvtrasach.setTextColor(Color.RED);
              tvtrasach.setText("Chưa trả sách");
          }
          imgdel=v.findViewById(R.id.imgDelete1);

        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }
}
