package com.kuo.moneycat.mode.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuo.moneycat.R;


/**
 * Created by Kuo on 2015/10/22.
 */
public class DrawerViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView title;
    public LinearLayout drawerItemLayout;

    public DrawerViewHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.iconImage);
        title = (TextView) itemView.findViewById(R.id.titleText);
        drawerItemLayout = (LinearLayout) itemView.findViewById(R.id.drawerItemLayout);
    }

}
