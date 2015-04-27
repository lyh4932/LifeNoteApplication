package com.lyh.lifenoteapplication.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

public abstract class BasicServerOperate {
    public static final String TAG = "BasicServerOperate";
    /** 接入点 */
    public String id;

    private static final String charset = "UTF-8";

    public static final String KEY_TOKEN = "accessToken";

    public static final String KEY_SEQUENCE_ID = "sequenceId";
    protected static final int CONNECTION_TIMEOUT = 30 * 1000;

    protected static final int SOCKET_TIMEOUT = 30 * 1000;

    /** 接口的Http方法 */
    public String mMethod = HttpPost.METHOD_NAME;

    /** 获取接口的request body */
    public abstract String getParam();

    /** 解析接口的response body */
    public abstract BasicResult praseResult(String response);

    /** 获取接口的uri */
    public String getURL() {
        return ServerConfig.SERVER_ADDRESS + id;
    }

    public static final boolean DEBUG = true;

    public void addSequenceID(JSONObject object) throws JSONException {
        object.put(KEY_SEQUENCE_ID, ServerHelper.getSequenceID());
    }

    public void addHeader(HttpUriRequest operate) {
        operate.addHeader("Content-Type", "application/json");
        operate.addHeader("appId", ServerConfig.APP_ID);
        operate.addHeader("appKey", ServerConfig.APP_KEY);
        operate.addHeader("appVersion", ServerConfig.APP_VERSION);
        operate.addHeader("Charset", charset);

        String clientId = "";
        operate.addHeader("clientId", clientId);
        // LoginInfo info = LoginInfo.getLoginInfo();
        String token = null;
        // if (info != null) {
        // token = info.accessToken;
        // }

        if (TextUtils.isEmpty(token)) {
            operate.addHeader(KEY_TOKEN, "");
        } else {
            operate.addHeader(KEY_TOKEN, token);
        }
    }

