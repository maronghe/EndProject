package logan.dl.com.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import logan.dl.com.myapplication.R;


public class Fragment1 extends Fragment {



    private View view=null;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();

        view=inflater.inflate(R.layout.fragment1,container,false);


        return view;
    }


}
