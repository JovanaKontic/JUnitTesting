package JUnit5;

import JUnit5.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        int actual = calculator.add(2,3);
        assertEquals(5,actual);

    }
}