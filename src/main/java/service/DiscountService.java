package service;

import model.Product;
import service.pattern.strategy.DiscountStrategy;

import java.util.List;
import java.util.Objects;

public class DiscountService {
    private final DiscountStrategy discountStrategy;

    public DiscountService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public List<Product> applyDiscounts(List<Product> products) {
        Objects.requireNonNull(products, "Product list cannot be null");
        return discountStrategy.applyDiscount(products);
    }
}
