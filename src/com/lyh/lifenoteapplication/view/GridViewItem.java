package com.lyh.lifenoteapplication.view;

import com.lyh.lifenoteapplication.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridViewItem extends RelativeLayout {

    private View mView;
    private TextView mTitle;
    private ImageView mIcon;

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mView = inflate(context, R.layout.gridview_item, null);
        if (mView != null) {
            mTitle = (TextView) mView.findViewById(R.id.text);
            mIcon = (ImageView) mView.findViewById(R.id.icon);
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GridViewItem);
            mTitle.setText(typedArray.getText(R.styleable.GridViewItem_text));
            mIcon.setImageResource(typedArray.getResourceId(R.styleable.GridViewItem_iconSrc,
                    R.drawable.search));
            typedArray.recycle();
            addView(mView);
            setGravity(Gravity.CENTER_VERTICAL);
        }
    }

}
