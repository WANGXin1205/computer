package com.growlithe.computer.common;

import java.io.Serializable;

/**
 * @Author : Growlithe
 * @Date : 2018/5/21 22:38
 * @Description 纪念我死去的卡蒂狗，因为Growlithe有拼写检查，我就用Candy了
 */
public class CandyResult<T> implements Serializable {

    private static final long serialVersionUID = -9130472993784728577L;

    /**
     * 成功标识
     */
    private boolean success = false;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回结果code
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    public CandyResult() {
    }

    public CandyResult(boolean success, T data, String code, String message) {
        this.success = success;
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
