package com.example.retrofit_example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofit_example.MainActivity;
import com.example.retrofit_example.R;
import com.example.retrofit_example.adapter.ArticleRecyclerViewAdapter;
import com.example.retrofit_example.response.ArticleData;

public class MangaFragment extends Fragment {
    private static MangaFragment instance;
    private ArticleRecyclerViewAdapter articleAdapter;
    private RecyclerView mangaRecyclerView;
    //private View view;
    private View view;

    public MangaFragment() {

    }

    public static MangaFragment getInstance() {
        if (instance == null) {
            instance = new MangaFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manga, container, false);

        mangaRecyclerView = view.findViewById(R.id.manga_recylcer_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mangaRecyclerView.setLayoutManager(gridLayoutManager);
        ((MainActivity)getActivity()).callMangaData();

        return view;
    }

    public void initializeMangaData(ArticleData articleData) {
        articleAdapter = new ArticleRecyclerViewAdapter(getActivity(), articleData);
        mangaRecyclerView.setAdapter(articleAdapter);
    }
}