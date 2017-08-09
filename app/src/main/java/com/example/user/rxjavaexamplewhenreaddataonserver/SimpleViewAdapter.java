package com.example.user.rxjavaexamplewhenreaddataonserver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/9/2017.
 */

public class SimpleViewAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private LayoutInflater inflater;
    private List<String> mStringList;

    public void setmStringList(List<String> mStringList) {
        this.mStringList = mStringList;
    }

    public SimpleViewAdapter(Context context) {
        inflater=LayoutInflater.from(context);
        mStringList=new ArrayList<>();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflater.inflate(R.layout.list_simple_string,parent,false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.mTextView.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }
}
