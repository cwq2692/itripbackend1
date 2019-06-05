package com.cwq.status;

/**
 * Created by cmy on 2019/5/21.
 */
public class DataType {
    private int status; // 0代表响应成功 1代表响应失败
    private Object data;//响应时y要传递的数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataType(int status, Object data) {
        this.status = status;
        this.data = data;
    }
}
