import controller.OrderManager;
import service.CafeService;
import service.DiscountService;
import service.PriceService;
import util.ProductUtility;
import validator.OrderValidator;

import java.util.Arrays;
import java.util.List;

public class CafeCorner {

    public static void main(String[] args) {
        OrderValidator validator = new OrderValidator();
        ProductUtility productUtility = new ProductUtility();
        DiscountService discountService = new DiscountService();
        PriceService priceService = new PriceService();
        CafeService cafeService = new CafeService(priceService);
        OrderManager controller = new OrderManager(validator, productUtility, discountService, cafeService);
        List<String> orderedItems = Arrays.asList("Small Coffee",
                "Medium Coffee",
                "Large Coffee",
                "Small Coffee With Extra Milk",
                "Small Coffee With Foamed Milk",
                "Bacon Roll");
        controller.placeOrder(orderedItems);
    }
}