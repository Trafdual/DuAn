package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import adapter.LoaiSachAdapter;
import adapter.LoaiSachSpinner;
import model.LoaiSach;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.LoaiSachDao;


public class LoaiSachFragment extends Fragment {
    ListView lvsach;
    ArrayList<LoaiSach> list;
    static LoaiSachDao dao;
    LoaiSachAdapter adapter;
    FloatingActionButton tb;
    Dialog dialog;
    EditText edtmaloaisach,edttenloai;
    Button btnsave,bthuy;
    LoaiSach item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v= inflater.inflate(R.layout.fragment_loai_sach, container, false);
       dao=new LoaiSachDao(getActivity());
       lvsach=v.findViewById(R.id.lvloaisach);
       tb=v.findViewById(R.id.tabls);
       capnhat();
       tb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               opendialog(getActivity(),0);
           }
       });
       lvsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list= (ArrayList<LoaiSach>) dao.getAll();
        adapter=new LoaiSachAdapter(getActivity(),this,list);
        lvsach.setAdapter(adapter);
    }
    public void xoa(final String ID){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
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
    public void opendialog(final Context context, final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_loaisach);
       edtmaloaisach=dialog.findViewById(R.id.edtMaloaisach);
        edttenloai=dialog.findViewById(R.id.edttenloaisach);
        btnsave=dialog.findViewById(R.id.btnSaveloai);
        bthuy=dialog.findViewById(R.id.btnHuyloai);
        edtmaloaisach.setEnabled(false);
        if (type!=0){
            edtmaloaisach.setText(String.valueOf(item.getMaLoai()));
            edttenloai.setText(item.getTenLoai());
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
                item=new LoaiSach();
                item.setTenLoai(edttenloai.getText().toString());
                if (validate()>0){
                    if (type==0){
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaLoai(Integer.parseInt(edtmaloaisach.getText().toString()));
                        if (dao.updateloai(item)>0){
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
        if (edttenloai.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}