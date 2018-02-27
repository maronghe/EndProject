package logan.dl.com.myapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import logan.dl.com.myapplication.R;

public class AppStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//必须放在setContentView之前执行，不然会报错的。
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_start);
        //去掉Activity标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                Intent intent = new Intent(AppStartActivity.this,TotalActivity.class);
                // go to log in page
                Intent intent = new Intent(AppStartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }


}
