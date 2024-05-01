package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SmartBearHomePage;
import pages.SmartbearLoginPage;
import pages.SmartbearOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmartBearSteps {

    WebDriver driver = Driver.getDriver();
    SmartbearLoginPage smartbearLoginPage=new SmartbearLoginPage();
    SmartBearHomePage smartBearHomePage=new SmartBearHomePage();
    SmartbearOrderPage smartbearOrderPage=new SmartbearOrderPage();
    List<Map<String, Object>>data;

    @Given("user navigates to smartbear application")
    public void user_navigates_to_smartbear_application() {
        driver.get(ConfigReader.getProperty("smartbearUrl"));
    }
    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        smartbearLoginPage.usernameInput.sendKeys(username);
        smartbearLoginPage.passwordInput.sendKeys(password);
        smartbearLoginPage.loginBtn.click();
    }

    @Then("user is successfully logged in and title is {string}")
    public void user_is_successfully_logged_in_and_title_is(String title) {
        Assert.assertEquals(title,driver.getTitle());
    }

    @When("User logoutApplication")
    public void user_logout_application() {
        smartbearLoginPage.logoutBtn.click();
    }
    @Then("user should see login page {string}")
    public void user_should_see_login_page(String title) {
        Assert.assertEquals(title,driver.getTitle());
    }
    @Then("user is not logged in and gets error message {string}")
    public void user_is_not_logged_in_and_gets_error_message(String expectedErrorMessage) {

      Assert.assertEquals(expectedErrorMessage,smartbearLoginPage.errorMsj.getText());
    }
    @Given("user clicks on Order tab")
    public void user_clicks_on_order_tab() {
     smartBearHomePage.orderTab.click();

    }
    @When("user selects product {string} and quantity {int}")
    public void user_selects_product_and_quantity(String product, Integer quantity) {
        BrowserUtils.selectOptionByValue(smartbearOrderPage.productSelect,product);
        smartbearOrderPage.quantityInput.sendKeys(quantity.toString());
        smartbearOrderPage.calculateBtn.click();
    }
    @Then("user validates the price is calculated correctly for quantity {int}")
    public void user_validates_the_price_is_calculated_correctly_for_quantity(Integer quantity) {

        int priceInt = Integer.parseInt(smartbearOrderPage.priceInput.getAttribute("value"));
        int discountInt=Integer.parseInt(smartbearOrderPage.discountInput.getAttribute("value"));
        int actualTotal=Integer.parseInt(smartbearOrderPage.total.getAttribute("value"));
        System.out.println(priceInt+", "+discountInt+", "+actualTotal);

        int expectedTotal;
        if(quantity<10){
            expectedTotal=priceInt*quantity;
        }else {
            expectedTotal=(priceInt-(priceInt*discountInt/100))*quantity;
        }

Assert.assertEquals(expectedTotal,actualTotal);
    }
    @When("user places an order with data and validates with success message {string}")
    public void user_places_an_order_with_data_and_validates_with_success_message(String successMessage, DataTable dataTable) {
      data = dataTable.asMaps(String.class, Object.class);
        for (int i = 0; i < data.size(); i++) {
            BrowserUtils.selectOptionByValue(smartbearOrderPage.productSelect, data.get(i).get("PRODUCT").toString());
            smartbearOrderPage.quantityInput.sendKeys(data.get(i).get("QUANTITY").toString());
            smartbearOrderPage.customerName.sendKeys(data.get(i).get("CUSTOMER NAME").toString());
            smartbearOrderPage.streetInput.sendKeys(data.get(i).get("STREET").toString());
            smartbearOrderPage.cityInput.sendKeys(data.get(i).get("CITY").toString());
            smartbearOrderPage.stateInput.sendKeys(data.get(i).get("STATE").toString());
            smartbearOrderPage.zipInput.sendKeys(data.get(i).get("ZIP").toString());
            switch (data.get(i).get("CARD").toString()) {
                case "Visa":
                    smartbearOrderPage.visaBtn.click();
                    break;
                case "Amex":
                    smartbearOrderPage.amexBtn.click();
                    break;
                case "Mastercard":
                    smartbearOrderPage.mastercardBtn.click();
                    break;
            }
            smartbearOrderPage.cardNumberInput.sendKeys(data.get(i).get("CARD NUM").toString());
            smartbearOrderPage.expDate.sendKeys(data.get(i).get("EXP DATE").toString());
            smartbearOrderPage.processBtn.click();
            Assert.assertEquals(successMessage, smartbearOrderPage.successMsj.getText());
        }




    }




    @Then("user validates the created order is in the list of all orders")
    public void user_validates_the_created_order_is_in_the_list_of_all_orders() {

smartbearOrderPage.viewOrdersTab.click();
Assert.assertEquals(data.get(2).get("CUSTOMER NAME"),smartBearHomePage.lastCustomerName.getText());



    }



}
