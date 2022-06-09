package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CurdStoreStepTest extends TestBase
{

     static String name = "hello" + TestUtils.getRandomValue();
     static String type = "hello";
     static String address = "hello";
     static String address2 = "hello";
     static String city = "hello";
     static String state = "hello";
     static String zip = "hello";
     static int lat = 014555;
     static int lng= 011255;
     static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9;Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static   HashMap<Object, Object> services = new HashMap<>();
     static int id;


    @Steps
   storeSteps storeSteps;


    @Title("creating new store data")
    @Test
    public void test001()
    {

        ValidatableResponse response = storeSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,services);
       id =  response.log().all().extract().path("id");
        System.out.println(id);
    }

    @Title("verify if store added")
    @Test
    public void test002()
    {
        HashMap<Object,Object> storemap =  storeSteps.getStoreInfoById(id);
        System.out.println(storemap+"====================");
    }

    @Title("update the store information and verify it")
    @Test
    public void test003()
    {
        name = name + "_updated";
        ValidatableResponse response = storeSteps.updateStoredata(id,name,type,address,address2,city,state,zip,lat,lng,hours,services);
        response.log().all().statusCode(200);

        HashMap<Object,Object> storemap = storeSteps.getStoreInfoById(id);
        Assert.assertThat(storemap,hasValue(name));
    }

    @Title("delete the created store")
    @Test
    public void test004()
    {
        storeSteps.deleteStore(id).statusCode(200);
        storeSteps.deleteStore(id).statusCode(404);
    }
}
