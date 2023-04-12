package service;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CafeServiceTest {

    private CafeService cafeService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        PriceService priceService = new PriceService();
        cafeService = new CafeService(priceService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void whenNullSourceIsAppliedThenNullPointerExceptionIsThrown() {
        assertThrows(NullPointerException.class, () -> cafeService.generateReceipt(null));
    }

    @Test
    public void whenEmptySourceIsAppliedThenReceiptWithNoProductsAndZeroAmountIsGenerated() {
        List<Product> products = new ArrayList<>();
        cafeService.generateReceipt(products);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                           Total amount to be paid: -";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void whenInputListHasProductsThenReceiptIsGeneratedWithProductsAndNonZeroAmount() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product1", 3.00, 0.50, "Beverage"));
        cafeService.generateReceipt(products);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                           Product1          1            3,00               -                 0,50                    -                           3,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                        Total amount to be paid: 3,50";
        assertEquals(expectedOutput, outContent.toString());
    }
}