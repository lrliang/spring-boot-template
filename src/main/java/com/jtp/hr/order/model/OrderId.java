package com.jtp.hr.order.model;

import com.jtp.hr.common.model.AbstractId;

public class OrderId extends AbstractId {

    private OrderId() {
    }

    private OrderId(String id) {
        super(id);
    }

    public static OrderId orderId(String id) {
        return new OrderId(id);
    }

}
