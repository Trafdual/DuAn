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

import model.Sach;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView matv,tentv;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
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
            v=inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item=list.get(position);
        if (item!=null){
            matv=v.findViewById(R.id.tvMaTvsp);
           matv.setText(item.getMaTV()+". ");
            tentv=v.findViewById(R.id.tvTenTVsp);
            tentv.setText(item.getHoTen());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final ThanhVien item=list.get(position);
        if (item!=null){
            matv=v.findViewById(R.id.tvMaTvsp);
            matv.setText(item.getMaTV()+". ");
            tentv=v.findViewById(R.id.tvTenTVsp);
            tentv.setText(item.getHoTen());
        }
        return v;
    }
}
