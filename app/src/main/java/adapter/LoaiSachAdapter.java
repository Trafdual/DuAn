package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fragment.LoaiSachFragment;
import fragment.ThanhVienFragment;
import model.LoaiSach;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> list;
    TextView tvmaloai,tvtenloai;
    ImageView imgdelete;

    public LoaiSachAdapter(Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> list) {
        super(context,0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach item=list.get(position);
        if (item!=null){
            tvmaloai=v.findViewById(R.id.tvMaloai);
            tvmaloai.setText("Mã Loại Sách: "+item.getMaLoai());
            tvtenloai=v.findViewById(R.id.tvTenloai);
            tvtenloai.setText("Tên Loại Sách: "+item.getTenLoai());
            imgdelete=v.findViewById(R.id.Delete0);
        }
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });


        return v;
    }
}
