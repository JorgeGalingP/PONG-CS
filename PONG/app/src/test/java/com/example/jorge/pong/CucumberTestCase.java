package com.example.jorge.pong;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by Jorge on 20/04/2018.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = {"app/src/test/java/com/example/jorge/pong/features/testing.feature",
                "app/src/test/java/com/example/jorge/pong/features/smokeTest.feature"}

)

public class CucumberTestCase {
}
