package JUnit5;

import exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransactionServiceTest {
// 5*-creating variables
    private static final double randomMoneyAmount = 100;
    private static final double zeroMoneyAmount = 0;
    private static final String accountExceptionMessage = "Accounts shouldn't be null";
    private static final String moneyAmountMessage = "Money amount should be greater than 0";
    private static final double moreThenRandomMoneyAmount = 101;
    private static final double negativeAmount = -1;


// 1*-create a instance of the code class
    private MoneyTransactionService testInstance;
//2*-start creating tests
    @BeforeAll  //JUnit5 + static (@BeforeClass  //JUnit4 + static)
    static void startUp() {
        //before all test (@BeforeAll, @BeforeEach, @Test(1,2,3...), @AfterEach, @AfterAll)
    }
    @BeforeEach    //Junit5 (@Before   //JUnit4)
        //before each test start up
    void setUp () {
        //creating new instance so that each test start fresh, usually common configurations for every test
        testInstance = new MoneyTransactionService();
    }
//4*- writing tests
    @Test
    void shouldTransferMoneyFromOneAccountToAnother() {
        //access modifier - anything but private in JUnit5 (private only in JUnit4)
        //name-what behavior is expected, clear and precise for everyone
        //unit tests are also a documentation
        Account account1 = new Account(randomMoneyAmount);                       //GIVEN
        Account account2 = new Account(zeroMoneyAmount);
        testInstance.transferMoney(account1,account2,randomMoneyAmount);        //WHEN
        assertEquals(zeroMoneyAmount,account1.getMoneyAmount());                //THEN (2 assertions-single concept by test)
        assertEquals(randomMoneyAmount, account2.getMoneyAmount());
    }
    @Test
    void shouldThrowExceptionIfAccountFromIsNull () {
        Account account1 = null;
        Account account2 = new Account(randomMoneyAmount);
        //if there is an error an exception is raised if not lambda is there to make money transaction
        //there are two exception messages for IllegalArgumentException in our code and the "if" will decide what will be printed
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> testInstance.transferMoney(account1,account2,randomMoneyAmount));
        assertEquals(accountExceptionMessage, exception.getMessage());
    }
    //Junit4 - can throw exception in the beginning
// @org.junit.Test(expected = IllegalArgumentException.class)
//	public void shouldThrowExceptionIfAccountFromIsNull2() {
//		// GIVEN
//		Account account1 = null;
//		Account account2 = new Account(RANDOM_MONEY_AMOUNT);
//		testInstance = new MoneyTransactionService();
//		// WHEN & THEN
//		testInstance.transferMoney(account1, account2, randomMoneyAmount);
//	}
    @Test
    void shouldThrowExceptionIfAccountToIsNull () {
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = null;
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> testInstance.transferMoney(account1,account2,randomMoneyAmount));
        assertEquals(accountExceptionMessage, exception.getMessage());
    }
    @Test
    void shouldThrowNotEnoughMoneyExceptionWhenTransferingMoreMoney () {
        Account account1 = new Account(randomMoneyAmount);
        Account account2 = new Account(zeroMoneyAmount);
        assertThrows(NotEnoughMoneyException.class, ()-> testInstance.transferMoney(account1, account2, moreThenRandomMoneyAmount));
    }
    @Test
    void shouldThrowExceptionWhenTransferingNegativeAmount () {
        Account account1 = new Account();
        Account account2 = new Account();
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> testInstance.transferMoney(account1, account2, negativeAmount));
        assertEquals(moneyAmountMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenTransferingZeroAmount () {
        Account account1 = new Account();
        Account account2 = new Account();
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> testInstance.transferMoney(account1,account2, zeroMoneyAmount));
        assertEquals(moneyAmountMessage, exception.getMessage());
    }



//3*-ending tests
    @AfterEach      //Junit5 (@After  //JUnit4)
    void cleanUp () {
        // this will be done after every test, to null what we have done so not to make any permanent changes
    }
    @AfterAll     //Junit5 + static (@After  //JUnit4)
    static void tearDown () {
        // this will be done after all tests, to null what we have done so not to make any permanent changes
    }


}