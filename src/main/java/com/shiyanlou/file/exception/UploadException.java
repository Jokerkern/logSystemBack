package com.shiyanlou.file.exception;

public abstract class UploadException extends RuntimeException{

    protected UploadException(String message) {
        super(message);
    }

    protected UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}