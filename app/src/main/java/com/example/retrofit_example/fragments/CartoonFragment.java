package com.example.retrofit_example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit_example.MainActivity;
import com.example.retrofit_example.R;
import com.example.retrofit_example.adapter.ArticleRecyclerViewAdapter;
import com.example.retrofit_example.response.ArticleData;

public class CartoonFragment extends Fragment {
    private View view;
    private static CartoonFragment instance;
    private ArticleRecyclerViewAdapter articleAdapter;
    private RecyclerView cartoonRecyclerView;

    public CartoonFragment() {}

    public static CartoonFragment getInstance() {
        if (instance == null) {
            instance = new CartoonFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cartoon, container, false);
        cartoonRecyclerView = view.findViewById(R.id.cartoon_recylcer_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 1);
        cartoonRecyclerView.setLayoutManager(gridLayoutManager);
        ((MainActivity)getActivity()).callMangaData();
        return view;
    }

    public void inicializeCartoonData(ArticleData articleData){
        articleAdapter=new ArticleRecyclerViewAdapter(getActivity(), articleData);
        cartoonRecyclerView.setAdapter(articleAdapter);
    }
}