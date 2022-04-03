package com.jtp.hr.order;

import com.jtp.hr.BaseComponentTest;
import com.jtp.hr.order.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.jtp.hr.common.utils.UuidGenerator.newUuid;
import static com.jtp.hr.order.model.OrderId.orderId;
import static com.jtp.hr.order.model.OrderItem.create;
import static com.jtp.hr.order.model.ProductId.newProductId;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderRepositoryComponentTest extends BaseComponentTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void should_save_order() {
        Order order = Order.create(orderId(newUuid()), newArrayList(create(newProductId(), 2, valueOf(20))), "theCreator");
        orderRepository.save(order);
        Order saved = orderRepository.byId(order.getId());
        assertEquals(order.getCreatedAt(), saved.getCreatedAt());
        assertEquals(BigDecimal.valueOf(40), order.getTotalPrice());
        assertEquals(1, order.getItems().size());
    }

}