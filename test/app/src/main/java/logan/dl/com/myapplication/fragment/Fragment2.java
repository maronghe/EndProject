package logan.dl.com.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import logan.dl.com.myapplication.MainActivity;
import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.activity.LeftMenuActivity;
import logan.dl.com.myapplication.activity.TotalActivity;

/**
 * Show map
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment2 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private Button showMenuBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view=inflater.inflate(R.layout.fragment2,container,false);
        showMenuBtn = (Button)view.findViewById(
                R.id.showMenu
        ) ;
        showMenuBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showMenu:
                Intent intent = new Intent(getActivity(),LeftMenuActivity.class);
                startActivity(intent);
                break;
        }
    }
}
