package validator;

import model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class OrderValidator {

    public void validateOrderedItems(List<String> orderedItems, List<Product> cafeMenu) {
        if (orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("Ordered items cannot be empty");
        }
        if (!new HashSet<>(cafeMenu.stream().map(Product::getName).collect(Collectors.toList())).containsAll(orderedItems)) {
            throw new IllegalArgumentException("Ordered items should be from the menu");
        }
    }
}
