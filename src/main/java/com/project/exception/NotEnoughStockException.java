package com.project.exception;

public class NotEnoughStockException extends RuntimeException{

    private static final String MESSAGE = "수량이 충분하지 않습니다.";


    public NotEnoughStockException() {
        super(MESSAGE);
    }

    public NotEnoughStockException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
