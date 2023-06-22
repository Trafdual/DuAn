package fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.LoaiSachSpinner;
import adapter.SachAdapter;
import model.LoaiSach;
import model.Sach;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.LoaiSachDao;
import tranhph26979.fpoly.duanmau.database.SachDao;


public class SachFragment extends Fragment {
ListView lvSach;
SachDao sachDao;
SachAdapter adapter;
FloatingActionButton tab1;
Dialog dialog;
EditText edtmasach,edttensach,edtgiathue,edttimkiem;
Spinner spinner;
Button btnsave,btnhuy;
ImageView btntim;
LoaiSachSpinner loaiSachSpinner;
ArrayList<LoaiSach> loaiSachArrayList;
LoaiSachDao loaiSachDao;
int maloaisach,position;
Sach item;
List<Sach> list;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach=v.findViewById(R.id.listSach);
        sachDao=new SachDao(getActivity());
        tab1=v.findViewById(R.id.fab1);
        capnhatlv();
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opendialog(getActivity(),0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                opendialog(getActivity(),1);
                return false;
            }
        });
        edttimkiem=v.findViewById(R.id.edttimkiem);
        btntim=v.findViewById(R.id.btntim);
        btntim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               filter(edttimkiem.getText().toString());
            }
        });
        return v;

    }
    private void filter(String text) {
        ArrayList<Sach> filteredList = new ArrayList<>();
        for (Sach sach: list){
            if (sach.getTenSach().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sach);
            }
        }
        adapter.filterList(filteredList);
    }

    void capnhatlv(){
        list= sachDao.getAll();
        adapter=new SachAdapter(getActivity(),this,list);
        lvSach.setAdapter(adapter);
    }
    public void xoa(final String ID){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDao.delete(ID);
                capnhatlv();
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
    protected void opendialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);
        edtmasach=dialog.findViewById(R.id.edtMasach);
        edttensach=dialog.findViewById(R.id.edttensach);
        edtgiathue=dialog.findViewById(R.id.edtgiathue);
        spinner=dialog.findViewById(R.id.spinnertenloai);
        btnsave=dialog.findViewById(R.id.btnSavesach);
        btnhuy=dialog.findViewById(R.id.btnHuysach);
        loaiSachArrayList=new ArrayList<LoaiSach>();
        loaiSachDao=new LoaiSachDao(context);
        loaiSachArrayList= (ArrayList<LoaiSach>) loaiSachDao.getAll();
        loaiSachSpinner=new LoaiSachSpinner(context , loaiSachArrayList);
        spinner.setAdapter(loaiSachSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloaisach=loaiSachArrayList.get(position).getMaLoai();
                Toast.makeText(context, "Chọn "+loaiSachArrayList.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edtmasach.setEnabled(false);
        if (type!=0){
            edtmasach.setText(String.valueOf(item.getMaSach()));
            edttensach.setText(item.getTenSach());
            edtgiathue.setText(String.valueOf(item.getGiaThue()));
            for (int i=0;i<loaiSachArrayList.size();i++){
                if (item.getMaLoai()==(loaiSachArrayList.get(i).getMaLoai())){
                    position=i;
                }
                Log.i("demo","posSach"+position);
                spinner.setSelection(position);
            }

        }
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item=new Sach();
                item.setTenSach(edttensach.getText().toString());
                item.setGiaThue(Integer.parseInt(edtgiathue.getText().toString()));
                item.setMaLoai(maloaisach);
                if (validate()>0){
                    if (type==0){
                        if (sachDao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaSach(Integer.parseInt(edtmasach.getText().toString()));
                        if (sachDao.updatesach(item)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capnhatlv();
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check=1;
        if (edttensach.getText().length()==0||edtgiathue.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}