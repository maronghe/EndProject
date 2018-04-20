package logan.dl.com.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.ChongZhiActivity;
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
    private Handler handler;
    private final static int FLAG = 1;
    private Thread thread = null;
    private View view=null;
    private TextView tv_money;
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
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == FLAG){

//                    Toast.makeText(getContext(),msg.obj+"",Toast.LENGTH_SHORT).show();
                    Object obj = msg.obj;
                    JsonObject jsonObject = new JsonParser().parse(obj.toString()).getAsJsonObject();
                    tv_money.setText(jsonObject.get("money")+"");

                }
            }
        };
        thread = new Thread(new MyThread());
        thread.start();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        thread.start();
    }

    class MyThread implements Runnable{
        @Override
        public void run() {

            String address = "http://47.93.194.171:8081/myproject/api/welcome/getMoney?id=18742530580";
            String str = "";
            HttpURLConnection connection = null;
            try {
                URL url = new URL(address);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);//超时链接时间设置为8秒
                connection.setReadTimeout(8000);
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                str = response.toString();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            Message message = Message.obtain();
            message.what = FLAG;
            message.obj = str;
            handler.sendMessage(message);
        }
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
                        welcomename.append(".");
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
        tv_money = (TextView)view.findViewById(R.id.tv_money);

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