package JUnit5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamcrestApp {
    @Test
    void firstHamcrestTest() {
        String actualString = "some value";
        String expectedString = "some value";

//		assertThat("optional error message", actualString, equalTo(expectedString));
        assertThat(actualString, equalTo(expectedString));
    }

    @Test
    void secondHamcrestTest() {
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        assertThat(list, hasItem(anyOf(equalTo("a"), equalTo("b"), equalTo("c"))));  //hamcrest

        assertTrue(list.contains("a") || list.contains("b") || list.contains("c")); //Junit5
    }
}
