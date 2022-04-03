package com.jtp.hr.order.model;

import com.jtp.hr.common.model.AbstractId;
import com.jtp.hr.common.utils.UuidGenerator;

public class ProductId extends AbstractId {
    private ProductId() {
    }

    private ProductId(String id) {
        super(id);
    }

    public static ProductId productId(String id) {
        return new ProductId(id);
    }

    public static ProductId newProductId() {
        return new ProductId(UuidGenerator.newUuid());
    }
}
