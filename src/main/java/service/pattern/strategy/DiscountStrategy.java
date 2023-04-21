package service.pattern.strategy;

import model.Product;

import java.util.List;

public interface DiscountStrategy {
    List<Product> applyDiscount(List<Product> products);
}
