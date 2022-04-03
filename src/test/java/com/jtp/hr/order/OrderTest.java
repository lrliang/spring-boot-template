package com.jtp.hr.order;

import com.jtp.hr.order.model.Order;
import com.jtp.hr.order.model.OrderItem;
import org.junit.jupiter.api.Test;

import static com.jtp.hr.common.utils.UuidGenerator.newUuid;
import static com.jtp.hr.order.model.OrderId.orderId;
import static com.jtp.hr.order.model.OrderItem.create;
import static com.jtp.hr.order.model.OrderStatus.CREATED;
import static com.jtp.hr.order.model.ProductId.newProductId;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    public void should_create_order() {
        OrderItem orderItem1 = create(newProductId(), 2, valueOf(20));
        OrderItem orderItem2 = create(newProductId(), 3, valueOf(30));
        Order order = Order.create(orderId(newUuid()), newArrayList(orderItem1, orderItem2), "theCreator");
        assertThat(valueOf(130), comparesEqualTo(order.getTotalPrice()));
        assertEquals(CREATED, order.getStatus());
    }

}