package logan.dl.com.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import logan.dl.com.myapplication.R;
import logan.dl.com.myapplication.vo.F2VO;

/**
 * Created by zhjzhang on 3/7/18.
 */

public class F2VOAdapter extends ArrayAdapter<F2VO> {

    private int resourceId;
    public F2VOAdapter(Context context, int resource, List<F2VO> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        F2VO f2vo=getItem(position); //获得当前项的Hero数据
        View view;
        ViewHolder viewHolder; //使用ViewHolder优化 ListView
        if (convertView==null){ //使用convertView重复使用查找加载好的布局
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);//使用布局填充器为子项加载我们传入的子布局「hero_item」
            viewHolder=new ViewHolder();
            viewHolder.tv_text= (TextView) view.findViewById(R.id.f2_text);//查找
            viewHolder.et_data= (EditText) view.findViewById(R.id.f2_date);
            view.setTag(viewHolder);//把ViewHolder储存在View里面

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
//        viewHolder.tv_text.setImageResource(hero.getImageId()); //设置数据
//        viewHolder.et_data.setText(hero.getName());
        viewHolder.tv_text.setText(f2vo.getText());
        viewHolder.et_data.setText(f2vo.getRiqi());
        return view;
    }
    class ViewHolder{
        TextView tv_text;
        EditText et_data;
    }
}