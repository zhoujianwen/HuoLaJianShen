package cn.zhoujianwen.huolajianshen.http;

import com.android.volley.Response;
import cn.zhoujianwen.huolajianshen.base.BaseApplication;
import cn.zhoujianwen.huolajianshen.utils.JsonUtil;
import cn.zhoujianwen.huolajianshen.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.zhoujianwen.huolajianshen.utils.JsonUtil;
import cn.zhoujianwen.huolajianshen.utils.LogUtils;

/**
 * Created by jimmy on 15/12/15.
 */
public abstract class HttpRequestListener implements Response.Listener<String> {
    @Override
    public final void onResponse(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonObject == null) {
            return;
        }
        // 解析数据
        //TODO
        int code = JsonUtil.getIntJsonValueByKey(jsonObject.toString(), "code");
        String message = JsonUtil.getJsonValueByKey(jsonObject.toString(), "message");
        String time = JsonUtil.getJsonValueByKey(jsonObject.toString(), "time");
        String responseData = JsonUtil.getJsonValueByKey(jsonObject.toString(), "data");
        LogUtils.e("状态:" + code);
        // 状态码为 200 返回成功
        if (HttpRequest.HTTP_OK == code) {
            onSuccess(responseData);

        }
        // 否则失败，显示错误提示
        else {
            onError(code, message);
        }
    }

    public abstract void onSuccess(String json);

    public void onError(int code, String message) {
        ToastUtil.showToast(BaseApplication.getApplication().getApplicationContext(), message);
    }
}
