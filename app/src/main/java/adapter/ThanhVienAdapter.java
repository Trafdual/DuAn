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

import java.util.ArrayList;
import java.util.List;

import fragment.ThanhVienFragment;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView tvmaTV,tvtenTV,tvnamSinh,tvgioitinh;
    ImageView imageView;

    public ThanhVienAdapter(@NonNull Context context,  ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
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
            v=inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item=list.get(position);
        if (item!=null){
            tvgioitinh=v.findViewById(R.id.tvgioitinhtv);
            tvgioitinh.setText("giới tính: "+item.getGioitinh());
            tvmaTV=v.findViewById(R.id.tvMatv);
            tvmaTV.setText("Mã Thành Viên: "+item.getMaTV());
            tvtenTV=v.findViewById(R.id.tvTentv);
            tvtenTV.setText("Tên thành viên: "+item.getHoTen());
            tvnamSinh=v.findViewById(R.id.tvNamsinh);
            tvnamSinh.setText("Năm sinh: "+item.getNamSinh());
            imageView=v.findViewById(R.id.Delete);
            if (item.getGioitinh().equalsIgnoreCase("nam")){
                tvgioitinh.setTextColor(Color.BLUE);
            }
            else {
                tvgioitinh.setTextColor(Color.YELLOW);
            }
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
fragment.xoa(String.valueOf(item.getMaTV()));
            }
        });


        return v;
    }
}
