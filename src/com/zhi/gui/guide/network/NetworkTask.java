package com.zhi.gui.guide.network;

import org.json.JSONObject;

public class NetworkTask {


    private String url;
    private JSONObject json;
    private FastJsonCommonHandler mJsonHandler;
    private NetRequestListener mRequestListener;

    public NetworkTask(String url, JSONObject json, FastJsonCommonHandler handler) {
        this.url = url;
        this.json = json;
        mJsonHandler = handler;
    }

    public void setNetRequestListener(NetRequestListener requestListener) {
        mRequestListener = requestListener;
    }


    public void execute() {
        JSONObject result = NetworkRequest.post(url, json, mRequestListener);
        if (mJsonHandler != null) {
            mJsonHandler.parse(result);
        }
    }
}
