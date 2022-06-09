package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class storeSteps {
    @Step("Creating store with name: {0} , type : {1} , address : {2} , address2 : {3} , city : {4} , state : {5} , zip : {6} , lat: {7} , lng : {8} , hours :{9}  , services:{10}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object, Object> services)
    {
        StorePojo storePojo = new StorePojo();

        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores")
                .then();
    }

    @Step("getting the information by id : {0}")
    public HashMap<Object, Object> getStoreInfoById(int id) {


    HashMap<Object, Object> storeMap = SerenityRest.given().log().all()
            .when()
            .pathParam("id",id)
            .get(EndPoints.GET_SINGLE_DATABYID)
            .then()
            .statusCode(200)
            .extract()
            .path("");
    return storeMap;

    }

    @Step("updating store data and verifying")
    public ValidatableResponse updateStoredata(int id ,String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object, Object> services)
    {
        StorePojo storePojo = new StorePojo();

        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("delete store data by id")
    public ValidatableResponse deleteStore(int id)
    {
        return SerenityRest.given().log().all()
            .pathParam("id", id )
            .when()
            .delete(EndPoints.GET_SINGLE_DATABYID)
            .then();
    }
}
