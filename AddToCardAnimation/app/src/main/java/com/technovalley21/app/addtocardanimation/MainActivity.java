package com.technovalley21.app.addtocardanimation;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technovalley21.app.addtocardanimation.adapter.ProductAdapter;
import com.technovalley21.app.addtocardanimation.model.Product;
import com.technovalley21.app.addtocardanimation.utils.CartAnimation;
import com.technovalley21.app.addtocardanimation.utils.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private int itemCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> list = Util.demoProducts();

        ProductAdapter adapter = new ProductAdapter(this, list);
        mRecyclerView.setAdapter(adapter);
        adapter.setActionListener(new ProductAdapter.ProductItemActionListener() {
            @Override
            public void onItemTap(ImageView imageView) {
                if (imageView != null)
                    makeCartAnimation(imageView);
            }
        });

    }

    private void makeCartAnimation(ImageView targetView) {

        RelativeLayout destView = (RelativeLayout) findViewById(R.id.cartRelativeLayout);

        new CartAnimation().attachActivity(this).setTargetView(targetView).setMoveDuration(500).setDestView(destView).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                addItemToCart();
                Toast.makeText(MainActivity.this, "Continue Shopping...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();

    }

    private void addItemToCart() {
        TextView textView = (TextView) findViewById(R.id.textNotify);
        textView.setText(String.valueOf(++itemCounter));
    }
}
