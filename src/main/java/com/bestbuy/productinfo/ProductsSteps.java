package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {

    @Step("Creating product  with name : {0} , type : {1} , price {2} , shipping: {3} , upc : {4} , description : {5} , manufacturer:{6}, model: {7} , url : {8} , image : {9} ")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {

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

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

    @Step("getting the information of product with id: {0}")
    public HashMap<String, ?> getProductinfoByName(int id) {
        HashMap<String, ?> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("productId", id)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;
    }

    @Step("updating product info with productId : {0}, name : {1} , type : {2} , price {3} , shipping: {4} , upc : {5} , description : {6} , manufacturer:{7}, model: {8} , url : {9} , image : {10} and verifying")
    public ValidatableResponse updateStudent(int productId, String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
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

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("deleting product information with productid: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("getting product info by id : {0}")
    public ValidatableResponse getProductById(int productId)
    {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId )
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }


}
