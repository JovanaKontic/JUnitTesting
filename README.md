# JUnit5 (Jupiter) Testing
## Autommation Tests
Unit testing help us to test each specific unit of completion, that is our classes.
With unit tests, we have the ability and to verify that each unit behaves in the way it expects to behave.
Unit testing is about testing one single unit in isolation and without testing simultaneously different units. 
We have to test each unit separately and independently to make sure that the root cause of the error is in this specific unit. 
And that the test case failure is not caused by another class.

Rule #1:  one assertion per test or single concept by test     
Rule #2:  test one single object at a time        
Rule #3:  choose meaningful test method names           

F.I.R.S.T. principles        
F: FAST – test should be fast, because there are many of them (miliseconds)        
I: INDEPENDENT or ISOLATED – environment like chrome or edge or timezone …           
R: REPEATABLE – In casetest in some way changed the environment, you have to clean up everything after test is finished. 
(if you perform any data manipulations with database you have to clean up everything after each test is finished)             
S: SELF VALIDATING - we should not go through a log file or compare data to know if they pass ( we use assertions)             
T: THOROUGH or TIMELY – tests should cover all the happy pass (write test even before you created code)              

Test Driven Development is a process where writing tests guide software development.
The purpose of testable code is not only testability, but it is also the easiness of dealing with code understandability, reusability, maintainability and extendability.

 Test Driven Development (TDD) Cycle:
1. Don’t write a line of new code unless you first have a failing automated test
2. Eliminate duplication

UNIT TEST structure :
* AAA – arrange/act/assert
* Build/operate/check
* Given/and/then

START :        
•	Open IntelliJ IDEA       
•	New project( java, maven, ok)    
•	Add dependency in POM            
<dependency>
<groupId>org.junit.jupiter</groupId>
<artifactId>junit-jupiter</artifactId>
<version>5.9.2</version>
<scope>test</scope>
</dependency>       
•	Add build      
<build>
<plugins>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>3.0.0-M7</version>
</plugin>
</plugins>
</build>        
•	Create new class in main/java    
•	On the new method name right click/ generate test/select methods to test         
•	Click OK      
•	New class in the test folder will be created and there will be tests for all selected methods
