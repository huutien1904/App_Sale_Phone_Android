package com.example.btl_appbandienthoai.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.btl_appbandienthoai.Class.SlidePhoto;
import com.example.btl_appbandienthoai.R;

import java.util.List;

public class SlidePhotoAdapter extends PagerAdapter {
    private List<SlidePhoto> mListSlidePhoto;
    private Fragment mfragment;

    public SlidePhotoAdapter(List<SlidePhoto> mListSlidePhoto,Fragment mfragment) {
        this.mListSlidePhoto = mListSlidePhoto;
        this.mfragment = mfragment;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide_photo,container,false);
        ImageView imgSlidePhoto = view.findViewById(R.id.img_slide_photo);

        SlidePhoto slidePhoto = mListSlidePhoto.get(position);
        if (slidePhoto != null){
            Glide.with(mfragment).load(slidePhoto.getResourceId()).into(imgSlidePhoto);
        }
        container.addView(view);

        return view;
    }
    @Override
    public int getCount() {
        if(mListSlidePhoto != null){
            return mListSlidePhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
