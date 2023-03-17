package com.example.retrofit_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit_example.adapter.FragmentViepagerAdapter;
import com.example.retrofit_example.fragments.AnimeFragment;
import com.example.retrofit_example.fragments.MangaFragment;
import com.example.retrofit_example.response.ArticleData;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy, RequestHandler.RequestListener {
    private AnimeFragment animeFragment = AnimeFragment.getInstance();
    private MangaFragment mangaFragment = MangaFragment.getInstance();
    private List<Fragment> fragmentList = new ArrayList<>();
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 articlesViewPager = findViewById(R.id.articles_viewpager);
        TabLayout tabs = findViewById(R.id.tab_layout);

        fragmentList.add(animeFragment);
        fragmentList.add(mangaFragment);

        FragmentViepagerAdapter fragmentViepagerAdapter = new FragmentViepagerAdapter(fragmentList, this);

        articlesViewPager.setAdapter(fragmentViepagerAdapter);

        new TabLayoutMediator(tabs, articlesViewPager, this).attach();

        requestHandler = new RequestHandler(this, this);
        requestHandler.getArticles(StringUtlis.ARTICLES.ANIME);
    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        switch (position) {
            case 0: {
                tab.setText("ANIME");
                break;
            }
            case 1: {
                tab.setText("MANGA");
                break;
            }
        }
    }

    @Override
    public void animeResponse(ArticleData articleData) {
        animeFragment.initializeAnimeData(articleData);
    }

    @Override
    public void mangaResponse(ArticleData articleData) {
        mangaFragment.initializeMangaData(articleData);

    }

    public void callMangaData() {
        requestHandler.getArticles(StringUtlis.ARTICLES.MANGA);
    }
}