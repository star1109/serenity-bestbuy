/*
package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import static net.serenitybdd.rest.RestRequests.given;

//@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {

    static String name = "practice" + TestUtils.getRandomValue();
    static String type = "practice1";
    static int price = 100000;
    static int shipping = 0;
    static String upc = "practice1";
    static String description = "practice1";
    static String manufacturer = "practice1";
    static String model = "practice1";
    static String url = "practice1";
    static String image = "practice1";

    static int productId;


    @Title("This will create a new product")
    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        ValidatableResponse response = SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.GET_ALL_PRODUCTS)
                .then().log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);
    }

    @Title("Verify if the product is added to the application")
    @Test
    public void test002() {

        String data =  given().log().all()

                .when()
                .get("/products/" + productId)
                .then()
                .statusCode(200)
                .extract().path("name");
        System.out.println(name);
        Assert.assertEquals(name,data);

    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);


        ValidatableResponse response = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
        response.log().all().statusCode(200);

        String data =  given().log().all()

                .when()
                .get("/products/" + productId)
                .then()
                .statusCode(200)
                .extract().path("name");

        Assert.assertEquals(name,data);

    }

    @Title("delete the created product and verify that")
    @Test
    public void test004()
    {
        ValidatableResponse response = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
        response.log().all().statusCode(200);

          given().log().all()

                .when()
                .get("/products/" + productId)
                .then()
                .statusCode(404)
                .extract().path("name");
    }
}*/
