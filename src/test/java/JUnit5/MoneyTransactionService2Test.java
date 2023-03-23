package JUnit5;

import exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("Money transaction service test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

//    Other Display Name Generators
//  - Standard
//  - Simple
//  - IndicativeSentences
class MoneyTransactionService2Test {
    private static final double randomMoneyAmount = 100;
    private static final double zeroMoneyAmount = 0;
    private static final String accountExceptionMessage = "Accounts shouldn't be null";
    private static final String moneyAmountMessage = "Money amount should be greater than 0";
    private static final double moreThenRandomMoneyAmount = 101;
    private static final double negativeAmount = -1;

    private MoneyTransactionService testInstance;
    @BeforeAll
    static void startUp() {
    }
    @BeforeEach
    void setUp () {
        testInstance = new MoneyTransactionService();
    }
    @Test
    void should_Transfer_Money_From_One_Account_To_Another_3() {
        //NESTED ASSERTION-DEPENDENT
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = new Account(zeroMoneyAmount);
        assertAll("Money Transaction",
                () -> {
                    boolean IsTransferred = testInstance.transferMoney(account1, account2, randomMoneyAmount);
                    assertTrue(IsTransferred);
                    assertAll("Money amount is changed on the accounts",
                            () -> assertEquals(zeroMoneyAmount, account1.getMoneyAmount()),
                            () -> assertEquals(randomMoneyAmount, account2.getMoneyAmount())
                    );
                }
        );
    }
    @Test
    void should_Transfer_Money_From_One_Account_To_Another_4() {
        //test if the action take more time
        //This is needed in the case we want to be sure that,for example,
        // some complex method with a lot of logic inside it doesn't
        //take more than one second to execute.
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = new Account(zeroMoneyAmount);
        assertTimeout(Duration.ofSeconds(1),
                ()-> testInstance.transferMoney(account1, account2, randomMoneyAmount) //secced in case action takes less than 1 second
        );
    }
    @Test
    @Timeout(2)
//    @Timeout(value=100, unit= TimeUnit.MILLISECONDS) // in case of needing  milliseconds instead of seconds(default)
    void should_Transfer_Money_From_One_Account_To_Another_5() {
        //TimOut
        //testing only if the transaction is possible
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = new Account(zeroMoneyAmount);
        boolean actualResult = assertTimeout(Duration.ofSeconds(1),
                ()-> {
            return testInstance.transferMoney(account1, account2, randomMoneyAmount);}
        );
        assertTrue(actualResult);
    }
    @Test
    void should_Transfer_Money_From_One_Account_To_Another_6() {
        //specify what is a valid input for this test
        //in case input is invalid we skip the test
        //applicable when we want to run test only in some environments and in another to skip
        //https://stackoverflow.com/questions/318239/how-do-i-set-environment-variables-from-java
        //https://stackoverflow.com/questions/8168884/how-to-test-code-dependent-on-environment-variables-using-junit

//        learn how to create environment in JUnit5

//        assumeTrue("Jovana".equals(System.getenv("Kontic")),
//                () -> "Aborting this test, because it is not running on "
//                        + "laptop of Andrii Piatakha");
//        Account account1 = new Account(randomMoneyAmount);
//        Account account2 = new Account(zeroMoneyAmount);
//        boolean actualResult = assertTimeout(Duration.ofSeconds(1),
//                ()-> {
//                    return testInstance.transferMoney(account1, account2, randomMoneyAmount);}
//        );
//        assertTrue(actualResult);
    }
    @ParameterizedTest
    @ValueSource(ints = {101,99,0,-15})
    void should_Transfer_Money_From_One_Account_To_Another_7(int moneyAmount) {
        assumeTrue(moneyAmount>0,"Money amount must be positive");
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = new Account(zeroMoneyAmount);
        assumeTrue(testInstance.transferMoney(account1, account2, randomMoneyAmount));
    }
    @ParameterizedTest
    @NullSource
    @EmptySource
    @NullAndEmptySource
    void nullAndEmptySources(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }
    @ParameterizedTest
    @MethodSource("sourceMethod")
    void testMethodSource(String arg) {
        assertNotNull(arg);
    }
    // return type can be any type that can be converted to a Stream.
    // For example: Collection, Iterator, Iterable, IntStream, DoubleStream, LongStream
    static Stream<String> sourceMethod() {
        return Stream.of("John", "Walter", "Derek");
    }
    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1"
    })
//    @Disabled("Disabled until Defect #11 will be fixed")
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }
    @AfterEach
    void cleanUp () {
    }
    @AfterAll
    static void tearDown () {
    }
}