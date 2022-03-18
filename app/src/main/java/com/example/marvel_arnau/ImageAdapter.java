package com.example.marvel_arnau;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private String[] mImagesIds;

    public ImageAdapter(Context mContext, String[] mImagesIds) {
        this.mContext = mContext;
        this.mImagesIds = mImagesIds;
    }

    @Override
    public int getCount() {
        return mImagesIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(mImagesIds[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
