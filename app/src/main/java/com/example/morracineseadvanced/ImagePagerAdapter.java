package com.example.morracineseadvanced;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ImagePagerAdapter extends FragmentStateAdapter {

    private List<Integer> imageList;

    public ImagePagerAdapter(FragmentActivity fragmentActivity, List<Integer> imageList) {
        super(fragmentActivity);
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Ritorna un nuovo frammento che visualizza l'immagine corrispondente
        return ImageFragment.newInstance(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        // Ritorna il numero totale di immagini
        return imageList.size();
    }
}
