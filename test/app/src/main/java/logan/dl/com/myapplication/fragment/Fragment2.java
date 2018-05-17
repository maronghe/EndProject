package logan.dl.com.myapplication.fragment;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.ListTingCheWeiActivity;
import logan.dl.com.myapplication.adapter.F2VOAdapter;
import logan.dl.com.myapplication.other.utils.StringUtil;
import logan.dl.com.myapplication.vo.F2VO;

/**
 * List view
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment2 extends Fragment implements View.OnClickListener {

    private View view = null;
    private Context context;
    private static final int FLAG = 1;
    private Button showMenuBtn;
    private String[] smss = {"goodgoodgoodgoodgoodgoodgoodgoodgoodgoodgoodgood", "greatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreat", "perfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfect", "vvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodery good"};
    private TextView justjump;
    private Handler handler;
    private int num = 0;//tingchejilugeshu


    private static List<F2VO> f2VOList = new ArrayList<F2VO>();
    private Thread thread = null;
    static {
//        F2VO f2VO1 = new F2VO();
//        f2VO1.setText("18742530580 1H");
//        f2VO1.setRiqi("2018-3-7");
//        f2VOList.add(f2VO1);
//        F2VO f2VO2= new F2VO();
//        f2VO2.setText("18742530580 2H");
//        f2VO2.setRiqi("2018-3-7");
//        f2VOList.add(f2VO2);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment2, container, false);

        thread = new Thread(new MyThread());
//        showMenuBtn = (Button)view.findViewById(
//                R.id.showMenu
//        ) ;
//        showMenuBtn.setOnClickListener(this);
        showLoadingDialog();
        thread.start();

        // init listview
        ListView listView = view.findViewById(R.id.f2lvlistview);
        //  listView.setAdapter(new ArrayAdapter<String>(context.getApplicationContext(), R.layout.item, smss));
        F2VOAdapter f2VOAdapter = new F2VOAdapter(context.getApplicationContext(), R.layout.item_layout_f2, f2VOList);
        listView.setAdapter(f2VOAdapter);

//        LoadingDialog.Builder loading = new LoadingDialog.Builder(getContext())
//                .setMessage("加载中...")
//                .setCancelable(true)
//                .setCancelOutside(true);
//        LoadingDialog dialog = loading.create();
//        dialog.show();
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        /**
         *  实例化通知栏构造器
         */

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == FLAG) {
                    Object obj = msg.obj;
                    JsonObject jsonObject = new JsonParser().parse(obj.toString()).getAsJsonObject();
                    String jsonArrayStr = jsonObject.get("data").getAsString();
                    System.out.println(jsonArrayStr + ":jsonArray");
                    String data = "[{\"kaishidate\":\"Apr 22, 2018 12:14:17 AM\",\"riqi\":\"Apr 21, 2018 10:16:31 PM\",\"id\":1,\"userid\":1,\"jieshudate\":\"Apr 22, 2018 2:14:26 AM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 1:24:38 PM\",\"riqi\":\"Apr 30, 2018 1:24:38 PM\",\"id\":2,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:14:37 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:07:36 PM\",\"riqi\":\"Apr 30, 2018 2:07:36 PM\",\"id\":3,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:15:49 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:09:02 PM\",\"riqi\":\"Apr 30, 2018 2:09:02 PM\",\"id\":4,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:18:16 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:10:43 PM\",\"riqi\":\"Apr 30, 2018 2:10:43 PM\",\"id\":5,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:18:38 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:11:52 PM\",\"riqi\":\"Apr 30, 2018 2:11:52 PM\",\"id\":6,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:19:05 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:13:26 PM\",\"riqi\":\"Apr 30, 2018 2:13:26 PM\",\"id\":7,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:19:18 PM\",\"shijian\":8.0},{\"kaishidate\":\"Apr 30, 2018 2:21:56 PM\",\"riqi\":\"Apr 30, 2018 2:21:56 PM\",\"id\":12,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:23:50 PM\",\"shijian\":4.0},{\"kaishidate\":\"Apr 30, 2018 2:29:54 PM\",\"riqi\":\"Apr 30, 2018 2:29:54 PM\",\"id\":14,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:30:30 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:34:28 PM\",\"riqi\":\"Apr 30, 2018 2:34:28 PM\",\"id\":16,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:35:29 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:35:37 PM\",\"riqi\":\"Apr 30, 2018 2:35:37 PM\",\"id\":17,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:35:46 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:35:54 PM\",\"riqi\":\"Apr 30, 2018 2:35:54 PM\",\"id\":18,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:36:02 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:36:11 PM\",\"riqi\":\"Apr 30, 2018 2:36:11 PM\",\"id\":19,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:36:19 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:36:27 PM\",\"riqi\":\"Apr 30, 2018 2:36:27 PM\",\"id\":20,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:36:35 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:36:43 PM\",\"riqi\":\"Apr 30, 2018 2:36:43 PM\",\"id\":21,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:36:51 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:38:31 PM\",\"riqi\":\"Apr 30, 2018 2:38:31 PM\",\"id\":22,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:38:39 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:38:47 PM\",\"riqi\":\"Apr 30, 2018 2:38:47 PM\",\"id\":23,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:38:55 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:39:04 PM\",\"riqi\":\"Apr 30, 2018 2:39:04 PM\",\"id\":24,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:39:15 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:39:46 PM\",\"riqi\":\"Apr 30, 2018 2:39:46 PM\",\"id\":25,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:42:17 PM\",\"shijian\":1.0},{\"kaishidate\":\"Apr 30, 2018 2:42:38 PM\",\"riqi\":\"Apr 30, 2018 2:42:38 PM\",\"id\":26,\"userid\":1,\"jieshudate\":\"Apr 30, 2018 2:43:07 PM\",\"shijian\":1.0}]";
                    System.out.println("data:"+data);



                    List<Map<String, String>> list = new Gson().fromJson(jsonArrayStr, new TypeToken<List<Map<String, String>>>() {
                    }.getType());

//                    if(num == 0){
//                        num = list.size();
//                    }else{
//                        if(list.size() > num){
//                            // Toast
////                            Toast.makeText(getContext(),"TongZhi...",Toast.LENGTH_SHORT).show();
//                            num = list.size();
//                        }
//                    }

                    for (Map<String, String> map : list) {
                        F2VO f2VO1 = new F2VO();
                        f2VO1.setText(map.get("id")+". 187****0580 " + map.get("shijian") + "H, 花费：" + Double.valueOf(map.get("shijian")) * 15 + " 元");
                        f2VO1.setRiqi(map.get("riqi"));
                        f2VOList.add(f2VO1);
                    }
                    dismissLoadingDialog();
//                    dialog.cancel();
//                    Toast.makeText(getContext(),object+"",Toast.LENGTH_LONG).show();
                }else if(msg.what ==2 ){

                    Toast.makeText(getContext(),"Tongzhi.....!!!!!!!",Toast.LENGTH_SHORT).show();
                    mBuilder.setContentTitle("车辆离开通知")
                            //设置内容
                            .setContentText("车主尾号0580，停车1小时，消费金额15元。")
                            //设置大图标
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            //设置小图标
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            //设置通知时间
                            .setWhen(System.currentTimeMillis())
                            //首次进入时显示效果
                            .setTicker("车主尾号0580，停车1小时，消费金额15元。")
                            //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                            .setDefaults(Notification.DEFAULT_SOUND);
                    //发送通知请求
                    notificationManager.notify(10, mBuilder.build());

                }
            }
        };

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                {
                    String address = StringUtil.URL+"tingchejilu";
                    System.out.println(address);
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

                  //process
                    JsonObject jsonObject = new JsonParser().parse(str.toString()).getAsJsonObject();
                    String jsonArrayStr = jsonObject.get("data").getAsString();
                    System.out.println(jsonArrayStr + ":jsonArray");
                    List<Map<String, String>> list = new Gson().fromJson(jsonArrayStr, new TypeToken<List<Map<String, String>>>() {
                    }.getType());
                    if(num == 0){
                        num = list.size();
                    }else{

                        System.out.println("list.size():"+list.size());
                        System.out.println("num:"+num);

                        if(list.size() > num){
                            num = list.size();
                            Message message = Message.obtain();
                            message.what = 2;
                            message.obj = "123";
                            handler.sendMessage(message);

                            // Toast
                            //Toast.makeText(getContext(),"TongZhi...",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,5000,5000);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //adapterView is that which adapter is clicked
//                //view click view object
//                // i is the position of view
//                // l is the id of data
//
//                //1 get messages
//                /*String sms = smss[i];
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.SEND");
//                intent.setType("text/plain");
//                intent.putExtra("sms_body",sms);
//                startActivity(intent);*/
//
//                F2VO f2VO = f2VOList.get(i);
//
//                String sms =f2VO.getText();
//                Intent intent = new Intent();
//                intent.setAction("android.itent.action.SEND");
//                intent.setType("text/plain");
//                intent.putExtra("sms_body",sms);
//                startActivity(intent);
//
//            }
//        });

        justjump = (TextView) view.findViewById(R.id.justjump);
        justjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), ListTingCheWeiActivity.class);
                startActivity(intent1);
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.showMenu:
//                Intent intent = new Intent(getActivity(),LeftMenuActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    private AlertDialog alertDialog;

    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        thread.start();
    }

    class MyThread implements Runnable{
            @Override
            public void run() {
                String address = StringUtil.URL+"tingchejilu";
                System.out.println(address);
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
}
