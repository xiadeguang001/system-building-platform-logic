package com.haso.common.exception;

public class HandlerExcelcntException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HandlerExcelcntException(String message){
        super(message);
    }

    public HandlerExcelcntException(Throwable cause)
    {
        super(cause);
    }

    public HandlerExcelcntException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
