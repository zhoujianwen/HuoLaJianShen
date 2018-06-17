package cn.zhoujianwen.huolajianshen.bean;

import cn.zhoujianwen.huolajianshen.base.BaseBean;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class ShowType extends BaseBean {
    public String getShowTypeStr() {
        return showTypeStr;
    }

    public void setShowTypeStr(String showTypeStr) {
        this.showTypeStr = showTypeStr;
    }

    private String showTypeStr;

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    private int showType;


}
