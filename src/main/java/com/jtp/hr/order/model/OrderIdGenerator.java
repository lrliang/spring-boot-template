package com.jtp.hr.order.model;

import com.jtp.hr.common.utils.UuidGenerator;
import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {

    public OrderId generate() {
        return OrderId.orderId(UuidGenerator.newUuid());
    }
}
