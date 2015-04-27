package com.lyh.lifenoteapplication.server;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerHelper {
    private static final String TAG = "ServerHelper";
    private static final int RETRY_TIMES = 3;
    private static int mSequenceCount = 0;
    private static final ExecutorService mExecutor = Executors.newCachedThreadPool();// wSingleThreadExecutor();

    public static void excuteOperate(Runnable runnable) {
        mExecutor.execute(runnable);
    }

    public static ExecutorService getExecutor() {
        return mExecutor;
    }

    public static String getSequenceID() {
        Calendar c = Calendar.getInstance();
        mSequenceCount = (mSequenceCount + 1) % 1000000;
        return String.format("%04d%02d%02d%02d%02d%02d%06d", c.get(Calendar.YEAR),
                c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),
                mSequenceCount);
    }
}
