package com.xhn.animatorsetdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button menu, item1, item2, item3, item4, item5;
    private boolean mIsMenuOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        menu = (Button) findViewById(R.id.menu);
        item1 = (Button) findViewById(R.id.item1);
        item2 = (Button) findViewById(R.id.item2);
        item3 = (Button) findViewById(R.id.item3);
        item4 = (Button) findViewById(R.id.item4);
        item5 = (Button) findViewById(R.id.item5);

        menu.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == menu) {
            if (mIsMenuOpen) {
                mIsMenuOpen = false;
                doAnimateClose(item1, 0, 5, 500);
                doAnimateClose(item2, 1, 5, 500);
                doAnimateClose(item3, 2, 5, 500);
                doAnimateClose(item4, 3, 5, 500);
                doAnimateClose(item5, 4, 5, 500);
            } else {
                mIsMenuOpen = true;
                doAnimateOpen(item1, 0, 5, 500);
                doAnimateOpen(item2, 1, 5, 500);
                doAnimateOpen(item3, 2, 5, 500);
                doAnimateOpen(item4, 3, 5, 500);
                doAnimateOpen(item5, 4, 5, 500);

            }
        } else {
            Toast.makeText(this, "你点击了" + view, Toast.LENGTH_SHORT).show();
        }

    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
//        set.setInterpolator(new BounceInterpolator());
        //动画周期为500ms
        set.setDuration(1 * 500).start();

    }

    private void doAnimateClose(final View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setScaleX(1.0f);
                view.setScaleY(1.0f);
                if (view.getVisibility() == View.VISIBLE) {
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        set.setDuration(1 * 500).start();
    }
}
