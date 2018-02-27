package logan.dl.com.myapplication.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.fragment.Fragment1;
import logan.dl.com.myapplication.fragment.Fragment2;
import logan.dl.com.myapplication.fragment.Fragment3;
import logan.dl.com.myapplication.fragment.Fragment4;

public class TotalActivity extends AppCompatActivity {

    private LinearLayout layout1, layout2, layout3, layout4;//用于界面切换
    private ImageButton ib1, ib2, ib3, ib4;//用户设置图片

    private ViewPager vp;//用于滑动切换

    private List<Fragment> listfragment;//Fragment  list
    private FragmentPagerAdapter fpadapter;//用来呈现Fragment页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        init();// page init
        initFragment();//初始化Fragment相关
       // initToolBar();

    }

    private void initToolBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        /*控制侧滑菜单*/
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        /*设定NavigationView菜单的选择事件*/
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        //实例化ImageButton为了切换图片
        ib1 = (ImageButton) findViewById(R.id.imageButton1);
        ib2 = (ImageButton) findViewById(R.id.imageButton2);
        ib3 = (ImageButton) findViewById(R.id.imageButton3);
        ib4 = (ImageButton) findViewById(R.id.imageButton4);
        //实例化Layout为了底部栏点击事件切换页面
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);

        //layout点击事件
        layout1.setOnClickListener(onClickListener);
        layout2.setOnClickListener(onClickListener);
        layout3.setOnClickListener(onClickListener);
        layout4.setOnClickListener(onClickListener);
    }


    //layout点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //先将四个按钮都设成未选图片
            resetImage();
            //然后根据底部的选择切换Fragment和ViewPager
            switch (view.getId()) {
                case R.id.layout1:
//                    Toast.makeText(MainActivity.this, "切换底部栏weixin", Toast.LENGTH_SHORT).show();
                    selectTab(0);
                    break;
                case R.id.layout2:
//                    Toast.makeText(MainActivity.this, "切换底部栏pengyou", Toast.LENGTH_SHORT).show();
                    selectTab(1);
                    break;
                case R.id.layout3:
//                    Toast.makeText(MainActivity.this, "切换底部栏tongxunlu", Toast.LENGTH_SHORT).show();
                    selectTab(2);
                    break;
                case R.id.layout4:
//                    Toast.makeText(MainActivity.this, "切换底部栏shezhi", Toast.LENGTH_SHORT).show();
                    selectTab(3);
                    break;
            }
        }
    };

    //重置底部栏图片为未选
    private void resetImage() {
        ib1.setImageResource(R.drawable.weixin_normal);
        ib2.setImageResource(R.drawable.friend_normal);
        ib3.setImageResource(R.drawable.address_normal);
        ib4.setImageResource(R.drawable.settings_normal);
    }

    //切换Fragment和ViewPager
    private void selectTab(int i) {
        switch (i) {
            case 0:
                ib1.setImageResource(R.drawable.weixin_pressed);
                break;
            case 1:
                ib2.setImageResource(R.drawable.friend_pressed);
                break;
            case 2:
                ib3.setImageResource(R.drawable.address_pressed);
                break;
            case 3:
                ib4.setImageResource(R.drawable.settings_pressed);
                break;
        }
        /**
         * 设置界面变化
         */
        vp.setCurrentItem(i);
    }


    private void initFragment(){
        listfragment = new ArrayList<Fragment>();//实例化Fragment list
        //将Fragment加入到list中

        listfragment.add(new Fragment1());
        listfragment.add(new Fragment2());
        listfragment.add(new Fragment3());
        listfragment.add(new Fragment4());

        //实例化FragmentPagerAdapter
        fpadapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            //获取某个Fragment
            @Override
            public Fragment getItem(int position) {
                return listfragment.get(position);
            }

            //确定Fragment数量
            @Override
            public int getCount() {
                return listfragment.size();
            }
        };
        vp = (ViewPager) findViewById(R.id.viewPager);//实例化ViewPager
        vp.setAdapter(fpadapter);//绑定ViewPager和Adapter
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //根据ViewPager的position选择某Fragment
            @Override
            public void onPageSelected(int position) {
                System.out.println("----" + String.valueOf(position));
                vp.setCurrentItem(position);
                resetImage();//底部图片重置
                selectTab(position);//选择某个Fragment
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("onPageScrollStateChanged:state:" + state);
            }
        });

    }


}
