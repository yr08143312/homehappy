package com.yangrui.homehappy.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int STATUS_CODE_SUCCESS = 1;

    public static final int STATUS_CODE_BUSINESS_ERROR = 0;

    public static final int STATUS_CODE_SYSTEM_ERROR = 999;

    public static final int STATUS_CODE_NOT_AUTHORIZED = -1;
    public static final int STATUS_CODE_NO_PERMISSION = -99;

    public static final int STATUS_CODE_NOT_LOGIN = -999;

    public static final int STATUS_CODE_NO_AUTHPOINT = -888;

    private T data;

    private int code;

    private String message;

    private boolean success;

    public ResultDTO(){
        this.setCode(STATUS_CODE_SUCCESS);
    }

    public ResultDTO(T data) {
        this();
        this.data = data;
    }

    public ResultDTO(Integer code, String message, T data) {
        this.setCode(code);
        this.message = message;
        this.data = data;
    }

    public boolean getSuccess(){
        return this.success;
    }

    public void setCode(int code){
        this.code = code;
        this.success =  this.code == STATUS_CODE_SUCCESS;
    }

    public static ResultDTO execute(Object data) {

        return new ResultDTO(STATUS_CODE_SUCCESS, null, data);
    }

    public static ResultDTO execute(String message, Object data) {
        return new ResultDTO(STATUS_CODE_SUCCESS, message, data);
    }
}
