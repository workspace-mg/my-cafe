package service.pattern.strategy.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import model.Product;
import org.junit.jupiter.api.Test;

class FreeCoffeeDiscountStrategyTest {

    private final FreeCoffeeDiscountStrategy discountStrategy = new FreeCoffeeDiscountStrategy();

    @Test
    void whenListHasOnlySnackAndDrinkThenNoDiscountOnItemOrAddonIsOffered() {
        List<Product> products = Arrays.asList(
                new Product("Bacon Roll", 4.50, 0, "Snack"),
                new Product("Orange Juice", 3.95, 0, "Drink")
        );
        List<Product> discountedProducts = discountStrategy.applyDiscount(products);
        assertEquals(products, discountedProducts);
        assertTrue(discountedProducts.stream().noneMatch(product -> product.getAddOnPriceAfterDiscount() > 0));
        assertTrue(discountedProducts.stream().noneMatch(product -> product.getItemPriceAfterDiscount() > 0));
    }

    @Test
    void whenListHasOnlyFourBeveragesThenNoDiscountOnItemIsOffered() {
        List<Product> products = Arrays.asList(
                new Product("Small Coffee", 2.50, 0, "Beverage"),
                new Product("Medium Coffee", 3.00, 0, "Beverage"),
                new Product("Large Coffee", 3.50, 0, "Beverage"),
                new Product("Small Coffee With Extra Milk", 2.50, 0.30, "Beverage")
        );
        List<Product> discountedProducts = discountStrategy.applyDiscount(products);
        assertEquals(products, discountedProducts);
        assertTrue(discountedProducts.stream().noneMatch(product -> product.getItemPriceAfterDiscount() > 0));
    }

    @Test
    void whenListHasMoreThanFourBeveragesThenDiscountIsOfferedForTwoBeveragesOnly() {
        List<Product> products = Arrays.asList(
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
                new Product("Bacon Roll", 4.50, 0, "Snack")
        );
        List<Product> discountedProducts = discountStrategy.applyDiscount(products);
        assertEquals(13, discountedProducts.size());
        assertEquals(2, discountedProducts.stream().filter(product -> product.getItemPriceAfterDiscount() > 0).count());
        assertTrue(discountedProducts.stream().noneMatch(product -> product.getAddOnPriceAfterDiscount() > 0));
    }

}
