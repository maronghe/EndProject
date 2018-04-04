package logan.dl.com.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import logan.dl.com.myapplication.R;

public class ListTingCheWeiActivity extends AppCompatActivity {
    String[] smss = {"goodgoodgoodgoodgoodgoodgoodgoodgoodgoodgoodgood", "greatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreatgreat", "perfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfectperfect", "vvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodvery goodery good"};

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


//        ListView listView = findViewById(R.id.lv_item_listview);
//        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.tcitem, smss));

    }



}
