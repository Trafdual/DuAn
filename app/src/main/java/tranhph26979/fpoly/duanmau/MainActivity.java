package tranhph26979.fpoly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import fragment.DoanhThuFragment;
import fragment.LoaiSachFragment;
import fragment.PhieuMuonFragment;
import fragment.SachFragment;
import fragment.ThanhVienFragment;
import fragment.Top10Fragment;
import fragment.doimatkhauFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout drawerLayout;
    private static final int FRAGMENT_QUANLYPM=0;
    private static final int FRAGMENT_DOIMATKHAU=1;
    private static final int FRAGMENT_DANGXUAT=2;
    private static final int FRAGMENT_THANHVIEN=3;
    private static final int FRAGMENT_SACH=4;
    private static final int FRAGMENT_LOAISACH=5;
    private static final int FRAGMENT_TOP=6;
    private static final int FRAGMENT_DOANHTHU=7;
    private  int CurrentFragment=FRAGMENT_QUANLYPM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
drawerLayout=findViewById(R.id.drawlayout);
        ImageView imageView=findViewById(R.id.menu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        NavigationView navigationView=findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new ThanhVienFragment());
        navigationView.getMenu().findItem(R.id.qlThanhVien).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.qlPhieuMuon){
            if (CurrentFragment!=FRAGMENT_QUANLYPM){
replaceFragment(new PhieuMuonFragment());
CurrentFragment=FRAGMENT_QUANLYPM;
            }

        }else if (id==R.id.DoiMatKhau){
            if (CurrentFragment!=FRAGMENT_DOIMATKHAU){
                replaceFragment(new doimatkhauFragment());
                CurrentFragment=FRAGMENT_DOIMATKHAU;
            }
        }
        else if (id==R.id.DangXuat){
            if (CurrentFragment!=FRAGMENT_DANGXUAT){
              startActivity(new Intent(getApplicationContext(),Login.class));
              finish();
            }

        }
        else if (id==R.id.qlThanhVien){
            if (CurrentFragment!=FRAGMENT_THANHVIEN){
                replaceFragment(new ThanhVienFragment());
                CurrentFragment=FRAGMENT_THANHVIEN;
            }

        }
        else if (id==R.id.qlSach){
            if (CurrentFragment!=FRAGMENT_SACH){
                replaceFragment(new SachFragment());
                CurrentFragment=FRAGMENT_SACH;
            }

        }
        else if (id==R.id.qlLoaiSach){
            if (CurrentFragment!=FRAGMENT_LOAISACH){
                replaceFragment(new LoaiSachFragment());
                CurrentFragment=FRAGMENT_LOAISACH;
            }
        }
        else if (id==R.id.top10){
            if (CurrentFragment!=FRAGMENT_TOP){
                replaceFragment(new Top10Fragment());
                CurrentFragment=FRAGMENT_TOP;
            }

        }
        else if (id==R.id.DoanhThu){
            if (CurrentFragment!=FRAGMENT_DOANHTHU){
                replaceFragment(new DoanhThuFragment());
                CurrentFragment=FRAGMENT_DOANHTHU;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}