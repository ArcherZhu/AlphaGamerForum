package com.shiro.issolution.gamerforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiro.issolution.gamerforum.MainActivity;
import com.shiro.issolution.gamerforum.PostListActivity;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.adapter.PlateAdapter;
import com.shiro.issolution.gamerforum.model.Plate;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FragmentHome extends Fragment {
    RecyclerView plateList;
    List<Plate> mPlateList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_home, container, false);
        plateList = (RecyclerView) view.findViewById(R.id.list_plate);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 3);
        plateList.setLayoutManager(layoutManager);
        mPlateList = new ArrayList<Plate>();
        BmobQuery<Plate> query = new BmobQuery<Plate>();
        query.findObjects(new FindListener<Plate>() {
            @Override
            public void done(List<Plate> list, BmobException e) {
                if (e == null) {
                    mPlateList = list;
                    PlateAdapter adapter = new PlateAdapter(mPlateList);
                    adapter.setOnPlateListener(new PlateAdapter.OnPlateListener() {
                        @Override
                        public void onPlateClick(int position, String plateId, String plateName) {
                            Intent i = new Intent("com.shiro.issolution.gamerForum.ACTION_POST_LIST");
                            i.putExtra("plateId", plateId);
                            i.putExtra("plateName",plateName);
                            startActivity(i);
                        }
                    });
                    plateList.setAdapter(adapter);
                } else {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

}
