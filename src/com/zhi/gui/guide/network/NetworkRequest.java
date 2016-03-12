package com.zhi.gui.guide.network;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class NetworkRequest {
    public static String TAG = "NetworkRequest";

    public static String postRequest(String Url, List<NameValuePair> pairlist) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Url);

        List<NameValuePair> params = pairlist;
        UrlEncodedFormEntity entity;
        int ret = 0;
        try {
            entity = new UrlEncodedFormEntity(params, "utf-8");
            httpPost.setEntity(entity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            ret = httpResponse.getStatusLine().getStatusCode();

            if (ret > 0) {
                HttpEntity getEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(getEntity, "utf-8");

                return response;
            } else {
                Log.e(TAG, "Httpresponse error");
            }
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        return "";
    }

    public static void post(String url, JSONObject json, NetRequestListener listener) {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);

        DefaultHttpClient client = new DefaultHttpClient();
        client.setParams(httpParams);

        HttpPost post = new HttpPost(url);

        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            int statusCode = res.getStatusLine().getStatusCode();
            Log.i(TAG, "response statusCode :" + statusCode);
            switch (statusCode) {
                case 200:
                    String string = EntityUtils.toString(res.getEntity(), "utf-8");
                    Log.i(TAG, "response data :" + string);
                    JSONObject response = new JSONObject(string);
                    int returnCode = response.getInt("retCode");
                    if (returnCode == 0) {
                        JSONObject object = new JSONObject(response.getString("data"));
                        if (listener != null) {
                            listener.onSucceed(object);
                        }
                    } else {
                        if (listener != null) {
                            listener.onFail(response.getString("reason"));
                        }
                    }
                    break;
                case 202:
                    String string1 = EntityUtils.toString(res.getEntity(), "utf-8");
                    Log.i(TAG, "response date :" + string1);
                    break;
                case 403:// 服务器拒绝请求
                    if (listener != null) {
                        listener.onError("Error Code: 403, 服务器拒绝请求");
                    }
                    break;
                case 404:// 找不到请求的页面
                    if (listener != null) {
                        listener.onError("Error Code: 404, 所请求的地址不存在");
                    }
                    break;
                case 408: // 请求超时
                    if (listener != null) {
                        listener.onTimeout();
                    }
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError("IOException");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError("JSONException");
            }
        }
    }
}