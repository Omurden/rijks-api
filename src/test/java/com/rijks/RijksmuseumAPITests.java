package com.rijks;

import io.restassured.RestAssured;
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

    @Test
    @Description("Retrieve collections with pagination and verify the response")
    public void testRetrieveCollectionsWithPagination() {
        retrieveCollectionsWithPagination(1, 10);
    }

    @Step("Retrieving collections with page '{page}' and page size '{pageSize}'")
    public void retrieveCollectionsWithPagination(int page, int pageSize) {
        given()
            .queryParam("key", API_KEY)
            .queryParam("p", page)
            .queryParam("ps", pageSize)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("artObjects.size()", lessThanOrEqualTo(pageSize));
    }

    @Test
    @Description("Retrieve collections sorted by relevance and verify the response")
    public void testRetrieveCollectionsSortedByRelevance() {
        retrieveCollectionsSortedByRelevance("landscape");
    }

    @Step("Retrieving collections for query '{query}' sorted by relevance")
    public void retrieveCollectionsSortedByRelevance(String query) {
        given()
            .queryParam("key", API_KEY)
            .queryParam("q", query)
            .queryParam("s", "relevance")
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("artObjects", notNullValue());
    }

    @Test
    @Description("Retrieve collections in a different language (Dutch) and verify the response")
    public void testRetrieveCollectionsInDifferentLanguage() {
        retrieveCollectionsInDifferentLanguage("nl", "Rembrandt");
    }

    @Step("Retrieving collections with language '{language}' and query '{query}'")
    public void retrieveCollectionsInDifferentLanguage(String language, String query) {
        given()
            .queryParam("key", API_KEY)
            .queryParam("lang", language)
            .queryParam("q", query)
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("artObjects", notNullValue())
            .body("artObjects.size()", greaterThan(0));
    }
}