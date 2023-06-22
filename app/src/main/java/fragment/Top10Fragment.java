package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.Top10Adapter;
import model.Top10;
import tranhph26979.fpoly.duanmau.R;
import tranhph26979.fpoly.duanmau.database.PhieuMuonDao;


public class Top10Fragment extends Fragment {
    ListView lv;
    ArrayList<Top10> list;
    Top10Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_top10, container, false);
        lv=v.findViewById(R.id.lvTop);
        PhieuMuonDao phieuMuonDao=new PhieuMuonDao(getActivity());
        list= (ArrayList<Top10>) phieuMuonDao.getTop();
        adapter=new Top10Adapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;
    }
}