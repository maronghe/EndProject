package logan.dl.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps.MapView;

public class MainActivity extends AppCompatActivity {


    private TextView lat_textView;
    private TextView lon_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat_textView = findViewById(R.id.lat);
        lon_textView = findViewById(R.id.lon);

        Intent intent = this.getIntent();
        Double lat = intent.getDoubleExtra("lat",0.0d);
        Double lon = intent.getDoubleExtra("lon",0.0d);

        System.out.println("hengzuobiao = "+ lat  + "zhongzuobiao = "+ lon);

        lat_textView.setText(""+lat);
        lon_textView.setText(""+lon);
    }
}
