package io.github.haodongling.lib.common.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import io.github.haodongling.lib.common.R;
import io.github.haodongling.lib.common.glide.GlideHelper;
import io.github.haodongling.lib.common.glide.progress.ProgressPlaceholderDrawable;
import io.github.haodongling.lib.common.glide.transformation.BlurTransformation;


public class ImageLoader {

    public static void image(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .errorHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .placeHolder(new ProgressPlaceholderDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .cache(true)
                .load(url)
                .into(imageView);
    }

    public static void gif(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .asGif()
                .errorHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .placeHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .cache(true)
                .load(url)
                .into(imageView);
    }

    public static void banner(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .errorHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .placeHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .cache(true)
                .load(url)
                .into(imageView);
    }

    public static void userIcon(ImageView imageView, String url) {
        GlideHelper.with(imageView.getContext())
                .cache(true)
                .errorHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .placeHolder(new ProgressPlaceholderDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .load(url)
                .into(imageView);
    }

    public static void userIcon(ImageView imageView, Uri uri) {
        GlideHelper.with(imageView.getContext())
                .cache(true)
                .errorHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .placeHolder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.shape_image_place_holder))
                .load(uri)
                .into(imageView);
    }

    public static void userBlur(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
            return;
        }
        GlideHelper.with(imageView.getContext())
                .cache(true)
                .load(url)
                .transformation(new BlurTransformation(0.2F))
                .into(imageView);
    }

    public static void userBlur(ImageView imageView, Uri uri) {
        if (uri == null) {
            imageView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
            return;
        }
        GlideHelper.with(imageView.getContext())
                .cache(true)
                .load(uri)
                .transformation(new BlurTransformation(0.2F))
                .into(imageView);
    }

    public static void userBlur(ImageView imageView, int res) {
        GlideHelper.with(imageView.getContext())
//                .errorHolder(R.drawable.image_holder)
//                .placeHolder(R.drawable.image_holder)
                .cache(true)
                .load(res)
                .transformation(new BlurTransformation(0.2F))
                .into(imageView);
    }
}
