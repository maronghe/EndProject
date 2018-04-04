package logan.dl.com.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.FullscreenActivity;
import logan.dl.com.myapplication.activity.ListTingCheWeiActivity;
import logan.dl.com.myapplication.activity.TingCheZTActivity;
import logan.dl.com.myapplication.activity.YiJianFanKuiActivity;
import logan.dl.com.myapplication.other.utils.HttpCallbackListener;
import logan.dl.com.myapplication.other.utils.HttpUtil;

/**
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment4 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private LinearLayout f4_layout_btn;
    private TextView tv_chepaiguanli,tv_tingchejilu,tv_wodechewei,tv_yijianfankui,welcomename,welcomenumber;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment4,container,false);

        f4_layout_btn = (LinearLayout) view.findViewById(R.id.f4_layout_btn);
        f4_layout_btn.setOnClickListener(this);

        initItems(view);

        initWelcome(view);

        return view;
    }

    private void initWelcome(View view) {


    //send http request   http://47.93.194.171:/myproject/api/welcome/1
        String address = "http://47.93.194.171:8081/myproject/api/welcome/1";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

            @Override
            public void onFinish(String response) {
                System.out.println("hahahahahahhaahahahahahahah");
//                JSONObject jsonObject = JSONObject.

                Gson gson = new Gson();
                JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                final String name = json.get("name").toString().replace("\"","");
                final String phoneNumber = json.get("phoneNumber").toString().replace("\"","");
                // update ui
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        welcomename.setText(name);
                        welcomenumber.setText(phoneNumber);
                    }
                });

                Log.d("TAG", response.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.d("TAG", e.toString());
            }
        });


    }

    private void initItems(View view) {
        tv_chepaiguanli = (TextView) view.findViewById(R.id.chepaiguanli);
        tv_tingchejilu = (TextView) view.findViewById(R.id.tingchejilu);
        tv_wodechewei = (TextView) view.findViewById(R.id.wodechewei);
        tv_yijianfankui = (TextView) view.findViewById(R.id.yijianfankui);
        tv_chepaiguanli.setOnClickListener(this);
        tv_tingchejilu.setOnClickListener(this);
        tv_wodechewei.setOnClickListener(this);
        tv_yijianfankui.setOnClickListener(this);

        welcomename = (TextView)view.findViewById(R.id.welcomename);
        welcomenumber = (TextView)view.findViewById(R.id.welcomephonenum);
        welcomenumber.setOnClickListener(this);
        welcomename.setOnClickListener(this);

        /*
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
                Toast.makeText(getContext(),"Hello!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.chepaiguanli:
                Intent intent1= new Intent(getActivity(), ListTingCheWeiActivity.class);
                startActivity(intent1);
                break;
            case R.id.tingchejilu:

                Intent intent2 = new Intent(getActivity(), FullscreenActivity.class);
                startActivity(intent2);
                break;
                //setting
            case R.id.wodechewei:
                Intent intent3 = new Intent(getActivity(), TingCheZTActivity.class);
                startActivity(intent3);
                break;
            case R.id.yijianfankui:
                Intent intent4 = new Intent(getActivity(), YiJianFanKuiActivity.class);
                startActivity(intent4);
                break;
        }
    }

}