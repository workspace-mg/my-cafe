package service;

import model.Product;
import util.AddOnComparator;
import util.ProductPriceComparator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiscountService {

    private final List<String> ITEM_TYPE_LIST_VALID_FOR_DISCOUNT = Arrays.asList("Beverage", "Snack");

    public boolean isQualifiedForSnackAndBeverageComboDiscount(List<Product> products) {
        Objects.requireNonNull(products, "Product list cannot be null");
        return products.stream().map(Product::getType).collect(Collectors.toSet()).containsAll(ITEM_TYPE_LIST_VALID_FOR_DISCOUNT);
    }

    public int getNumberOfFreeCoffee(List<Product> products) {
        Objects.requireNonNull(products, "Product list cannot be null");
        int numberOfBeverages = Math.toIntExact(products.stream().filter(product -> product.getType().equalsIgnoreCase("Beverage")).count());
        return numberOfBeverages / 5;
    }

    public List<Product> applyDiscounts(List<Product> products) {
        Objects.requireNonNull(products, "Product list cannot be null");

        int freeCoffeeCount = getNumberOfFreeCoffee(products);
        boolean isQualifiedForSnackAndBeverageComboDiscount = isQualifiedForSnackAndBeverageComboDiscount(products);

        List<Product> beverages = products.stream()
                .filter(product -> product.getType().equalsIgnoreCase("Beverage"))
                .sorted(new ProductPriceComparator()).collect(Collectors.toList());

        beverages.stream()
                .limit(freeCoffeeCount)
                .forEach(product -> product.setItemPriceAfterDiscount(product.getItemPrice()));

        if (isQualifiedForSnackAndBeverageComboDiscount && freeCoffeeCount == 0) {
            beverages.stream()
                    .sorted(new AddOnComparator())
                    .limit(1)
                    .forEach(product -> product.setAddOnPriceAfterDiscount(product.getAddOnPrice()));
        }

        return products;
    }


}
