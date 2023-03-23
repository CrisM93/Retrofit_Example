package com.example.retrofit_example.adapter;

import android.os.LimitExceededException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.retrofit_example.fragments.AnimeFragment;
import com.example.retrofit_example.fragments.CartoonFragment;
import com.example.retrofit_example.fragments.MangaFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentViepagerAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragmentList;

    public FragmentViepagerAdapter(@NonNull List<Fragment> fragmentList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment frag = AnimeFragment.getInstance();
        switch (position) {
            case 0: {
                frag = AnimeFragment.getInstance();
                break;
            }
            case 1: {
                frag = MangaFragment.getInstance();
                break;
            }
            case 2: {
                frag = CartoonFragment.getInstance();
                break;
            }
        }
        return frag;
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}

