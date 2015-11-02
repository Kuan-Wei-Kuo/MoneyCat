package com.kuo.moneycat.mode.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuo.moneycat.R;

import java.util.ArrayList;

/**
 * Created by Kuo on 2015/10/22.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder> {

    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public DrawerAdapter(ArrayList<DrawerItem> drawerItems) {
        this.drawerItems = drawerItems;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_recycler_item, parent, false);

        return new DrawerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, final int position) {

        holder.icon.setBackgroundResource(drawerItems.get(position).getIconResId());
        holder.title.setText(drawerItems.get(position).getTitleText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    onItemClickListener.onClick(view, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
