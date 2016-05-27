package com.example.zzt.whyfi.vm;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by zzt on 5/27/16.
 * <p/>
 * Usage:
 */
public class AvatarBindingAdapters {

    @BindingAdapter({"imageResource", "imageError"})
    public static void loadImage(ImageView imageView, int id, int error) {
        if (id == 0) {
            imageView.setImageResource(error);
        } else {
            imageView.setImageResource(id);
        }
    }
}
