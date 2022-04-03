package com.jtp.hr.order.exception;

import com.jtp.hr.common.exception.AppException;
import com.jtp.hr.order.model.OrderId;
import com.google.common.collect.ImmutableMap;
import com.jtp.hr.common.exception.DefaultErrorCode;


public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(OrderId orderId) {
        super(DefaultErrorCode.ORDER_NOT_FOUND, ImmutableMap.of("orderId", orderId.toString()));
    }
}
