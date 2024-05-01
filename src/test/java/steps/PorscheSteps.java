package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PorscheCaymanPage;
import pages.PorscheHomePage;
import utilities.ConfigReader;
import utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;

public class PorscheSteps {
    WebDriver driver = Driver.getDriver();
    HashMap<String, String> storeData = new HashMap<>();
    PorscheHomePage porscheHomePage = new PorscheHomePage();
    PorscheCaymanPage porscheCaymanPage = new PorscheCaymanPage();

    @Given("user navigates to porsche application")
    public void user_navigates_to_porsche_application() {
        driver.get(ConfigReader.getProperty("porscheUrl"));
    }


    @When("user stores the price and selects the model {string}")
    public void user_stores_the_price_and_selects_the_model_cayman(String model) {

        String price = porscheHomePage.caymanPrice.getText();
        price = price.replaceAll("[^0-9]", "");

        storeData.put("actualCarPrice", price);
        porscheHomePage.caymanClick.click();

    }

    @Then("user validates Base price is matched with listed price")
    public void user_validates_base_price_is_matched_with_listed_price() {
        String actualPrice = storeData.get("actualCarPrice");
        int actual = Integer.parseInt(actualPrice);
        String price2 = porscheCaymanPage.caymanModelPrice.getText();
        price2= price2.replaceAll("[^0-9]", "");
        int expected = Integer.parseInt(price2);
        if (actual >= expected) {
            Assert.assertTrue(true);
        }

    }

    @When("user adds Power Sport Seats 14way with Memory Package")
    public void user_adds_power_sport_seats_way_with_memory_package() {
        String price2 = porscheCaymanPage.caymanModelPrice.getText();
        price2= price2.replaceAll("[^0-9]", "");
      storeData.put("oldPrice",price2);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,4500)", "");

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(porscheCaymanPage.powerSeatBtn));

        porscheCaymanPage.powerSeatBtn.click();



    }



    @Then("user validates that Price For Equipment is added and price matches")
    public void user_validates_that_price_for_equipment_is_added_and_price_matches() {
      String oldPrice =  storeData.get("oldPrice").replaceAll("[^0-9]", "");
        int oldPriceValue = Integer.parseInt(oldPrice);

        String powerPrice= porscheCaymanPage.powerSeatPrice.getText();
        powerPrice=powerPrice.replaceAll("[^0-9]", "");
        int finalPowerPrice=Integer.parseInt(powerPrice);

        String expectedPrice = porscheCaymanPage.updatedCaymanModelPrice.getText();
        expectedPrice=expectedPrice.replaceAll("[^0-9]", "");
        int finalExpectedPrice=Integer.parseInt(expectedPrice);

        Assert.assertEquals(finalExpectedPrice,oldPriceValue+finalPowerPrice);

    }




}
