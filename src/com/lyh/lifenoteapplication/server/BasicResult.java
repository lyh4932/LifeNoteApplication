package com.lyh.lifenoteapplication.server;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class BasicResult {

    public static final boolean DEBUG = true;

    /** 错误码 */
    public int retCode = Err.OTHER_ERR;//        if (info != null) {

    public static final String KEY_RET_CODE = "retCode";

    /** 错误详情 */
    public String retInfo;
    public static final String KEY_RET_INFO = "retInfo";

    public BasicResult(JSONObject object) {
        if (object != null) {
            try {
                String code = object.getString(KEY_RET_CODE);
                try {
                    retCode = Integer.valueOf(code);
                } catch (NumberFormatException e) {
                    if (TextUtils.equals(code, Err.OK_S)) {
                        retCode = Err.OK;
                    } else if (TextUtils.equals(code, Err.SESSION_VOID_S)) {
                        retCode = Err.SESSION_VOID;
                    }
                }

                retInfo = object.getString(KEY_RET_INFO);
            } catch (JSONException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BasicResult() {
    }
}
