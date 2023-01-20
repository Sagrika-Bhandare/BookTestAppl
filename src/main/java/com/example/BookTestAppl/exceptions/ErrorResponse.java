package com.example.BookTestAppl.exceptions;

public class ErrorResponse {
    private int code;
    private String errormsg;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String errormsg) {
        this.code = code;
        this.errormsg = errormsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code=" + code +
                ", errormsg='" + errormsg + '\'' +
                '}';
    }
}
