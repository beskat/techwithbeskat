package com.techwithbeskat.steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomUserApiSteps {

    private RequestSpecification request = given();
    private Response response;

    @When("API GET request is made to endpoint {string}")
    public void apiGETRequestIsMadeToEndpoint(String endpoint) {
        response = request.when().get(endpoint);
    }

    @Then("Verify status code is {int}")
    public void verifyStatusCodeIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("Verify response content type is {string}")
    public void verifyResponseContentTypeIs(String contentType) {
        assertTrue(response.getContentType().contains(contentType));
    }

    @When("Print out the response body to console")
    public void printOutTheResponseBodyToConsole() {
        response.prettyPrint();
    }

    @And("Print out the user's first name to console")
    public void printOutTheUserSFirstNameToConsole() {
        String firstName = response.path("results[0].name.first");
        System.out.println("First Name: " + firstName);
    }

    @And("Print out the user's last name to console")
    public void printOutTheUserSLastNameToConsole() {
        String lastName = response.path("results[0].name.last");
        System.out.println("Last Name: " + lastName);
    }

    @And("Print out the user's age to console")
    public void printOutTheUserSAgeToConsole() {
        int age = response.path("results[0].dob.age");
        System.out.println("Age: " + age);
    }
}
