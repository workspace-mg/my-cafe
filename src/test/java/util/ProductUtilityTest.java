package util;

import data.TestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductUtilityTest {

    final ProductUtility productUtility = new ProductUtility();

    public static Stream<Arguments> testDifferentProducts() {
        return Stream.of(
                Arguments.of(List.of("SMALL Coffee")),
                Arguments.of(List.of("Small COFFEE")),
                Arguments.of(List.of("SmALl CoFFee")),
                Arguments.of(List.of("Small Coffee", "Large Coffee With Extra Milk", "Small Coffee With Extra Milk", "Large Coffee", "Medium Coffee", "Bacon Roll")),
                Arguments.of(List.of("Orange Juice", "Large Coffee With Special Roast", "Small Coffee With Extra Milk", "Large Coffee", "Medium Coffee", "Bacon Roll")));
    }

    @ParameterizedTest
    @MethodSource("testDifferentProducts")
    public void whenProductsByNameIsCalledThenReturnedListSizeIsSameAsListSizeThatWasPassedAsInput(List<String> enteredProducts) {
        assertEquals(enteredProducts.size(), productUtility.constructProductUsingOrderedNames(enteredProducts, TestData.AVAILABLE_PRODUCTS).size());
        assertTrue(productUtility.constructProductUsingOrderedNames(enteredProducts, TestData.AVAILABLE_PRODUCTS).size() > 0);
    }

}