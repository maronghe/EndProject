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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;

/**
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment4 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private LinearLayout f4_layout_btn;
    private TextView tv_chepaiguanli,tv_tingchejilu,tv_wodechewei,tv_yijianfankui;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment4,container,false);

        f4_layout_btn = (LinearLayout) view.findViewById(R.id.f4_layout_btn);
        f4_layout_btn.setOnClickListener(this);

        initItems(view);

        return view;
    }

    private void initItems(View view) {
        tv_chepaiguanli = (TextView) view.findViewById(R.id.chepaiguanli);
        tv_tingchejilu = (TextView) view.findViewById(R.id.tingchejilu);
        tv_wodechewei = (TextView) view.findViewById(R.id.wodechewei);
        tv_yijianfankui = (TextView) view.findViewById(R.id.yijianfankui);
        tv_chepaiguanli.setOnClickListener(this);
        tv_tingchejilu.setOnClickListener(this);
        tv_wodechewei.setOnClickListener(this);
        tv_yijianfankui.setOnClickListener(this);/*
        tv_yijianfankui.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(),"onLongClick clicking",Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.f4_layout_btn:
                Toast.makeText(getContext(),"jump to reigster page ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.chepaiguanli:
                Toast.makeText(getContext(),"jump to chepaiguanli page ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tingchejilu:
                Toast.makeText(getContext(),"jump to tingchejilu page ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.wodechewei:
                Toast.makeText(getContext(),"jump to wodechewei page ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.yijianfankui:
                Toast.makeText(getContext(),"jump to yijianfankui page ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}