package com.kuo.moneycat.mode.cost;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuo.moneycat.R;

/**
 * Created by Kuo on 2015/10/23.
 */
public class CostViewHolder extends RecyclerView.ViewHolder {

    public ImageButton iconImage;
    public TextView titleText, costText;
    public RelativeLayout infoLayout;

    public CostViewHolder(View itemView) {
        super(itemView);

        iconImage = (ImageButton) itemView.findViewById(R.id.iconImage);
        titleText = (TextView) itemView.findViewById(R.id.titleText);
        costText = (TextView) itemView.findViewById(R.id.costText);
        infoLayout = (RelativeLayout) itemView.findViewById(R.id.infoLayout);

    }

}
