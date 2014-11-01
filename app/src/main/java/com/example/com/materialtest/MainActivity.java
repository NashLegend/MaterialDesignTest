package com.example.com.materialtest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;


public class MainActivity extends ActionBarActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //if set, recyclerView will layout items horizontally
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        AnimationSet animationSet = new AnimationSet(false);
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(100);
        animationSet.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(animationSet, 1);
        recyclerView.setLayoutAnimation(controller);

        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case R.id.action_addItem:
                recyclerAdapter.addItem();
                break;
            case R.id.action_removeItem:
                recyclerAdapter.removeItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
