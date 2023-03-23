package com.example.retrofit_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.retrofit_example.adapter.FragmentViepagerAdapter;
import com.example.retrofit_example.fragments.AnimeFragment;
import com.example.retrofit_example.fragments.CartoonFragment;
import com.example.retrofit_example.fragments.MangaFragment;
import com.example.retrofit_example.response.ArticleData;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy, RequestHandler.RequestListener {
    private AnimeFragment animeFragment = AnimeFragment.getInstance();
    private MangaFragment mangaFragment = MangaFragment.getInstance();
    private CartoonFragment cartoonFragment= CartoonFragment.getInstance();
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
        fragmentList.add(cartoonFragment);

        FragmentViepagerAdapter fragmentViepagerAdapter = new FragmentViepagerAdapter(fragmentList, this);

        articlesViewPager.setAdapter(fragmentViepagerAdapter);

       // articlesViewPager.setPageTransformer(new ZoomOutPageTransformer());
        //articlesViewPager.addItemDecoration(new SpacesItemDecoration(42));
        articlesViewPager.setOffscreenPageLimit(3);

        //float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        //float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

       articlesViewPager.setPageTransformer(((page, position) -> {
            float myOffset = position * -(2 * 120 + 55);
            if (position < -1) {
                page.setTranslationX(-myOffset);
            } else if (position <= 1) {
                float scaleFactor = Math.max(0.7f, 1f - Math.abs(position - 0.14285715f));
                page.setTranslationX(myOffset);
                page.setScaleY(scaleFactor);
                page.setAlpha(scaleFactor);
            } else {
                page.setAlpha(0f);
                page.setTranslationX(myOffset);
            }
        }));

        new TabLayoutMediator(tabs, articlesViewPager, this).attach();

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
            case 2:{
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