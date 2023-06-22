package tranhph26979.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import tranhph26979.fpoly.duanmau.database.ThuThuDao;

public class Login extends AppCompatActivity {
EditText edusername,edpass;
Button btnlogin,btnhuy;
CheckBox checkpass;
String strUser,strpass;
ThuThuDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edusername=findViewById(R.id.edusername);
        edpass=findViewById(R.id.edpassword);
        btnlogin=findViewById(R.id.btnLogin);
        btnhuy=findViewById(R.id.btnHuy);
        checkpass=findViewById(R.id.checkPass);
        dao=new ThuThuDao(this);
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user=pref.getString("USERNAME","");
        String pass=pref.getString("PASSWORD","");
       Boolean rem=pref.getBoolean("REMEMBER",false);

       edusername.setText(user);
       edpass.setText(pass);
       checkpass.setChecked(rem);
       btnhuy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             edusername.setText("");
             edpass.setText("");
           }
       });
       btnlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               checkLogin();
           }
       });

    }
    public void rememberUser(String u,String p,Boolean status){
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit=pref.edit();
        if (!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);

        }
        edit.commit();
    }
    public void checkLogin(){
        strUser=edusername.getText().toString();
        strpass=edpass.getText().toString();
        if (strUser.isEmpty()||strpass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dao.checkLogin(strUser,strpass)>0){
                Toast.makeText(getApplicationContext(), "Login Thành Công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strpass,checkpass.isChecked());
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}