package com.example.instockalert;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<String> type;
    List<String> info;
    List<Integer> image;



    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Recycler View
        type = new ArrayList<>();
        info = new ArrayList<>();
        image = new ArrayList<>();
        type.add("GPU 1");
        info.add("In Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 2");
        info.add("In Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 3");
        info.add("In Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 4");
        info.add("Out of Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 5");
        info.add("Out of Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 6");
        info.add("In Stock");
        image.add(R.drawable.gpu_test);
        type.add("GPU 7");
        info.add("In Stock");
        image.add(R.drawable.gpu_test);

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.itemTracker);
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 3. Create an adapter
        MyAdapter myAdapter = new MyAdapter(getActivity(), type, info, image);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        // 4. Set adapter
        recyclerView.setAdapter(myAdapter);
        // 5. Set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
