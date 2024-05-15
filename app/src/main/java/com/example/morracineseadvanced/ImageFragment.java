package com.example.morracineseadvanced;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {

    private static final String ARG_IMAGE_RESOURCE = "image_resource";

    private int imageResource;

    public static ImageFragment newInstance(int imageResource) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RESOURCE, imageResource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageResource = getArguments().getInt(ARG_IMAGE_RESOURCE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = rootView.findViewById(R.id.imageView);
        imageView.setImageResource(imageResource);
        return rootView;
    }
}
