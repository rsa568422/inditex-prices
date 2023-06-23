package org.inditex.prices.api.controllers;

import org.inditex.prices.api.InditexPricesApiExceptionHandler;
import org.inditex.prices.api.responses.PriceResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerIntegrationTest {

    private final TestRestTemplate client;

    @Autowired
    public PriceControllerIntegrationTest(TestRestTemplate client) {
        this.client = client;
    }

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    void testFindApplicablePrice1() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-10.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<PriceResponse> response = client.getForEntity(getFullUri(url), PriceResponse.class);

        PriceResponse price = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> assertEquals(1L, price.getBrandId()),
                () -> assertEquals(35455L, price.getProductId()),
                () -> assertEquals(new BigDecimal("35.50"), price.getPrice()),
                () -> assertEquals("2020-06-14-00.00.00",price.getStartDate()),
                () -> assertEquals("2020-12-31-23.59.59",price.getEndDate())
        );
    }

    @Test
    @Order(2)
    void testFindApplicablePrice2() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-16.00.00";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<PriceResponse> response = client.getForEntity(getFullUri(call), PriceResponse.class);

        PriceResponse price = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> assertEquals(1L, price.getBrandId()),
                () -> assertEquals(35455L, price.getProductId()),
                () -> assertEquals(new BigDecimal("25.45"), price.getPrice()),
                () -> assertEquals("2020-06-14-15.00.00",price.getStartDate()),
                () -> assertEquals("2020-06-14-18.30.00",price.getEndDate())
        );
    }

    @Test
    @Order(3)
    void testFindApplicablePrice3() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-21.00.00";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<PriceResponse> response = client.getForEntity(getFullUri(call), PriceResponse.class);

        PriceResponse price = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> assertEquals(1L, price.getBrandId()),
                () -> assertEquals(35455L, price.getProductId()),
                () -> assertEquals(new BigDecimal("35.50"), price.getPrice()),
                () -> assertEquals("2020-06-14-00.00.00",price.getStartDate()),
                () -> assertEquals("2020-12-31-23.59.59",price.getEndDate())
        );
    }

    @Test
    @Order(4)
    void testFindApplicablePrice4() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-15-10.00.00";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<PriceResponse> response = client.getForEntity(getFullUri(call), PriceResponse.class);

        PriceResponse price = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> assertEquals(1L, price.getBrandId()),
                () -> assertEquals(35455L, price.getProductId()),
                () -> assertEquals(new BigDecimal("30.50"), price.getPrice()),
                () -> assertEquals("2020-06-15-00.00.00",price.getStartDate()),
                () -> assertEquals("2020-06-15-11.00.00",price.getEndDate())
        );
    }

    @Test
    @Order(5)
    void testFindApplicablePrice5() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-16-21.00.00";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<PriceResponse> response = client.getForEntity(getFullUri(call), PriceResponse.class);

        PriceResponse price = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType()),
                () -> assertEquals(1L, price.getBrandId()),
                () -> assertEquals(35455L, price.getProductId()),
                () -> assertEquals(new BigDecimal("38.95"), price.getPrice()),
                () -> assertEquals("2020-06-15-16.00.00",price.getStartDate()),
                () -> assertEquals("2020-12-31-23.59.59",price.getEndDate())
        );
    }

    @Test
    @Order(6)
    void testFindApplicablePriceFailure1() {
        String brandId = "1";
        String productId = "3545";
        String date = "2020-06-16-21.00.00";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<InditexPricesApiExceptionHandler.ErrorMessage> response =
                client.getForEntity(getFullUri(call), InditexPricesApiExceptionHandler.ErrorMessage.class);

        InditexPricesApiExceptionHandler.ErrorMessage errorMessage = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()),
                () -> assertEquals("Price not found", errorMessage.getError()),
                () -> assertEquals(call, errorMessage.getMessage())
        );
    }

    @Test
    @Order(7)
    void testFindApplicablePriceFailure2() {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-16";
        String call = String.format("/api/prices/%s&%s&%s", brandId, productId, date);
        ResponseEntity<InditexPricesApiExceptionHandler.ErrorMessage> response =
                client.getForEntity(getFullUri(call), InditexPricesApiExceptionHandler.ErrorMessage.class);

        InditexPricesApiExceptionHandler.ErrorMessage errorMessage = Objects.requireNonNull(response.getBody());

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals("Bad format in request date", errorMessage.getError()),
                () -> assertEquals("Something was wrong processing the request", errorMessage.getMessage())
        );
    }

    private String getFullUri(String partialUri) {
        return String.format("http://localhost:%d%s", port, partialUri);
    }

}