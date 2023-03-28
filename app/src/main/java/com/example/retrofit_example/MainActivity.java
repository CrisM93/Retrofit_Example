package com.example.retrofit_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;

import com.example.retrofit_example.adapter.FragmentViepagerAdapter;
import com.example.retrofit_example.fragments.AnimeFragment;
import com.example.retrofit_example.fragments.CartoonFragment;
import com.example.retrofit_example.fragments.MangaFragment;
import com.example.retrofit_example.response.ArticleData;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy, RequestHandler.RequestListener {
    private AnimeFragment animeFragment = AnimeFragment.getInstance();
    private MangaFragment mangaFragment = MangaFragment.getInstance();
    private CartoonFragment cartoonFragment = CartoonFragment.getInstance();
    private List<Fragment> fragmentList = new ArrayList<>();
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.articles_viewpager);
        //TabLayout tabs = findViewById(R.id.tab_layout);

        fragmentList.add(animeFragment);
        fragmentList.add(mangaFragment);
        fragmentList.add(cartoonFragment);

        //DotsIndicator dotsIndicator =  findViewById(R.id.dots_indicator);
        WormDotsIndicator dotsIndicator = findViewById(R.id.worm_dots_indicator);


        FragmentViepagerAdapter viewPagerAdapter = new FragmentViepagerAdapter(fragmentList, this);

        viewPager2.setAdapter(viewPagerAdapter);
        dotsIndicator.attachTo(viewPager2);
        //viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        //viewPager2.addItemDecoration(new SpacesItemDecoration(-50));
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.setPageTransformer((page, position) -> {
            float pageTranslationX = 280;
            page.setTranslationX(-pageTranslationX * position);
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.setScaleY(1f - (0.30f * Math.abs(position)));
            // page.setScaleX(1f);
            // If you want a fading effect uncomment the next line:
            page.setAlpha(0.25f + (1 - Math.abs(position)));
        });

        //new TabLayoutMediator(tabs, viewPager2, this).attach();

        requestHandler = new RequestHandler(this, this);
        requestHandler.getArticles(StringUtlis.ARTICLES.ANIME);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        switch (position) {
            case 0: {
                tab.setText("MANGA");
                break;
            }
            case 1: {
                tab.setText("ANIME");
                break;
            }
            case 2: {
                tab.setText("CARTOON");
                break;
            }
        }
    }

    @Override
    public void animeResponse(ArticleData articleData) {
        animeFragment.initializeAnimeData(articleData);
        cartoonFragment.inicializeCartoonData(articleData);
    }

    @Override
    public void mangaResponse(ArticleData articleData) {
        mangaFragment.initializeMangaData(articleData);
    }

    public void callMangaData() {
        requestHandler.getArticles(StringUtlis.ARTICLES.MANGA);
    }
}