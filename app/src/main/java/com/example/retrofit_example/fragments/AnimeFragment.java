package com.example.retrofit_example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit_example.R;
import com.example.retrofit_example.adapter.ArticleRecyclerViewAdapter;
import com.example.retrofit_example.response.ArticleData;


public class AnimeFragment extends Fragment {
    private static AnimeFragment instance;
    private ArticleRecyclerViewAdapter articleAdapter;
    private RecyclerView animeRecyclerview;
    private View view;

    private AnimeFragment() {
    }

    public static AnimeFragment getInstance() {
        if (instance == null) {
            instance = new AnimeFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_anime, container, false);
        animeRecyclerview = view.findViewById(R.id.anime_recylcer_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        animeRecyclerview.setLayoutManager(gridLayoutManager);


        return view;
    }

    public void initializeAnimeData(ArticleData articleData) {
        articleAdapter = new ArticleRecyclerViewAdapter(getActivity(), articleData);
        animeRecyclerview.setAdapter(articleAdapter);
    }
}