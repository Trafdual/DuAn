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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.PhieuMuonAdapter;
import adapter.SachSpinnerAdapter;
import adapter.ThanhVienSpinnerAdapter;
import model.PhieuMuon;
import model.Sach;
import model.ThanhVien;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.PhieuMuonDao;
import tranhph26979.fpoly.duanmau.database.SachDao;
import tranhph26979.fpoly.duanmau.database.ThanhVienDao;


public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
ArrayList<PhieuMuon> list;
static PhieuMuonDao dao;
PhieuMuonAdapter adapter;
PhieuMuon item;
FloatingActionButton tab;
Dialog dialog;
EditText adtMaPm;
Spinner spTv,spSach;
CheckBox checkBox;
Button btnsave,btnhuy;
TextView tvngay,tvtientue;
ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
ArrayList<ThanhVien> listthanvien;
ThanhVienDao thanhVienDao;
ThanhVien thanhVien;
int mathanhvien;
SachSpinnerAdapter sachSpinnerAdapter;
ArrayList<Sach> listsach;
SachDao sachDao;
Sach sach;
int masach,tienthue;
int positionTv,positionSach;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v= inflater.inflate(R.layout.fragment_phieu_muon, container, false);
       lvPhieuMuon=v.findViewById(R.id.lvPhieumuon);
       dao=new PhieuMuonDao(getActivity());
       tab=v.findViewById(R.id.tabPM);
       tab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               opendialog(getActivity(),0);
           }
       });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=list.get(position);
                opendialog(getActivity(),1);
                return false;
            }
        });

       capnhatlv();
       return v;
    }
    void capnhatlv(){
        list= (ArrayList<PhieuMuon>) dao.getAll();
        adapter=new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);
    }
    protected void opendialog(final Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        adtMaPm=dialog.findViewById(R.id.edtMapm);
        spTv=dialog.findViewById(R.id.spMaTv);
        spSach=dialog.findViewById(R.id.spmasach);
        tvngay=dialog.findViewById(R.id.tvNgay);
        tvngay.setText("Ngày thuê: "+sdf.format(new Date()));
        tvtientue=dialog.findViewById(R.id.tvtienthue);
        checkBox=dialog.findViewById(R.id.checktrasach);
        btnhuy=dialog.findViewById(R.id.btnHuyPM);
        btnsave=dialog.findViewById(R.id.btnSavePM);
        thanhVienDao=new ThanhVienDao(context);
        listthanvien=new ArrayList<>();
        listthanvien= (ArrayList<ThanhVien>) thanhVienDao.getAll();
        thanhVienSpinnerAdapter=new ThanhVienSpinnerAdapter(context,listthanvien);
        spTv.setAdapter(thanhVienSpinnerAdapter);
        spTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mathanhvien=listthanvien.get(position).getMaTV();
                //Toast.makeText(context, "Chọn "+listthanvien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDao=new SachDao(context);
        listsach=new ArrayList<>();
        listsach= (ArrayList<Sach>) sachDao.getAll();
        sachSpinnerAdapter=new SachSpinnerAdapter(context,listsach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                masach=listsach.get(position).getMaSach();
                tienthue=listsach.get(position).getGiaThue();
                tvtientue.setText("Tiền thuê: "+tienthue);
                //Toast.makeText(context, "Chọn "+listsach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
item=new PhieuMuon();
item.setMaSach(masach);
item.setMaTV(mathanhvien);
item.setNgay(new Date());
item.setTienThue(tienthue);
if (checkBox.isChecked()){
    item.setTrasach(1);
}
else {
    item.setTrasach(0);
}if (type==0){
    if (dao.insert(item)>0){
        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
    }
}else {
    item.setMaPM(Integer.parseInt(adtMaPm.getText().toString()));
    if (dao.updateP(item)>0){
        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
    }
    else {
        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

    }
}
capnhatlv();
dialog.dismiss();
            }
        });
        if (type!=0){
adtMaPm.setText(String.valueOf(item.getMaPM()));
for (int i=0;i<listthanvien.size();i++)
    if (item.getMaTV()==(listthanvien.get(i).getMaTV())){
        positionTv=i;
    }
    spTv.setSelection(positionTv);
for (int i=0;i<listsach.size();i++)
                if (item.getMaTV()==(listsach.get(i).getMaSach())){
                    positionSach=i;
                }
                spSach.setSelection(positionSach);
                tvngay.setText("Ngày Thuê: "+sdf.format(item.getNgay()));
                tvtientue.setText("Tiền thuê: "+item.getTienThue());
            if (item.getTrasach()==1){
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }

        }
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
dialog.dismiss();
            }
        });
        dialog.show();
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
}