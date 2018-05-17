package logan.dl.com.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import logan.dl.com.myapplication.MainActivity;
import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.other.utils.HttpCallbackListener;
import logan.dl.com.myapplication.other.utils.HttpUtil;
import logan.dl.com.myapplication.other.utils.StringUtil;

public class ListTingCheWeiActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    String[] smss = {"goodgoodgoodgoodgoodgoodgoodgoodgoodgoodgoodgood", "greatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreat", "perfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfect", "vvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodery good"};
    private Handler handler;
    private Switch tingcheSwtich;
    int status = 0;
    private Switch n1order,n2order,n3order,n4order,n5order,n6order;
    private Switch n1stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ting_che_wei);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
//                   Map<String,Object> map= (Map<String,Object>)msg.obj;
                    Gson gson = new Gson();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = gson.fromJson((String) msg.obj, map.getClass());
                    Toast.makeText(ListTingCheWeiActivity.this,map.toString(),Toast.LENGTH_LONG).show();
                }
                else  if(msg.what == 2){
                    Toast.makeText(ListTingCheWeiActivity.this,"Unlocking...",Toast.LENGTH_SHORT).show();
                }
            }
        };
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tingcheSwtich = (Switch) findViewById(R.id.iwantstop);
        tingcheSwtich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    {
                        String address = StringUtil.SERVER_URL+"/JieSuoAPI";//Local
                        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                Message message = Message.obtain();
                                message.what = 2;
                                handler.sendMessage(message);
                            }
                            @Override
                            public void onError(Exception e) {
                                Log.d("TAG", e.toString());
                            }
                        });
                    }
                }
            }
        });
        n1order = (Switch) findViewById(R.id.n1order);
        n1order.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    status = 1;
                }else{
                    status = 0;
                }

                new Thread(new MyThread()).start();

            }
        });

//        ListView listView = findViewById(R.id.lv_item_listview);
//        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.tcitem, smss));

    }
    class MyThread implements  Runnable{
        @Override
        public void run() {
            String address = StringUtil.URL+"n1order?status="+status;//Local
            HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Gson gson = new Gson();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = gson.fromJson(response, map.getClass());
                    Boolean aBoolean=(Boolean) map.get("flag");
                    System.out.println("map的aBoolean值为:"+aBoolean);
                    System.out.println(map.toString());
                    Log.d("TAG", response.toString());

                    Message message = Message.obtain();
                    message.obj = map.toString();
                    message.what = 1 ;
                    handler.sendMessage(message);

                }
                @Override
                public void onError(Exception e) {
                    Log.d("TAG", e.toString());
                }
            });
        }
    }
    int status2 = 0;
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){
            status2 = 1;
            //tingche  计时
        }else{
            status2 = -1;
            //jiaofei  统计停车时间、结账
        }
        new Thread(new MyThread2()).start();
    }


    class MyThread2 implements  Runnable{

        @Override
        public void run() {
            String address = StringUtil.URL+"jidianqi?status="+status2;
            final int finalStatus = status2;
            HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

                @Override
                public void onFinish(String response) {
                    Gson gson = new Gson();
                    JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                    final String flag = json.get("flag").getAsString();
                    Toast.makeText(ListTingCheWeiActivity.this,flag + " " + finalStatus,Toast.LENGTH_SHORT).show();

                    Log.d("TAG", response.toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.d("TAG", e.toString());
                }
            });


            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    String curl = StringUtil.URL+"jidianqi?status=0";

                    HttpUtil.sendHttpRequest(curl, new HttpCallbackListener() {

                        @Override
                        public void onFinish(String response) {
                            Gson gson = new Gson();
                            JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                            final String flag = json.get("flag").getAsString();
                            Toast.makeText(ListTingCheWeiActivity.this,flag +"0",Toast.LENGTH_SHORT).show();

                            Log.d("TAG", response.toString());
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("TAG", e.toString());
                        }
                    });

                }
            };
            Timer timer = new Timer();
            timer.schedule(task,1000);
        }
    }

}
