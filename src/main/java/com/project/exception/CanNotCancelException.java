package com.project.exception;

public class CanNotCancelException extends RuntimeException{

    private static final String MESSAGE = "배송 완료된 상품은 취소가 불가능합니다.";

    public CanNotCancelException() {
        super(MESSAGE);
    }

    public CanNotCancelException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
