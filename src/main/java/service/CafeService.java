package service;

import model.Product;

import java.util.List;
import java.util.Objects;

public class CafeService {
    private final PriceService priceService;

    public CafeService(PriceService priceService) {
        this.priceService = priceService;
    }

    public void generateReceipt(List<Product> products) {
        Objects.requireNonNull(products, "Product list cannot be null");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%35s %15s %15s %20s %15s %25s %25s", "PRODUCT", "QUANTITY", "ITEM_PRICE", "DISCOUNT_ON_ITEM", "ADDON_PRICE", "DISCOUNT_ON_ADDON", "TOTAL_PRICE");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Product product : products) {
            System.out.format("%35s %10s %15s %15s %20s %20s %30s",
                    product.getName(),
                    1,
                    formatPrice(product.getItemPrice()),
                    formatPrice(product.getItemPriceAfterDiscount()),
                    formatPrice(product.getAddOnPrice()),
                    formatPrice(product.getAddOnPriceAfterDiscount()),
                    formatPrice(product.getItemPrice() - product.getItemPriceAfterDiscount() +
                            product.getAddOnPrice() - product.getAddOnPriceAfterDiscount())
            );
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%85s", "Total amount to be paid: " + formatPrice(priceService.calculateTotalPrice(products)));
    }

    private String formatPrice(double price) {
        return price == 0.0 ? "-" : String.format("%.2f", price);
    }
}
