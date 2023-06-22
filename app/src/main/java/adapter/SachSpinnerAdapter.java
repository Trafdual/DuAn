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

import model.LoaiSach;
import model.Sach;
import tranhph26979.fpoly.duanmau.R;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
private Context context;
private ArrayList<Sach> list;
TextView masach,tensch;
    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> list) {
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
            v=inflater.inflate(R.layout.sach_item_spinner,null);
        }
        final Sach item=list.get(position);
        if (item!=null){
           masach=v.findViewById(R.id.tvMaSachsp);
            masach.setText(item.getMaSach()+". ");
           tensch=v.findViewById(R.id.tvTenSachsp);
            tensch.setText(item.getTenSach());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spinner,null);
        }
        final Sach item=list.get(position);
        if (item!=null){
            masach=v.findViewById(R.id.tvMaSachsp);
            masach.setText(item.getMaSach()+". ");
            tensch=v.findViewById(R.id.tvTenSachsp);
            tensch.setText(item.getTenSach());
        }
        return v;
    }
}
