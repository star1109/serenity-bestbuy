package com.bestbuy.productinfo;

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
public class ProductCurdStepTest extends TestBase {

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
    @Steps
    ProductsSteps productsSteps;


    @Title("This will create new Product")
    @Test
    public void test001() {

       ValidatableResponse response =  productsSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
      productId =  response.log().all().extract().path("id");
    }

    @Title("Verify if product added to the list or not")
    @Test
    public void test002()
    {
         HashMap<String,?> productMap =  productsSteps.getProductinfoByName(productId);
        Assert.assertThat(productMap,hasValue(name));
    }

    @Title("Update product and verify")
    @Test
    public void test003()
    {
        name = name + "_updated";
        ValidatableResponse response = productsSteps.updateStudent(productId,name, type,price ,shipping , upc,description, manufacturer, model ,url , image);
        response.log().all().statusCode(200);

        HashMap<String,?> productmap = productsSteps.getProductinfoByName(productId);
        Assert.assertThat(productmap,hasValue(name));

    }

    @Title("delete the created product and verify")
    @Test
    public void test004()
    {
        productsSteps.deleteProduct(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }
}
