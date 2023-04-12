# What is this application about?
Java console application to place an order from available products in a coffee shop setup.
Build tool Gradle is used to build the application.

# Assumptions
1. Combination of offerings and extras shared in the problem statement are the only valid combinations.
2. Order can only be placed only with valid products.
3. In case of any invalid input, the application will throw an exception and exit.
4. Quantity cannot be entered as input and order can only be placed for one quantity and using product names.
5. Only one offer can be redeemed per order even if the order qualifies for multiple offers.
6. When order is qualified for free beverage, only beverage cost is discounted. Extra/Add-on if added to the product is not discounted.
7. If the order qualifies for multiple offers, the offer with the highest discount will be applied.

# Prerequisites
1. OpenJDK 11
2. Any IDE (Eclipse, IntelliJ, etc.) preferably IntelliJ
3. Internet connection to download the dependencies and build the application
4. Configure test runner in the IDE to run the tests. Set "run tests using" option to "Intellij Idea". See following link for more details if need be:
   https://www.jetbrains.com/help/idea/work-with-tests-in-gradle.html#configure_gradle_test_runner

# Information about Gradle
Gradle wrapper is made available in the application. So, no need to install Gradle separately.

# How to run the application?
1. Download the application from the repository
2. Open the application in an IDE (Eclipse, IntelliJ, etc.)
3. Setup JDK 11
4. Run the application expanding the "src/main/java" folder and right-clicking on "CafeCorner" class, click on "Run CafeCorner.Main()".
5. Check console for the output. Note, I have added a sample input in the application. So, you can see the output without entering any input.
6. To run the tests, expand the "src" folder and right-click on "test" folder, click on "Run All Tests".
7. Check console for the test results. NOTE: In case you don't see the test results, please check the IDE test runner settings as mentioned in the prerequisites section.
8. Refer "CoffeeCornerIntegrationTest.java" and method test7() for product list from which order can be placed.
