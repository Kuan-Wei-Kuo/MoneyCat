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

    private int flagFocus = 0;

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

        holder.drawerItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    flagFocus = position;
                    onItemClickListener.onClick(view, position);
                    notifyItemChanged(flagFocus);
                    notifyItemChanged(position);
                }
            }
        });

        if(position == flagFocus) {
            holder.drawerItemLayout.setBackgroundColor(holder.drawerItemLayout.getResources().getColor(R.color.Grey_400));
        } else {
            holder.drawerItemLayout.setBackgroundColor(holder.drawerItemLayout.getResources().getColor(R.color.Grey_50));
        }

    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setFlagFocus(int flagFocus) {
        this.flagFocus = flagFocus;
    }
}
