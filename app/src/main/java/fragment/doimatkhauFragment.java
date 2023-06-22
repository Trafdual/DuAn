package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import model.ThuThu;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.ThuThuDao;


public class doimatkhauFragment extends Fragment {
TextInputEditText edtpassold,edtpass,edrepass;
Button btnsave,btncancel;
ThuThuDao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        dao=new ThuThuDao(getActivity());
        edtpass=v.findViewById(R.id.passnew);
        edtpassold=v.findViewById(R.id.passold);
        edrepass=v.findViewById(R.id.repass);
        btnsave=v.findViewById(R.id.btnSavepass);
        btncancel=v.findViewById(R.id.cancelsave);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtpassold.setText("");
                edtpass.setText("");
                edrepass.setText("");
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu= dao.getID(user);
                    thuThu.setMatKhau(edtpass.getText().toString());
                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edtpassold.setText("");
                        edtpass.setText("");
                        edrepass.setText("");
                    }
                    else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();

                    }                }
            }
        });
        return v;
    }
    public int validate(){
        int check=1;
        if (edtpassold.getText().length()==0||edtpass.getText().length()==0||edrepass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passold=pref.getString("PASSWORD","");
            String pass=edtpass.getText().toString();
            String repass=edrepass.getText().toString();
            if (!passold.equals(edtpassold.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
            check=-1;
            }
            if (!pass.equals(repass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            check=-1;
            }
        }
        return check;
    }
}