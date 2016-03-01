package com.zhi.gui.guide.network;

public interface NetRequestListener {
    public void onSucceed();
    public void onFail();
    public void onTimeout();
    public void onError();
}