    public BasicResult operateSync() {
        HttpUriRequest request = null;
        if (mMethod.equals(HttpPost.METHOD_NAME)) {
            request = setPostRequest();
        } else if (mMethod.equals(HttpGet.METHOD_NAME)) {
            request = new HttpGet(getURL());
        } else if (mMethod.equals(HttpDelete.METHOD_NAME)) {
            request = setDelRequest();
        } else {
            if (DEBUG) {
                Log.e(TAG, "不会用到的方法 " + mMethod);
            }
            return null;
        }

        addHeader(request);

        // AndroidHttpClient client = AndroidHttpClient.newInstance(null);
        HttpClient client = new DefaultHttpClient();
        HttpParams params = client.getParams();
        params.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
        params.setIntParameter(HttpConnectionParams.SO_TIMEOUT, SOCKET_TIMEOUT);
        InputStream is = null;
        BufferedReader br = null;
        try {
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                is = response.getEntity().getContent();
                br = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                if (DEBUG) {
                    Log.i(TAG, sb.toString());
                }
                // ServerHelper.checkSessionExpires(ReturnDataConvertHelper.getReturnInfo(sb
                // .toString()));
                BasicResult result = praseResult(sb.toString());
                return result;
            } else {
                Log.i(TAG, "Http req error : " + code);
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception happened : " + getExceptionMessage(e));
            if (DEBUG) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                // client.close();
            }
        }
        return null;
    }

    public void operate(final OnOperateExcuteEndListener listener, final int retryCount) {
        ServerHelper.excuteOperate(new Runnable() {

            @Override
            public void run() {
                int retryTimes = 1;
                while (retryTimes <= retryCount) {
                    HttpUriRequest request = null;
                    if (mMethod.equals(HttpPost.METHOD_NAME)) {
                        request = setPostRequest();
                    } else if (mMethod.equals(HttpGet.METHOD_NAME)) {
                        request = new HttpGet(getURL());
                    } else if (mMethod.equals(HttpDelete.METHOD_NAME)) {
                        request = setDelRequest();
                    } else {
                        if (DEBUG) {
                            Log.e(TAG, "不会用到的方法 " + mMethod);
                        }
                        return;
                    }

                    addHeader(request);

                    HttpClient client = new DefaultHttpClient();
                    HttpParams params = client.getParams();
                    params.setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
                            CONNECTION_TIMEOUT);
                    params.setIntParameter(HttpConnectionParams.SO_TIMEOUT, SOCKET_TIMEOUT);

                    InputStream is = null;
                    BufferedReader br = null;

                    try {
                        while (retryTimes <= retryCount) {
                            HttpResponse response = client.execute(request);
                            int code = response.getStatusLine().getStatusCode();
                            if (code == HttpStatus.SC_OK) {
                                is = response.getEntity().getContent();
                                br = new BufferedReader(new InputStreamReader(is, Charset
                                        .forName(charset)));
                                StringBuilder sb = new StringBuilder();
                                String line = null;
                                while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                }

                                if (DEBUG) {
                                    Log.i(TAG, "Response Body: " + sb.toString());
                                }
                                // ServerHelper.checkSessionExpires(ReturnDataConvertHelper
                                // .getReturnInfo(sb.toString()));
                                BasicResult result = praseResult(sb.toString());
                                if (retryTimes == retryCount
                                        || (result != null && result.retCode != Err.SESSION_VOID && result.retCode != Err.SESSION_VALIDATE_ERROR)) {
                                    retryTimes = retryCount;
                                    if (listener != null) {
                                        listener.onExcuteFinished(result);
                                    }
                                }
                            } else {
                                Log.i(TAG, "Http error code : " + code);
                                if (retryTimes == retryCount) {
                                    if (listener != null) {
                                        listener.onHttpErr(code);
                                    }
                                }
                            }
                            retryTimes++;
                        }
                    } catch (ConnectTimeoutException e) {
                        Log.i(TAG, "ConnectTimeoutException happened : " + getExceptionMessage(e));
                        if (listener != null) {
                            listener.onHttpErr(Err.HTTP_CONNECTION_TIMEOUT);
                            retryTimes = retryCount;
                        }
                    } catch (SocketTimeoutException e) {
                        Log.i(TAG, "SocketTimeoutException happened : " + getExceptionMessage(e));
                        if (listener != null) {
                            listener.onHttpErr(Err.HTTP_SOCKET_TIMEOUT);
                            retryTimes = retryCount;
                        }
                    } catch (ClientProtocolException e) {
                        Log.i(TAG, "ClientProtocolException happened : " + getExceptionMessage(e));
                        if (retryTimes == retryCount && listener != null) {
                            listener.onHttpErr(Err.HTTP_PROTOCOL_ERR);
                        }
                        if (DEBUG) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        Log.i(TAG, "IOException happened : " + getExceptionMessage(e));
                        if (retryTimes == retryCount && listener != null) {
                            listener.onHttpErr(Err.HTTP_OTHER_ERR);
                        }
                        if (DEBUG) {
                            e.printStackTrace();
                        }
                    } finally {
                        try {
                            if (br != null) {
                                br.close();
                            }
                            if (is != null) {
                                is.close();
                            }
                        } catch (IOException e) {
                            if (DEBUG) {
                                e.printStackTrace();
                            }
                        }
                        if (client != null) {
                            // client.close();
                        }
                    }

                    retryTimes++;
                }

            }
        });
    }

    /**
     * convert from exception to desc string
     * 
     * @param e
     * @return
     */
    private String getExceptionMessage(Exception e) {
        String error = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        e.printStackTrace(ps);
        try {
            error = baos.toString("UTF-8");
            ps.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return error;
    }

    private HttpUriRequest setPostRequest() {
        HttpUriRequest request;
        request = new HttpPost(getURL());
        String param = getParam();
        if (DEBUG) {
            Log.i(TAG, "URI: " + getURL());
            Log.i(TAG, "Request Body: " + param);
        }
        if (param != null) {
            StringEntity entity = null;
            try {
                entity = new StringEntity(param, charset);
            } catch (UnsupportedEncodingException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        return request;
    }

    private HttpUriRequest setDelRequest() {
        HttpUriRequest request;
        HttpEntityEnclosingRequestBase delete = new HttpEntityEnclosingRequestBase() {

            @Override
            public String getMethod() {
                return HttpDelete.METHOD_NAME;
            }
        };
        URI uri = URI.create(getURL());
        delete.setURI(uri);
        String param = getParam();
        if (DEBUG) {
            Log.i(TAG, "URI: " + uri);
            Log.i(TAG, "Request Body: " + param);
        }
        if (param != null) {
            StringEntity entity = null;
            try {
                entity = new StringEntity(param, charset);
            } catch (UnsupportedEncodingException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
            delete.setEntity(entity);
        }
        request = delete;
        return request;
    }

    public interface OnOperateExcuteEndListener {
        /**
         * Http通信正常，得到Result
         */
        public void onExcuteFinished(BasicResult result);

        /**
         * Http通信错误
         */
        public void onHttpErr(int errCode);
    }
}
