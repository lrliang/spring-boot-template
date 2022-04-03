package com.jtp.hr.common.distributedlock;

import com.jtp.hr.common.exception.AppException;
import com.jtp.hr.common.exception.DefaultErrorCode;

import static com.google.common.collect.ImmutableMap.of;

public class LockAlreadyOccupiedException extends AppException {
    public LockAlreadyOccupiedException(String lockKey) {
        super(DefaultErrorCode.LOCK_OCCUPIED, of("lockKey", lockKey));
    }
}
