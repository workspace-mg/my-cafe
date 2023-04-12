package service;

import model.Product;

import java.util.List;
import java.util.Objects;

public class PriceService {
    public double calculateTotalPrice(List<Product> productList) {
        Objects.requireNonNull(productList, "Product list cannot be null");
        return productList.stream()
                .map(product -> product.getItemPrice() - product.getItemPriceAfterDiscount() + product.getAddOnPrice() - product.getAddOnPriceAfterDiscount())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
