package com.bdd.APIFramework.cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( features="src/test/java/features", 
					glue= {"stepDefinations","resources"},
					plugin="json:target/jsonReports/cucumber-report.json"
)
public class TestRunner {
	
	//,tags=" @Before or @DeletePlace"

}
