package com.jtp.hr.order.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.jtp.hr.order.model.OrderStatus.CREATED;
import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigDecimal.ZERO;
import static java.time.Instant.now;

public class Order {
    private OrderId id;
    private List<OrderItem> items = newArrayList();
    private String creator;
    private BigDecimal totalPrice;
    private OrderStatus status;

    private Order() {
    }

    private Order(OrderId id, List<OrderItem> items, String creator) {
        this.id = id;
        this.items.addAll(items);
        this.creator = creator;
        this.totalPrice = calculateTotalPrice();
        this.status = CREATED;
    }

    public static Order create(OrderId id, List<OrderItem> items, String creator) {
        return new Order(id, items, creator);
    }

    private BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(OrderItem::totalPrice)
                .reduce(ZERO, BigDecimal::add);

    }

    public OrderId getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

}
