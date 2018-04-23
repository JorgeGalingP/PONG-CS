package com.example.jorge.pong;

/**
 * Created by Jorge on 23/04/2018.
 */

import com.example.jorge.pong.Activities.MainActivity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberSteps {

    // attributes
    private MainActivity activity;

    // unit tests
    @Given("^gameplay screen$")
    public void gameplay_screen() throws Throwable {
        activity = new MainActivity();
    }

    @When("^I tap the right of the screen$")
    public void tap_right() throws Throwable {
        activity = new MainActivity();
    }

    @Then("^The paddle moves to the right 1 unit$")
    public void move_right() throws Throwable {
        activity = new MainActivity();
    }

    // smoke tests
    @Given("^gameplay screen smoke$")
    public void gameplay_screenS() throws Throwable {
        activity = new MainActivity();
    }

    @When("^can shot$")
    public void tap_smoke() throws Throwable {
        activity = new MainActivity();
    }

    @Then("^shot enabled$")
    public void shot_enabled() throws Throwable {
        activity.enabledShot();
    }
}
