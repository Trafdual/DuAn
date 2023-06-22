package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import adapter.ThanhVienAdapter;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.ThanhVienDao;


public class ThanhVienFragment extends Fragment {
    ListView lvThanhvien;
    ArrayList<ThanhVien> list;
    static ThanhVienDao dao;
    ThanhVienAdapter adapter;
    FloatingActionButton tb;
    Dialog dialog;
    EditText edtma,edtten,edtnamsinh,edtgioitinh;
    Button btnsave,bthuy;
    ThanhVien item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v= inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhvien=v.findViewById(R.id.lvThanhVien);
        dao=new ThanhVienDao(getActivity());
        tb=v.findViewById(R.id.tab);
        capnhat();
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
opendialog(getActivity(),0);
            }
        });
        lvThanhvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                opendialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    void capnhat(){
        list= (ArrayList<ThanhVien>) dao.getAll();
        adapter=new ThanhVienAdapter(getActivity(),this,list);
        lvThanhvien.setAdapter(adapter);
    }
    public void xoa(final String ID){
        AlertDialog.Builder builder= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(ID);
                capnhat();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        builder.show();
    }
public void opendialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        edtma=dialog.findViewById(R.id.edtMatv);
        edtgioitinh=dialog.findViewById(R.id.edtgioitinh);
        edtten=dialog.findViewById(R.id.edttenTV);
        edtnamsinh=dialog.findViewById(R.id.edtnamsinh);
        btnsave=dialog.findViewById(R.id.btnSaveTv);
        bthuy=dialog.findViewById(R.id.btnHuyTV);
        edtma.setEnabled(false);
        if (type!=0){
            edtgioitinh.setText(item.getGioitinh());
            edtma.setText(String.valueOf(item.getMaTV()));
            edtten.setText(item.getHoTen());
            edtnamsinh.setText(item.getNamSinh());
        }
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
item=new ThanhVien();
item.setGioitinh(edtgioitinh.getText().toString());
item.setHoTen(edtten.getText().toString());
item.setNamSinh(edtnamsinh.getText().toString());
if (validate()>0){
if (type==0){
    if (dao.insert(item)>0){
        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
    }
}else {
    item.setMaTV(Integer.parseInt(edtma.getText().toString()));
    if (dao.updatetv(item)>0){
        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }
    else {
        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

    }
}
capnhat();
dialog.dismiss();

}
            }
        });
        dialog.show();
}
public int validate(){
        int check=1;
        if (edtten.getText().length()==0||edtnamsinh.getText().length()==0||edtgioitinh.getText().length()==0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            check=-1;
        }
        return check;
}
}