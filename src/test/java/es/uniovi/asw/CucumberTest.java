package es.uniovi.asw;

import cucumber.api.CucumberOptions;

@CucumberOptions(plugin = { "pretty",
		"html:target/cucumber-html-report" }, features = "src/test/resources/features")
public class CucumberTest {

}