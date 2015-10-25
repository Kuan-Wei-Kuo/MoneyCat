package com.kuo.moneycat.mode.cost;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuo.moneycat.R;

import java.util.ArrayList;

/**
 * Created by Kuo on 2015/10/23.
 */
public class CostAdapter extends RecyclerView.Adapter<CostViewHolder> {

    private ArrayList<CostItem> costItems = new ArrayList<>();
    private OnClickListener onClickListenerAdapter;

    public interface OnClickListener {
        void onClick(View view);
    }

    public CostAdapter(ArrayList<CostItem> costItems) {
        this.costItems = costItems;
    }

    @Override
    public CostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cost_recycler_item, parent, false);

        return new CostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CostViewHolder holder, int position) {

        holder.iconImage.setBackgroundResource(costItems.get(position).getIconImage());
        holder.titleText.setText(costItems.get(position).getTitleText());
        holder.costText.setText("$ " + costItems.get(position).getCost());
        //holder.infoLayout.setOnClickListener(onClickListener);

        holder.itemView.setOnClickListener(onClickListener);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.iconImage.setTransitionName("iconImage" + position);
        }
    }

    @Override
    public int getItemCount() {
        return costItems.size();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(onClickListenerAdapter != null) {
                onClickListenerAdapter.onClick(view);
            }

        }
    };

    public void setOnClickListener(OnClickListener onClickListenerAdapter) {
        this.onClickListenerAdapter = onClickListenerAdapter;
    }
}
