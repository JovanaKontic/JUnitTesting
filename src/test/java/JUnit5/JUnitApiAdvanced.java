package JUnit5;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.JRE.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

//@Tag("production")
// * There are different test method order implementation. They are:
// * - DisplayName
// * - MethodName
// * - OrderAnnotation   //rarely user in unit testing because the order is not important(m every test is isolated and not connected with another)
// * - Random

@TestMethodOrder(OrderAnnotation.class)
//@Order and specify number

public class JUnitApiAdvanced {
    @Test
    @Tag("production")
    void testForProductionEnvironment() {
    }
    @Test
    @Order(1)         // first test to  execute
    void order1() {
    }
    @Test
    @Order(2)         // second test to  execute
    void order2() {
    }
    @Test
    @Order(3)      // third test to  execute
     void order3() {
    }
// Conditions Test Excecution - enable/disable
//		-	Operating System conditions
//		-	Java Runtime Environments conditions
//		-	System Property Conditions
//		-	Environment Variable Conditions
//		-	Custom conditions
//
    //  Operating System Conditions
    @Test
    @EnabledOnOs(OS.MAC) //only work on MacOS
    void onlyOnMacOs() {
    }
    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC }) //only work on MacOS and Linux
    void onLinuxOrMac() {
    }
    @Test
    @DisabledOnOs(OS.WINDOWS) //only doesn't work on Windows
    void notOnWindows() {
    }
    //  JRE Conditions - Java Runtime Environment
    @Test
    @EnabledOnJre(JAVA_8)
    void onlyOnJava8() {
    }
    @Test
    @EnabledOnJre({ JAVA_9, JAVA_10 })
    void onJava9Or10() {
    }
    @Test
    @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
    void fromJava9to11() {
    }
    @Test
    @EnabledForJreRange(min = JAVA_9)
    void fromJava9toCurrentJavaFeatureNumber() {
    }
    @Test
    @EnabledForJreRange(max = JAVA_11)
    void fromJava8To11() {
    }
    @Test
    @DisabledOnJre(JAVA_9)
    void notOnJava9() {
    }
    @Test
    @DisabledForJreRange(min = JAVA_9, max = JAVA_11)
    void notFromJava9to11() {
    }
    @Test
    @DisabledForJreRange(min = JAVA_9)
    void notFromJava9toCurrentJavaFeatureNumber() {
    }
    @Test
    @DisabledForJreRange(max = JAVA_11)
    void notFromJava8to11() {
    }
    // System properties conditions
//You can use, enabled or disabled property annotations to check if specific properties are equal to the value we pass here.
// The value applied via matches attribute will be interpreted as a regular expression.
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
    }
    @Test
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    void notOnCiServer() {
    }
    // Environment variables conditions
//    like system properties
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() {
    }
    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void notOnDeveloperWorkstation() {
    }
    // Custom conditions
//    We can use a enable or disable if annotations and paste to it the name of the method that we are going
//to use to define the condition. (customCondition).
//The method should return is a true or false, and based on this,the test will be is enabled or disabled.
//This option gives you a lot of flexibility in definition of your own condition.
    @Test
    @EnabledIf("customCondition")
    void enabled() {
    }
    @Test
    @DisabledIf("customCondition")
    void disabled() {
    }
    boolean customCondition() {
        return true;
    }
    // = Repeated test
    //	@RepeatedTest(10) //just repetition
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}") //repetition with displayed name and test iteration number
//    use when asserting if even after multiple test , always same result
    void repeatedTest() {
    }
    // Extension - TempDir

    @Test
    void tempDirExample(@TempDir Path tempDir) throws IOException {
//        when working with files, temporary files that we create for the purpose of testing
//        In this particular case I wrote some text to a file and after that verified the text in the file is the same as I expect.
//        After test execution's, this file will be removed, so there is no impact on your local file system.
        Path path = tempDir.resolve("test");
        Files.write(path, "some text".getBytes());
        assertEquals("some text", Files.readAllLines(path).stream().collect(Collectors.joining()));
    }
}
