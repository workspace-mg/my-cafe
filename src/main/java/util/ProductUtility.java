package util;

import model.Product;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductUtility {
    public List<Product> constructProductUsingOrderedNames(List<String> productNames, List<Product> availableProducts) {
        Objects.requireNonNull(productNames, "Product names cannot be null");
        return availableProducts.stream()
                .filter(product -> productNames.stream().anyMatch(productName -> productName.equalsIgnoreCase(product.getName())))
                .collect(Collectors.toList());
    }
}
