package com.project.exception;

import lombok.Getter;

@Getter
public class DuplicateUser extends RuntimeException{

    private static final String MESSAGE = "존재하는 회원입니다.";

    public DuplicateUser( ) {
        super(MESSAGE);
    }

    public DuplicateUser(Throwable cause) {
        super(MESSAGE, cause);
    }

    public int getStatusCode() {
        return 400;
    }
}
