package com.jtp.hr.order.representation;

import com.jtp.hr.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepresentationService {

    public OrderRepresentation toRepresentation(Order order) {
        return new OrderRepresentation(order);
    }
}
