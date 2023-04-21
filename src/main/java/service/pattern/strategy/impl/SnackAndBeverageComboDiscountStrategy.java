package service.pattern.strategy.impl;

import model.Product;
import service.pattern.strategy.DiscountStrategy;
import util.AddOnComparator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SnackAndBeverageComboDiscountStrategy implements DiscountStrategy {
    private final List<String> ITEM_TYPE_LIST_VALID_FOR_DISCOUNT = Arrays.asList("Beverage", "Snack");

    public List<Product> applyDiscount(List<Product> products) {
        boolean isQualifiedForDiscount = products.stream()
                .map(Product::getType)
                .collect(Collectors.toSet())
                .containsAll(ITEM_TYPE_LIST_VALID_FOR_DISCOUNT)
                && products.stream()
                .noneMatch(product -> product.getItemPriceAfterDiscount() > 0);

        if (isQualifiedForDiscount) {
            products.stream()
                    .filter(product -> product.getType().equalsIgnoreCase("Beverage"))
                    .min(new AddOnComparator())
                    .ifPresent(product -> product.setAddOnPriceAfterDiscount(product.getAddOnPrice()));
        }

        return products;
    }
}