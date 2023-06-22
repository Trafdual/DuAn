package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fragment.LoaiSachFragment;
import fragment.SachFragment;
import model.LoaiSach;
import tranhph26979.fpoly.duanmau.R;

public class LoaiSachSpinner extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView tvmaloaisach,tvtenloaisach;


    public LoaiSachSpinner(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spinner,null);
        }
        final LoaiSach item=list.get(position);
        if (item!=null){
            tvmaloaisach=v.findViewById(R.id.tvMaloaisach);
            tvmaloaisach.setText(item.getMaLoai()+". ");
            tvtenloaisach=v.findViewById(R.id.tvTenloaisach);
            tvtenloaisach.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spinner,null);
        }
        final LoaiSach item=list.get(position);
        if (item!=null){
            tvmaloaisach=v.findViewById(R.id.tvMaloaisach);
            tvmaloaisach.setText(item.getMaLoai()+". ");
            tvtenloaisach=v.findViewById(R.id.tvTenloaisach);
            tvtenloaisach.setText(item.getTenLoai());
        }
        return v;
    }
}
