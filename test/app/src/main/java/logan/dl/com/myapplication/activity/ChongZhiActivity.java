package logan.dl.com.myapplication.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.other.utils.StringUtil;

public class ChongZhiActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText czshoujihao;
    private EditText czjine;

    private TextView je50;
    private TextView je100;
    private TextView je200;
    private TextView je500;
    private TextView je1000;
    private TextView je2000;
    private Button querenBtn;
    private Button quxiaoBtn;

    private Handler handler;
    private final static int FLAG = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi);

        czshoujihao = (EditText) findViewById(R.id.czshoujihao);
        je50 = (TextView) findViewById(R.id.je50);
        je100 = (TextView) findViewById(R.id.je100);
        je200 = (TextView) findViewById(R.id.je200);
        je500 = (TextView) findViewById(R.id.je500);
        je1000 = (TextView) findViewById(R.id.je1000);
        je2000 = (TextView) findViewById(R.id.je2000);
        querenBtn = (Button) findViewById(R.id.querenchongzhi);
        quxiaoBtn = (Button) findViewById(R.id.quexiaochongzhi);
        czjine = (EditText) findViewById(R.id.czjine);

        je50.setOnClickListener(this);
        je100.setOnClickListener(this);
        je200.setOnClickListener(this);
        je500.setOnClickListener(this);
        je1000.setOnClickListener(this);
        je2000.setOnClickListener(this);
        querenBtn.setOnClickListener(this);
        quxiaoBtn.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == FLAG){

                    Gson gson = new Gson();
                    HashMap map =  gson.fromJson((String) msg.obj , new HashMap<>().getClass());
                    System.out.println("map.get(\"flag\")"+map.get("flag"));
                    if((boolean)map.get("flag")){
                        Toast.makeText(ChongZhiActivity.this,"Success!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ChongZhiActivity.this,"Failed!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    class MyThread implements Runnable{
        @Override
        public void run() {
            String address = StringUtil.URL+"chongzhi?zh="+czshoujihao.getText().toString()+"&je="+czjine.getText().toString();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.je50:
                setTextToEV(50);
                break;
            case R.id.je100:
                setTextToEV(100);
                break;
            case R.id.je200:
                setTextToEV(200);
                break;
            case R.id.je500:
                setTextToEV(500);
                break;
            case R.id.je1000:
                setTextToEV(100);
                break;
            case R.id.je2000:
                setTextToEV(2000);
                break;
            case R.id.querenchongzhi:
                Confirm();
                break;
            case R.id.quexiaochongzhi:
                finish();
                break;

        }
    }

    private void Confirm() {

        if(StringUtil.isMobileNO(czshoujihao.getText().toString())){
            String money = czjine.getText().toString();
            if("".equals(money)){
                Toast.makeText(ChongZhiActivity.this,"请输入金额!",Toast.LENGTH_SHORT).show();
                return;
            }

           if(Integer.valueOf(money) >= 50){

               new AlertDialog.Builder(this)
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .setTitle("确认充值")
                       .setMessage("请核对以下信息:\n充值帐号:"+czshoujihao.getText()+",\n充值金额:"+czjine.getText()+"元.")
                       .setPositiveButton("OK", new DialogInterface.OnClickListener()
                       {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                                Thread thread = new Thread(new MyThread());
                                thread.start();

                               //Toast.makeText(ChongZhiActivity.this,"Success!",Toast.LENGTH_SHORT).show();
                               //I want to call it here
                           }})
                       .setNegativeButton("Cancel", null)
                       .show();


           }else
           {

               Toast.makeText(ChongZhiActivity.this,"金额至少50元!",Toast.LENGTH_SHORT).show();
           }
        }else{

            Toast.makeText(ChongZhiActivity.this,"手机号格式不正确!",Toast.LENGTH_SHORT).show();

        }


    }

    private void setTextToEV(int i) {
        czjine.setText(i+"");
    }

}
