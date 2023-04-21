package data;

import model.Product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestData {

    public static final List<Product> AVAILABLE_PRODUCTS = Stream.of(
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

    public static List<Product> getProductsWithOnlyOneBeverage() {
        Product product = new Product("Small Coffee", 2.50, 0, "Beverage");
        return List.of(product);
    }

    public static List<Product> getDiscountAppliedProductsWithBeverageAndSnack() {
        Product productWithHigherAddon = new Product("Small Coffee With Special Roast", 2.50, 0.90, "Beverage");
        productWithHigherAddon.setAddOnPriceAfterDiscount(0.90);
        productWithHigherAddon.setItemPriceAfterDiscount(0.0);
        Product snack = new Product("Bacon Roll", 4.50, 0, "Snack");
        snack.setAddOnPriceAfterDiscount(0.0);
        snack.setItemPriceAfterDiscount(0.0);
        Product productWithLesserAddon = new Product("Medium Coffee With Extra Milk", 3.00, 0.30, "Beverage");
        productWithLesserAddon.setAddOnPriceAfterDiscount(0.0);
        productWithLesserAddon.setItemPriceAfterDiscount(0.0);
        return List.of(productWithHigherAddon, snack, productWithLesserAddon);
    }

    public static List<Product> getDiscountAppliedProductsWithFiveBeverages() {
        Product product1 = new Product("Small Coffee", 2.50, 0, "Beverage");
        product1.setAddOnPriceAfterDiscount(0.0);
        product1.setItemPriceAfterDiscount(0.0);
        Product product2 = new Product("Medium Coffee", 3.00, 0, "Beverage");
        product2.setAddOnPriceAfterDiscount(0.0);
        product2.setItemPriceAfterDiscount(0.0);
        Product product3 = new Product("Large Coffee", 3.50, 0, "Beverage");
        product3.setAddOnPriceAfterDiscount(0.0);
        product3.setItemPriceAfterDiscount(3.50);
        Product product4 = new Product("Small Coffee With Extra Milk", 2.50, 0.30, "Beverage");
        product4.setAddOnPriceAfterDiscount(0.0);
        product4.setItemPriceAfterDiscount(0.0);
        Product product5 = new Product("Small Coffee With Foamed Milk", 2.50, 0.50, "Beverage");
        product5.setAddOnPriceAfterDiscount(0.0);
        product5.setItemPriceAfterDiscount(0.0);
        return List.of(product1, product2, product3, product4, product5);
    }

}
