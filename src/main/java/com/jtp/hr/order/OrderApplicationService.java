package com.jtp.hr.order;

import com.jtp.hr.order.command.CreateOrderCommand;
import com.jtp.hr.order.model.Order;
import com.jtp.hr.order.model.OrderFactory;
import com.jtp.hr.order.model.OrderId;
import com.jtp.hr.order.model.OrderItem;
import com.jtp.hr.order.representation.OrderRepresentation;
import com.jtp.hr.order.representation.OrderRepresentationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.jtp.hr.order.model.OrderId.orderId;
import static com.jtp.hr.order.model.ProductId.productId;

@Component
public class OrderApplicationService {
    private final OrderRepresentationService orderRepresentationService;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderApplicationService(OrderRepresentationService orderRepresentationService,
                                   OrderRepository orderRepository,
                                   OrderFactory orderFactory) {
        this.orderRepresentationService = orderRepresentationService;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    @Transactional
    public OrderId createOrder(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems().stream()
                .map(item -> OrderItem.create(productId(item.getProductId()),
                        item.getCount(),
                        item.getItemPrice()))
                .collect(Collectors.toList());

        Order order = orderFactory.create(items);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderRepresentation byId(String id) {
        Order order = orderRepository.byId(orderId(id));
        return orderRepresentationService.toRepresentation(order);
    }

}
