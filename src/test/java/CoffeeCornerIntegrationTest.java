import controller.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CafeService;
import service.PriceService;
import util.ProductUtility;
import validator.OrderValidator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeCornerIntegrationTest {

    private OrderManager controller;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        OrderValidator validator = new OrderValidator();
        ProductUtility productUtility = new ProductUtility();
        PriceService priceService = new PriceService();
        CafeService cafeService = new CafeService(priceService);
        controller = new OrderManager(validator, productUtility, cafeService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("When Order With Small Coffee With Extra Milk And Bacon Roll Is Placed Then Receipt With Total Amount To Be Paid Is Generated with add on discounted")
    public void test() {
        List<String> orderedList = Arrays.asList(
                "Small Coffee With Extra Milk",
                "Bacon Roll");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "       Small Coffee With Extra Milk          1            2,50               -                 0,30                 0,30                           2,50\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                        Total amount to be paid: 7,00";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With Large Coffee And Bacon Roll Is Placed Then Receipt With Total Amount To Be Paid Is Generated with out discount")
    public void test2() {
        List<String> orderedList = Arrays.asList(
                "Large Coffee",
                "Bacon Roll");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                       Large Coffee          1            3,50               -                    -                    -                           3,50\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                        Total amount to be paid: 8,00";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With Bacon Roll Is Placed Then Receipt With Total Amount To Be Paid Is Generated with out discount")
    public void test3() {
        List<String> orderedList = List.of(
                "Bacon Roll");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                        Total amount to be paid: 4,50";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With Snack And Beverage With/With out add on Is Placed Then Receipt With Total Amount To Be Paid Is Generated with one of the item discounted")
    public void test4() {
        List<String> orderedList = Arrays.asList("Small Coffee",
                "Medium Coffee",
                "Large Coffee",
                "Small Coffee With Extra Milk",
                "Small Coffee With Foamed Milk",
                "Bacon Roll");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                       Small Coffee          1            2,50               -                    -                    -                           2,50\n" +
                "                      Medium Coffee          1            3,00               -                    -                    -                           3,00\n" +
                "                       Large Coffee          1            3,50            3,50                    -                    -                              -\n" +
                "       Small Coffee With Extra Milk          1            2,50               -                 0,30                    -                           2,80\n" +
                "      Small Coffee With Foamed Milk          1            2,50               -                 0,50                    -                           3,00\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                       Total amount to be paid: 15,80";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With Snack And Beverage With/With out add on Is Placed Then Receipt With Total Amount To Be Paid Is Generated with one of the add on discounted")
    public void test5() {
        List<String> orderedList = Arrays.asList(
                "Medium Coffee",
                "Medium Coffee",
                "Medium Coffee",
                "Small Coffee With Foamed Milk",
                "Small Coffee With Special Roast",
                "Bacon Roll");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                      Medium Coffee          1            3,00               -                    -                    -                           3,00\n" +
                "      Small Coffee With Foamed Milk          1            2,50               -                 0,50                    -                           3,00\n" +
                "    Small Coffee With Special Roast          1            2,50               -                 0,90                 0,90                           2,50\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                       Total amount to be paid: 13,00";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With Drink And Beverage With/With out add on Is Placed Then Receipt With Total Amount To Be Paid Is Generated with out discount")
    public void test6() {
        List<String> orderedList = Arrays.asList("Small Coffee",
                "Medium Coffee With Extra Milk",
                "Orange Juice");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                       Small Coffee          1            2,50               -                    -                    -                           2,50\n" +
                "      Medium Coffee With Extra Milk          1            3,00               -                 0,30                    -                           3,30\n" +
                "                       Orange Juice          1            3,95               -                    -                    -                           3,95\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                        Total amount to be paid: 9,75";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("When Order With all products in cafe Is Placed Then Receipt With Total Amount To Be Paid Is Generated with discount")
    public void test7() {
        List<String> orderedList = Arrays.asList("Small Coffee",
                "Medium Coffee",
                "Large Coffee",
                "Small Coffee With Extra Milk",
                "Small Coffee With Foamed Milk",
                "Small Coffee With Special Roast",
                "Medium Coffee With Extra Milk",
                "Medium Coffee With Foamed Milk",
                "Medium Coffee With Special Roast",
                "Large Coffee With Extra Milk",
                "Large Coffee With Foamed Milk",
                "Large Coffee With Special Roast",
                "Bacon Roll",
                "Orange Juice");
        controller.placeOrder(orderedList);
        String expectedOutput = "----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                            PRODUCT        QUANTITY      ITEM_PRICE     DISCOUNT_ON_ITEM     ADDON_PRICE         DISCOUNT_ON_ADDON               TOTAL_PRICE\n" +
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                       Small Coffee          1            2,50               -                    -                    -                           2,50\n" +
                "                      Medium Coffee          1            3,00               -                    -                    -                           3,00\n" +
                "                       Large Coffee          1            3,50            3,50                    -                    -                              -\n" +
                "       Small Coffee With Extra Milk          1            2,50               -                 0,30                    -                           2,80\n" +
                "      Small Coffee With Foamed Milk          1            2,50               -                 0,50                    -                           3,00\n" +
                "    Small Coffee With Special Roast          1            2,50               -                 0,90                    -                           3,40\n" +
                "      Medium Coffee With Extra Milk          1            3,00               -                 0,30                    -                           3,30\n" +
                "     Medium Coffee With Foamed Milk          1            3,00               -                 0,50                    -                           3,50\n" +
                "   Medium Coffee With Special Roast          1            3,00               -                 0,90                    -                           3,90\n" +
                "       Large Coffee With Extra Milk          1            3,50            3,50                 0,30                    -                           0,30\n" +
                "      Large Coffee With Foamed Milk          1            3,50               -                 0,50                    -                           4,00\n" +
                "    Large Coffee With Special Roast          1            3,50               -                 0,90                    -                           4,40\n" +
                "                         Bacon Roll          1            4,50               -                    -                    -                           4,50\n" +
                "                       Orange Juice          1            3,95               -                    -                    -                           3,95\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "                                                       Total amount to be paid: 42,55";
        assertEquals(expectedOutput, outContent.toString());
    }

}