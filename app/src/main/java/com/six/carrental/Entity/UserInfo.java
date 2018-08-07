package com.six.carrental.Entity;

/**
 * @author Lian
 * @create 2018/7/27
 * @Describe
 */
public class UserInfo {

    /**
     * user_id : 6
     * message : 注册成功
     * status : 200
     */

    private String user_id;
    private String message;
    private int status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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
