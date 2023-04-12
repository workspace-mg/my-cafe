package controller;

import model.Product;
import service.CafeService;
import service.DiscountService;
import util.ProductUtility;
import validator.OrderValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderManager {
    private final List<Product> AVAILABLE_PRODUCTS;
    private final OrderValidator validator;
    private final ProductUtility productUtility;
    private final DiscountService discountService;
    private final CafeService cafeService;

    public OrderManager(OrderValidator validator, ProductUtility productUtility, DiscountService discountService, CafeService cafeService) {
        this.validator = validator;
        this.productUtility = productUtility;
        this.discountService = discountService;
        this.cafeService = cafeService;
        AVAILABLE_PRODUCTS = Stream.of(
                        new Product("Small Coffee", 2.50, 0, "Beverage"),
                        new Product("Medium Coffee", 3.00, 0, "Beverage"),
                        new Product("Large Coffee", 3.50, 0, "Beverage"),
                        new Product("Small Coffee With Extra Milk", 2.50, 0.30, "Beverage"),
                        new Product("Small Coffee With Foamed Milk", 2.50, 0.50, "Beverage"),
                        new Product("Small Coffee With Special Roast", 2.50, 0.90, "Beverage"),
                        new Product("Medium Coffee With Extra Milk", 3.00, 0.30, "Beverage"),
                        new Product("Medium Coffee With Foamed Milk", 3.00, 0.50, "Beverage"),
                        new Product("Medium Coffee With Special Roast", 3.00, 0.90, "Beverage"),
                        new Product("Large Coffee With Extra Milk", 3.50, 0.30, "Beverage"),
                        new Product("Large Coffee With Foamed Milk", 3.50, 0.50, "Beverage"),
                        new Product("Large Coffee With Special Roast", 3.50, 0.90, "Beverage"),
                        new Product("Bacon Roll", 4.50, 0, "Snack"),
                        new Product("Orange Juice", 3.95, 0, "Drink"))
                .collect(Collectors.toList());
    }

    public void placeOrder(List<String> orderedItems) {
        Objects.requireNonNull(orderedItems, "Ordered items cannot be null");
        validator.validateOrderedItems(orderedItems, AVAILABLE_PRODUCTS);
        List<Product> productList = productUtility.constructProductUsingOrderedNames(orderedItems, AVAILABLE_PRODUCTS);
        productList = discountService.applyDiscounts(productList);
        cafeService.generateReceipt(productList);
    }

}
