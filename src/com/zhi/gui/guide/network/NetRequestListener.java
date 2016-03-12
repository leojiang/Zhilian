package com.zhi.gui.guide.network;

import org.json.JSONObject;

public interface NetRequestListener {
    public void onSucceed(JSONObject json);
    public void onFail(String reason);
    public void onTimeout();
    public void onError(String reason);
}
