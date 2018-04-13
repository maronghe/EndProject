package logan.dl.com.myapplication.activity;

import android.os.Bundle;
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
import java.util.Timer;
import java.util.TimerTask;

import logan.dl.com.myapplication.MainActivity;
import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.other.utils.HttpCallbackListener;
import logan.dl.com.myapplication.other.utils.HttpUtil;

public class ListTingCheWeiActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    String[] smss = {"goodgoodgoodgoodgoodgoodgoodgoodgoodgoodgoodgood", "greatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreat", "perfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfect", "vvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodery good"};

    private Switch tingcheSwtich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ting_che_wei);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tingcheSwtich = (Switch) findViewById(R.id.iwantstop);
        tingcheSwtich.setOnCheckedChangeListener(this);


//        ListView listView = findViewById(R.id.lv_item_listview);
//        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.tcitem, smss));

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int status = 0;

        if(isChecked){
            status = 1;
        }else{
            status = -1;
        }

        String address = "http://47.93.194.171:8081/myproject/api/welcome/jidianqi?status="+status;
        final int finalStatus = status;
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

            @Override
            public void onFinish(String response) {
                Gson gson = new Gson();
                JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                final String flag = json.get("flag").getAsString();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                    }
//                }).start();
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
                String curl = "http://47.93.194.171:8081/myproject/api/welcome/jidianqi?status=0";

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
