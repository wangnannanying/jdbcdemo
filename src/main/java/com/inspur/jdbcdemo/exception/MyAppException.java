package com.inspur.jdbcdemo.exception;

/**
 * 业务异常
 */
public class MyAppException extends RuntimeException{
    public MyAppException() {
    }

    public MyAppException(String message) {
        super(message);
    }
}
