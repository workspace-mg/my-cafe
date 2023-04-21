package service.pattern.strategy.impl;

import model.Product;
import service.pattern.strategy.DiscountStrategy;
import util.ProductPriceComparator;

import java.util.List;
import java.util.stream.Collectors;

public class FreeCoffeeDiscountStrategy implements DiscountStrategy {
    public List<Product> applyDiscount(List<Product> products) {
        int numberOfBeverages = Math.toIntExact(products.stream().filter(product -> product.getType().equalsIgnoreCase("Beverage")).count());
        int freeCoffeeCount = numberOfBeverages / 5;

        List<Product> beverages = products.stream()
                .filter(product -> product.getType().equalsIgnoreCase("Beverage"))
                .sorted(new ProductPriceComparator())
                .collect(Collectors.toList());

        beverages.stream()
                .limit(freeCoffeeCount)
                .forEach(product -> product.setItemPriceAfterDiscount(product.getItemPrice()));

        return products;
    }
}
