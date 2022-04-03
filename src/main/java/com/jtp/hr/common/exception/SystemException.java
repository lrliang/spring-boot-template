package com.jtp.hr.common.exception;


import static com.jtp.hr.common.exception.DefaultErrorCode.SYSTEM_ERROR;
import static com.google.common.collect.ImmutableMap.of;

public class SystemException extends AppException {

    public SystemException(Throwable cause) {
        super(SYSTEM_ERROR, of("detail", cause.getMessage()), cause);
    }
}
