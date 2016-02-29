package com.zhi.gui.guide.network;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zhi.gui.guide.data.JSONEntityBase;

import org.json.JSONObject;

/**
 * Created by admin on 2016/2/29.
 */
public class FastJsonCommonHandler<T extends JSONEntityBase> {
    final String TAG = FastJsonCommonHandler.class.getSimpleName();

    Class<T> mClass;
    T mData;

    public FastJsonCommonHandler(Class<T> tClass) {
        super();
        mClass = tClass;
    }

    public T getData() {
        return mData;
    }

    public void parse(JSONObject object) {
        if(null == object){
            return;
        }
//        super.parse(object);

//        this.code = object.optInt("code");
//        this.message = object.optString("message");
//        this.action = RequextMessageActions.fromString(object.optString("action"));
        String content = object.optString("data");
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Log.d(TAG, "content:" + content);

        mData = JSON.parseObject(content, mClass);
        if (mData != null) {
            Log.d(TAG, "JSON:" + JSON.toJSONString(mData));
            mData.onParsed();
        }
    }
}
