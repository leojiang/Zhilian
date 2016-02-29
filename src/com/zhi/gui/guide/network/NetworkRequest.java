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
import org.apache.http.util.EntityUtils;
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
                Log.e("error", "Httpresponse error");
            }
        } catch (UnsupportedEncodingException e) {
            Log.e("error", e.toString());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.e("error", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("error", e.toString());
            e.printStackTrace();
        }
        return "";
    }


//    public void test() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                JSONObject object = new JSONObject();
//                try {
//                    Log.d(TAG, "DEBUG " + mRecvIdentifier);
//                    object.put(Util.EXTRA_ROOM_NUM, roomNum);
//                    System.out.println(object.toString());
//                    List<NameValuePair> list = new ArrayList<NameValuePair>();
//                    list.add(new BasicNameValuePair("closedata", object.toString()));
//                    String ret = HttpUtil.PostUrl(HttpUtil.liveCloseUrl, list);
//                    Log.d(TAG, "close room" + ret);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }

    public static JSONObject post(String url, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == 202) {
                String string = EntityUtils.toString(res.getEntity(), "utf-8");
                Log.i(TAG, "response date :" + string);
//                response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), charset)));
                response = new JSONObject(string);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    public static JSONObject get(String url, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        JSONObject response = null;
        try {
//            StringEntity s = new StringEntity(json.toString());
//            s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");
//            post.setEntity(s);

            HttpResponse res = client.execute(get);
            if (res.getStatusLine().getStatusCode() == 202) {
                HttpEntity entity = res.getEntity();
                String charset = EntityUtils.getContentCharSet(entity);
                Log.i("leojiang", "response date, get method1:" + charset);
                String string = EntityUtils.toString(entity, "utf-8");
                Log.i("leojiang", "response date get method2:" + string);
//                response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), charset)));
                response = new JSONObject(string);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}