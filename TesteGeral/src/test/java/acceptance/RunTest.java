package acceptance;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin ={"pretty" , "html:TestReport"},
        features = "src/test/resources/features/")
public class RunTest{

}
