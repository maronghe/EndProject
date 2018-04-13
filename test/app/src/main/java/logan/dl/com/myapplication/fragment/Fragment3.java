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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.ShowMapActivity;
import logan.dl.com.myapplication.activity.ChongZhiActivity;
import logan.dl.com.myapplication.activity.LeftMenuActivity;
import logan.dl.com.myapplication.other.utils.HttpCallbackListener;
import logan.dl.com.myapplication.other.utils.HttpUtil;

/**
 * Show left Menu
 * Created by zhjzhang on 1/25/18.
 */

public class Fragment3 extends Fragment implements View.OnClickListener{

    private View view=null;
    private Context context;
    private Button showMapBtn;
    private Button chongzhiBtn;

    private TextView myMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment3,container,false);
        showMapBtn = (Button)view.findViewById(
                R.id.showMap
        ) ;
        chongzhiBtn = (Button)view.findViewById(R.id.chongzhi);
        showMapBtn.setOnClickListener(this);
        chongzhiBtn.setOnClickListener(this);
        myMoney  =  (TextView) view.findViewById(R.id.urmoney);
        String address = "http://47.93.194.171:8081/myproject/api/welcome/getMoney?id=1";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
                final String name = json.get("money").getAsString();
                // update ui
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myMoney.append(" " + name);
                        myMoney.append(" Yuan.");
                    }
                });

                Log.d("TAG", response.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.d("TAG", e.toString());
            }
        });



        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showMap:
                Intent intent = new Intent(getActivity(),ShowMapActivity.class);
                startActivity(intent);
                break;
            case  R.id.chongzhi:
                startActivity(new Intent(getActivity(), ChongZhiActivity.class));
                break;
        }
    }

}
