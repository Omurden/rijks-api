package com.rijks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class RijksmuseumAPITests {

    private static final String BASE_URL = "https://www.rijksmuseum.nl/api/en/collection";
    private static final String API_KEY = "0fiuZFh4";

    @BeforeAll
    @Step("Setting up the base URL for RestAssured")
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("Retrieve the list of collections and verify the response")
    public void testRetrieveCollections() {
        retrieveCollections("Rembrandt");
    }

    @Step("Retrieving collections with query parameter '{query}'")
    public void retrieveCollections(String query) {
        given()
            .queryParam("key", API_KEY)
            .queryParam("q", query)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("artObjects", notNullValue())
            .body("artObjects.size()", greaterThan(0));
    }

    @Test
    @Description("Test to retrieve details of a specific art object")
    public void testRetrieveObjectDetails() {
        retrieveObjectDetails("SK-C-5");
    }

    @Step("Retrieving object details for object number '{objectNumber}'")
    public void retrieveObjectDetails(String objectNumber) {
        given()
            .queryParam("key", API_KEY)
        .when()
            .get("/{objectNumber}", objectNumber)
        .then()
            .statusCode(200)
            .body("artObject.title", notNullValue())
            .body("artObject.objectNumber", equalTo(objectNumber));
    }
    //dummy text24
}
