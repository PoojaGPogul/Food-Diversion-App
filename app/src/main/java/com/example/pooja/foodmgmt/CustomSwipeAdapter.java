package com.example.pooja.foodmgmt;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomSwipeAdapter  extends PagerAdapter {
    private int []image_resources = {R.drawable.logo};
    private Context ctx;
    int cnt=0;
    private LayoutInflater layoutInflater;
    public  CustomSwipeAdapter()
    {

    }
    public CustomSwipeAdapter(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return  ((view == (LinearLayout)o));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.activity_custom_swipe_adapter,container,false);

        TextView t1 = (TextView) item_view.findViewById(R.id.app_name);
        TextView t = (TextView) item_view.findViewById(R.id.slogan);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Swiper.context, MainActivity.class);
                Swiper.context.startActivity(i);
                Swiper.context.finish();
            }
        }, 2000);


            if (position == 0) {
                t1.setText("FOOD DIVERSION");
                t.setText("\nWillful waste makes woeful want!!!");
            }

            ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
            imageView.setImageResource(image_resources[position]);
            container.addView(item_view);
        return item_view;
    }

}

