package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fragment.SachFragment;
import model.LoaiSach;
import model.Sach;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.LoaiSachDao;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    SachFragment fragment;
    List<Sach> list;
    TextView tvmasch,tvtensach,tvgiathue,tvmaloai;
    ImageView imageView;

    public SachAdapter(@NonNull Context context,  SachFragment fragment, List<Sach> list) {
        super(context,0,list);
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
 v=inflater.inflate(R.layout.sach_item,null);
        }
        Sach item=list.get(position);
        if (item!=null){
            LoaiSachDao loaiSachDao=new LoaiSachDao(context);
            LoaiSach loaiSach=loaiSachDao.getID(String.valueOf(item.getMaLoai()));
            tvmasch=v.findViewById(R.id.tvMasach);
            tvmasch.setText("Mã sách: "+item.getMaSach());
            tvtensach=v.findViewById(R.id.tvTensach);
            tvtensach.setText("Tên sách: "+item.getTenSach());
            tvgiathue=v.findViewById(R.id.tvgiaThue);
            tvgiathue.setText("Giá thuê: "+item.getGiaThue());
            tvmaloai=v.findViewById(R.id.tvmaloai);
            tvmaloai.setText("Tên loại: "+loaiSach.getTenLoai());
            imageView=v.findViewById(R.id.DeleteSach);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
fragment.xoa(String.valueOf(item.getMaSach()));
                }
            });
        }
        return v;
    }
    public void sort(String s){
        s=s.toUpperCase();
        int k=0;
        for (int i=0;i<list.size();i++){
            Sach t=list.get(i);
            String ten=t.getTenSach().toUpperCase();
            if (ten.indexOf(s)>=0){
                list.set(i,list.get(k));
                list.set(k,t);
                k++;
            }
        }
        notifyDataSetChanged();
    }
    public void filterList(ArrayList<Sach> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}

