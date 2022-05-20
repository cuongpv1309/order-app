package com.example.orderapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.orderapp.R;

public class SpinnerAdapter extends BaseAdapter {

    private int[] imgs = {R.drawable.cafecotdua,R.drawable.cafetrung,R.drawable.capuchino,
            R.drawable.chon,R.drawable.culi, R.drawable.den, R.drawable.moka, R.drawable.macchiato};
    private Context context;

    public SpinnerAdapter(int[] imgs, Context context) {
        this.imgs = imgs;
        this.context = context;
    }

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    public SpinnerAdapter() {
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(this.context).inflate(R.layout.spinner_coffee, viewGroup, false);
        ImageView img = item.findViewById(R.id.anh);
        img.setImageResource(imgs[i]);
        return item;
    }

}
