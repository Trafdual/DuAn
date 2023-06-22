package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fragment.Top10Fragment;
import model.Top10;
import tranhph26979.fpoly.duanmau.R;

public class Top10Adapter extends ArrayAdapter<Top10> {
    private Context context;
    Top10Fragment top10Fragment;
    ArrayList<Top10> list;
    TextView tvsach,tvsl;
    ImageView imgdel;

    public Top10Adapter(@NonNull Context context, Top10Fragment top10Fragment, ArrayList<Top10> list) {
        super(context, 0,list);
        this.context = context;
        this.top10Fragment = top10Fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top10_item,null);

        }
        final Top10 top10=list.get(position);
        if(top10!=null){
            tvsach=v.findViewById(R.id.tvMalvsp);
            tvsach.setText("Sách: "+top10.getTenSach());
            tvsl=v.findViewById(R.id.tvsoluongsp);
            tvsl.setText("Số lượng: "+top10.getSoLuong());

        }
        return v;
    }
}
