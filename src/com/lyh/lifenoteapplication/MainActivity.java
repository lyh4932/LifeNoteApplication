package com.lyh.lifenoteapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    private WindowManager wm;
    private WindowManager.LayoutParams wmlp;
    private FloatView fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFloatView();
    }

    private void createFloatView() {
        fl = new FloatView(getApplicationContext());
        fl.setImageResource(R.drawable.ic_launcher);
        wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
        wmlp = ((MyApplication) getApplication()).getLayoutParams();

        wmlp.type = 2002;
        wmlp.format = 1;
        wmlp.flags |= 8;
        wmlp.gravity = Gravity.LEFT | Gravity.TOP; // let the float view to the
                                                   // right top

        wmlp.x = 0;
        wmlp.y = 0;

        wmlp.width = 50;
        wmlp.height = 15;

        wm.addView(fl, wmlp);// add the view

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        wm.removeView(fl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
