package JUnit5;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;

@RunWith(JUnitPlatform.class)
//@ExtendWith(JUnitPlatform.class)  ///JUnit5

//By default, it will only include test classes whose names either begin with Test or end with Test or Tests.
@SelectPackages("JUnit5")
//@SelectPackages({"JUnit5","som.other.folder.package"})   //for more packages then put them in {}

//@SelectPackages("som.other.folder.package")           //if we want to be more specific what is being tested
@ExcludePackages("som.other.folder.package2")
@IncludePackages("som.other.folder.package3")

@IncludeTags("production")      //put tags for specifying environments where the tests should run
@ExcludeTags("development")

@SelectClasses( JUnitApiAdvanced.class )            //if we want to run specific classes
//@SelectClasses( {CalculatorTest.class, MoneyTransactionServiceTest.class} )   //if selecting more than 1 class then put them in {}

public class TestSuiteExample {

}
