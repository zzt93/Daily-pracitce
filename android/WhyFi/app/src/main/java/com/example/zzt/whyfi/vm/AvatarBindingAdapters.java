package com.example.zzt.whyfi.vm;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.zzt.whyfi.model.Device;

import java.util.HashMap;

/**
 * Created by zzt on 5/27/16.
 * <p/>
 * Usage:
 */
public class AvatarBindingAdapters {

    private static HashMap<ImageView, Device> map = new HashMap<>();

    @BindingAdapter({"imageResource", "imageError"})
    public static void loadImage(ImageView imageView, int id, int error) {
        if (id == 0) {
            imageView.setImageResource(error);
        } else {
            imageView.setImageResource(id);
        }
    }

    @BindingAdapter("device")
    public static void setDevice(ImageView imageView, Device device) {
        map.put(imageView, device);
    }

    public static Device getDevice(ImageView view) {
        return map.get(view);
    }
}
