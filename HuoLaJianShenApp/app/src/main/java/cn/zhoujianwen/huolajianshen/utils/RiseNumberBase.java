package cn.zhoujianwen.huolajianshen.utils;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public interface RiseNumberBase {
    public void start();
    public RiseNumberTextView withNumber(float number);
    public RiseNumberTextView withNumber(int number);
    public RiseNumberTextView setDuration(long duration);
    public void setOnEnd(RiseNumberTextView.EndListener callback);
}
