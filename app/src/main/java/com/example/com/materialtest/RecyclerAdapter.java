package com.example.com.materialtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by NashLegend on 2014/10/24 0024
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    String[] ds = {"First String", "Second Chars", "Third You Know", "Fourth Exists", "Fifth Lives",
            "Nice Number", "Fluent Kit", "Fortune Char", "Best Data", "Last One", "Milestone Number for",
            "Two Multiply Six", "A Bad Number"};
    ArrayList<String> list = new ArrayList<String>();

    public RecyclerAdapter() {
    }

    Timer initTimer;
    int currentTime = 0;
    int animateNumber = 6;

    public void init() {
        initTimer = TimerUtil.setInterval(new Runnable() {
            @Override
            public void run() {
                list.add(ds[currentTime]);
                notifyItemInserted(currentTime);
                currentTime++;
                if (animateNumber < ds.length) {
                    if (currentTime >= animateNumber && currentTime < ds.length) {
                        TimerUtil.clearInterval(initTimer);
                        for (int i = currentTime; i < ds.length; i++) {
                            list.add(ds[i]);
                        }
                        notifyItemRangeInserted(currentTime, ds.length - currentTime);
                    } else if (currentTime >= ds.length) {
                        TimerUtil.clearInterval(initTimer);
                    }
                } else if (currentTime >= ds.length) {
                    TimerUtil.clearInterval(initTimer);
                }
            }
        }, 100, 100);
    }

    public void addItem() {
        if (list.size() > 0) {
            list.add(1, "AddedItem");
            notifyItemInserted(1);
        } else {
            list.add(0, "AddedItem");
            notifyItemInserted(0);
        }

    }

    public void removeItem() {
        if (list.size() > 0) {
            list.remove(0);
            notifyItemRemoved(0);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.txt, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int i) {
        viewHolder.tv.setText(i + ": " + list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        public TextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.txt);
        }
    }

}
