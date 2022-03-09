package com.optima.plugin.repluginlib.base;

import org.xutils.common.Callback;

/**
 * create by wma
 * on 2020/8/27 0027
 */
public class BaseModule {
    public String code;

    public String msg;

    public Object data;


    public Callback.Cancelable cancelable;

    public void cancelRequest(){
        if (cancelable!=null) {
            cancelable.cancel();
            cancelable = null;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
