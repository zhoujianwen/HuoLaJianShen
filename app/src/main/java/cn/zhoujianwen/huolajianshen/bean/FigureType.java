package cn.zhoujianwen.huolajianshen.bean;

/**
 * Created by zhoujianwen.cn on 2018-05-28.
 */
public class FigureType {

    private String figureName;
    private int figureType;

    public FigureType(int figureType, String figureName) {
        this.figureType = figureType;
        this.figureName = figureName;
    }

    public int getFigureType() {
        return figureType;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return figureName;
    }
}
