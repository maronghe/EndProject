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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.ListTingCheWeiActivity;
import logan.dl.com.myapplication.adapter.F2VOAdapter;
import logan.dl.com.myapplication.vo.F2VO;

/**
 * List view
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment2 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private static final int FLAG = 1;
    private Button showMenuBtn;
    private String[] smss = {"goodgoodgoodgoodgoodgoodgoodgoodgoodgoodgoodgood", "greatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreat", "perfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfect", "vvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodery good"};
    private TextView justjump;
    private Handler handler;


    private static List<F2VO> f2VOList = new ArrayList<F2VO>();

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
        view=inflater.inflate(R.layout.fragment2,container,false);


//        showMenuBtn = (Button)view.findViewById(
//                R.id.showMenu
//        ) ;
//        showMenuBtn.setOnClickListener(this);

        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    String address = "http://47.93.194.171:8081/myproject/api/welcome/tingchejilu";
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
        ).start();

        // init listview
        ListView listView = view.findViewById(R.id.f2lvlistview);
      //  listView.setAdapter(new ArrayAdapter<String>(context.getApplicationContext(), R.layout.item, smss));
        F2VOAdapter f2VOAdapter = new F2VOAdapter(context.getApplicationContext(),R.layout.item_layout_f2,f2VOList);
        listView.setAdapter(f2VOAdapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == FLAG){
                    Object obj = msg.obj;
                    JsonObject jsonObject = new JsonParser().parse(obj.toString()).getAsJsonObject();
                    Object object = jsonObject.get("data");
                    Toast.makeText(getContext(),object+"",Toast.LENGTH_LONG).show();
                }
            }
        };


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
                Intent intent1= new Intent(getActivity(), ListTingCheWeiActivity.class);
                startActivity(intent1);
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.showMenu:
//                Intent intent = new Intent(getActivity(),LeftMenuActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
