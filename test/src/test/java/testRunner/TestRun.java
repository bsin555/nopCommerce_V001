package testRunner;

import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions
		(
		features= {".//features/"},
		glue= {"stepDefinations"},
		dryRun=false,
		monochrome= true,
		plugin= {"pretty",
				"html:test-output"},
		tags= {"@sanity or @regression"}
		)

public class TestRun {

}
