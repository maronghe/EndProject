package logan.dl.com.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.other.utils.StringUtil;


public class Fragment1 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;

    private TextView tv_findchewei;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment1,container,false);
        tv_findchewei = (TextView) view.findViewById(R.id.zhaochewei);
        tv_findchewei.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what ==1 ){


                }


            }
        };


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhaochewei:
                Intent intent = new Intent(getActivity(),ShowMapActivity.class);
                startActivity(intent);
                break;
            default:
                    break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    class MyThread implements Runnable{
        @Override
        public void run()
        {

        }
    }
}
