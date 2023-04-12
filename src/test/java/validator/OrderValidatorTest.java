package validator;

import data.TestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderValidatorTest {
    final OrderValidator validator = new OrderValidator();

    public static Stream<Arguments> testInvalidProducts() {
        return Stream.of(
                Arguments.of(List.of("Coke", "Coke", "Coke")),
                Arguments.of(List.of("Coke", "Small Coffee")),
                Arguments.of(List.of("Small Coffee1")),
                Arguments.of(List.of("1Small Coffee")));
    }

    public static Stream<Arguments> testValidProducts() {
        return Stream.of(
                Arguments.of(List.of("Small Coffee", "Large Coffee With Extra Milk", "Small Coffee With Extra Milk", "Large Coffee", "Medium Coffee", "Bacon Roll")),
                Arguments.of(List.of("Orange Juice", "Large Coffee With Special Roast", "Small Coffee With Extra Milk", "Large Coffee", "Medium Coffee", "Bacon Roll")));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("testInvalidProducts")
    public void whenInputIsEitherNullOrEmptyOrHasInvalidProductsThenIllegalArgumentExceptionIsThrown(List<String> orderedItems) {
        assertThrows(IllegalArgumentException.class, () -> validator.validateOrderedItems(orderedItems, TestData.AVAILABLE_PRODUCTS));
    }

    @ParameterizedTest
    @MethodSource("testValidProducts")
    public void whenInputHasAllValidProductsThenNoExceptionIsThrown(List<String> orderedItems) {
        assertDoesNotThrow(() -> validator.validateOrderedItems(orderedItems, TestData.AVAILABLE_PRODUCTS));
    }

}