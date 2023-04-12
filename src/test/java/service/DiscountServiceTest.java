package service;

import model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountServiceTest {
    private final DiscountService discountService = new DiscountService();

    @Test
    public void whenInputListIsEmptyThenReturnsFalse() {
        assertFalse(discountService.isQualifiedForSnackAndBeverageComboDiscount(List.of()));
    }

    @Test
    public void whenInputListHasNoSnackAndBeverageProductsThenReturnsFalse() {
        assertFalse(discountService.isQualifiedForSnackAndBeverageComboDiscount(getProductsWithOnlyOneBeverage()));
    }

    @Test
    public void whenInputListHasSnackAndBeverageProductsThenReturnsTrue() {
        assertTrue(discountService.isQualifiedForSnackAndBeverageComboDiscount(getDiscountAppliedProductsWithBeverageAndSnack()));
    }

    @Test
    public void whenInputListHasFiveBeveragesThenReturnsOneFreeBeverage() {
        assertEquals(1, discountService.getNumberOfFreeCoffee(getDiscountAppliedProductsWithFiveBeverages()));
    }

    @Test
    public void whenInputListHasLessThanFiveBeveragesThenReturnsZeroFreeBeverages() {
        assertEquals(0, discountService.getNumberOfFreeCoffee(getProductsWithOnlyOneBeverage()));
        assertEquals(0, discountService.getNumberOfFreeCoffee(List.of()));
    }

    @Test
    public void whenInputListHasMoreThanFiveBeveragesThenReturnsTwoFreeBeverages() {
        List<Product> productListWithTenBeverages = new ArrayList<>(getDiscountAppliedProductsWithFiveBeverages());
        productListWithTenBeverages.addAll(getDiscountAppliedProductsWithFiveBeverages());
        assertEquals(2, discountService.getNumberOfFreeCoffee(productListWithTenBeverages));
    }

    @Test
    public void whenInputListHasBeverageAndSnackAndBeverageCountIsMoreThanFiveThenDiscountIsNotAppliedToAnyAddonAndAppliedToOnlyOneExpensiveCoffee() {
        List<Product> discountedProductList = discountService.applyDiscounts(getProductsWithBeverageAndSnackAndBeverageCountFive());
        discountedProductList.stream()
                .filter(product -> product.getAddOnPrice() > 0 && product.getAddOnPriceAfterDiscount() > 0)
                .findAny().ifPresent(product -> fail("Discount should not be applied to any add-on"));
        assertEquals(1, discountedProductList.stream().filter(product -> product.getItemPriceAfterDiscount() == 3.5).count());
    }

    @Test
    public void whenInputListHasBeverageAndSnackAndBeverageCountIsLessThanFiveThenDiscountIsAppliedToOnlyOneExpensiveAddOn() {
        List<Product> discountedProductList = discountService.applyDiscounts(getProductsWithBeverageAndSnackAndBeverageCountLessThanFive());
        assertEquals(1, discountedProductList.stream()
                .filter(product -> product.getAddOnPrice() > 0 && product.getAddOnPriceAfterDiscount() > 0).count());
    }

    @Test
    public void whenInputListHasDrinkAndBeverageWithAddOnThenNoDiscountIsAppliedToAnyAddOn() {
        List<Product> discountedProductList = discountService.applyDiscounts(getProductsWithDrinkAndBeverageWithAddOn());
        assertEquals(0, discountedProductList.stream()
                .filter(product -> product.getAddOnPrice() > 0 && product.getAddOnPriceAfterDiscount() > 0).count());
    }
}