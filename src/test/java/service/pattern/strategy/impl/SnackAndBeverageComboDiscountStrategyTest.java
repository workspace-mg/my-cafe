package service.pattern.strategy.impl;

import static org.junit.jupiter.api.Assertions.*;

import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.pattern.strategy.DiscountStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SnackAndBeverageComboDiscountStrategyTest {
    private DiscountStrategy strategy;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        strategy = new SnackAndBeverageComboDiscountStrategy();
        productList = new ArrayList<>();
    }

    @Test
    void testApplyDiscountWithNoDiscount() {
        Product drink1 = new Product("Orange Juice", 3.95, 0, "Drink");
        Product snack1 = new Product("Bacon Roll", 4.50, 0, "Snack");
        productList.addAll(Arrays.asList(drink1, snack1));
        List<Product> result = strategy.applyDiscount(productList);
        Assertions.assertEquals(productList, result);
    }

    @Test
    void testApplyDiscountWithDiscount() {
        Product beverage1 =  new Product("Small Coffee With Extra Milk", 2.50, 0.30, "Beverage");
        Product snack1 =   new Product("Bacon Roll", 4.50, 0, "Snack");
        productList.addAll(Arrays.asList(beverage1, snack1));
        productList = strategy.applyDiscount(productList);
        assertTrue(productList.stream().anyMatch(product -> product.getAddOnPriceAfterDiscount() > 0));
    }
}
