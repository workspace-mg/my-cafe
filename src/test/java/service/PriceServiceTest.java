package service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceServiceTest {
    final PriceService priceService = new PriceService();

    @Test
    public void whenInputListIsEmptyThenTotalPriceIsZero() {
        assertEquals(0.0, priceService.calculateTotalPrice(List.of()));
    }

    @Test
    public void whenInputListHasNoProductsQualifiedForDiscountThenTotalPriceIsFullPrice() {
        assertEquals(2.50, priceService.calculateTotalPrice(getProductsWithOnlyOneBeverage()));
    }

    @Test
    public void whenInputListHasProductsQualifiedForSnackAndBeverageComboThenOneExpensiveAddOnIsDiscountedFromTotalPrice() {
        assertEquals(10.30, priceService.calculateTotalPrice(getDiscountAppliedProductsWithBeverageAndSnack()));
    }

    @Test
    public void whenInputListHasFiveBeverageProductsThenOneExpensiveBeverageIsDiscountedFromTotalPrice() {
        assertEquals(11.30, priceService.calculateTotalPrice(getDiscountAppliedProductsWithFiveBeverages()));
    }

}