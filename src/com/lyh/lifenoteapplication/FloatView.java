package com.lyh.lifenoteapplication;

import android.content.Context;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class FloatView extends ImageView {
    private float startX; // the first pointer index of x coordinate
    private float startY; // the first pointer index of Y coordinate
    private float x;// the coordinate of X
    private float y;// the coordinate of Y

    private WindowManager wm = (WindowManager) getContext().getApplicationContext()
            .getSystemService(getContext().WINDOW_SERVICE);
    private WindowManager.LayoutParams wmlp = ((MyApplication) getContext().getApplicationContext())
            .getLayoutParams();

    public FloatView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // To get the coordinate on the screen
        x = event.getRawX();
        y = event.getRawY() - 25; // 25 the height of the status bar
        switch (event.getAction()) {
        // begin to move the view
        case MotionEvent.ACTION_DOWN:
            startX = event.getX();
            startY = event.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            updateViewPosition();
            break;
        case MotionEvent.ACTION_UP:
            updateViewPosition();
            startX = startY = 0;
            break;
        }
        return true;
    }

    private void updateViewPosition() {
        // To change the position of the float view
        wmlp.x = (int) (x - startX);
        wmlp.y = (int) (y - startY);
        wm.updateViewLayout(this, wmlp);
    }
}
