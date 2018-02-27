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

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.LeftMenuActivity;

/**
 * Show left Menu
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment3 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private Button showMapBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment3,container,false);
        showMapBtn = (Button)view.findViewById(
                R.id.showMap
        ) ;
        showMapBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showMap:
                Intent intent = new Intent(getActivity(),ShowMapActivity.class);
                startActivity(intent);
                break;
        }
    }

}
