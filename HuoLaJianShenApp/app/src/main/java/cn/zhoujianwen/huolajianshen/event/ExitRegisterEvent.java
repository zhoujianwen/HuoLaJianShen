package cn.zhoujianwen.huolajianshen.event;

/**
 * Created by zhoujianwen on 2018/05/01.
 */
public class ExitRegisterEvent {
    private boolean isExit;

    public ExitRegisterEvent(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
