package com.six.carrental.Entity;

/**
 * @author Lian
 * @create 2018/7/27
 * @Describe 接口返回内容
 */
public class ResultEntity {

    /**
     * message : 登录成功
     * status : 200
     */

    private String message;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
