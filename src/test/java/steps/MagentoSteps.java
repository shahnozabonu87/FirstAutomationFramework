package steps;



import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MagentoHomePage;
import pages.MagentoSearchResultPage;
import pages.MagentoWhatsNewPage;
import pages.MagentoWomenJacketPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class MagentoSteps {
    WebDriver driver= Driver.getDriver();

    MagentoHomePage magentoHomePage = new MagentoHomePage();
    MagentoSearchResultPage searchResultsPageMagento=new MagentoSearchResultPage();
    MagentoWhatsNewPage magentoWhatsNewPage =new MagentoWhatsNewPage();
    MagentoWomenJacketPage magentoWomenJacketPage = new MagentoWomenJacketPage();


    @Given("user navigates to magento application")
    public void user_navigates_to_magento_application() {
      driver.get(ConfigReader.getProperty("magentoUrl"));

    }
    @When("user searches for keyword {string}")
    public void user_searches_for_keyword(String keyword) {
    magentoHomePage.searchBox.sendKeys(keyword+ Keys.ENTER);
    BrowserUtils.scrollByPixels(3000);
        BrowserUtils.selectOptionByValue(searchResultsPageMagento.limiter,"24");
    }
    @Then("user validates search result contains")
    public void user_validates_search_result_contains(DataTable dataTable) {
        MagentoSearchResultPage obj = new MagentoSearchResultPage();
        driver.navigate().refresh();
    List<String> keywords=dataTable.asList();

    for(WebElement item: searchResultsPageMagento.items){
        String itemDescription = item.getText();
        boolean isFound=false;
        String wordNotFound="";
        for(int i=0;i<keywords.size();i++){
            if(itemDescription.toLowerCase().contains(keywords.get(i).toLowerCase())){
                isFound =true;
                break;
            }else {
              wordNotFound=keywords.get(i);

            }
        }
        Assert.assertTrue("["+itemDescription+"] does not contain keyword ["+wordNotFound+"]",isFound);
    }
    }
    @When("user searches for women's jacket")
    public void user_searches_for_women_s_jacket() {
      magentoHomePage.whatsNewTab.click();
      magentoWhatsNewPage.jacket.click();
    }
    @When("user selects price range from fifty to sixty dollars")
    public void user_selects_price_range_from_fifty_to_sixty_dollars() {
      magentoWomenJacketPage.priceFilterTab.click();
      magentoWomenJacketPage.fiftySixtyRange.click();
    }

    @Then("user validates items prices are within {double} and {double} dollars")
    public void user_validates_items_prices_are_within_and_dollars(Double priceMin, Double priceMax) {

        MagentoWomenJacketPage obj = new MagentoWomenJacketPage();
        for(WebElement itemPrice : obj.itemPrices){
            double priceDouble= Double.parseDouble(itemPrice.getText().replace("$",""));
            Assert.assertTrue(priceMin<=priceDouble && priceDouble<=priceMax);
        }

    }

    @When("user clicks on {string} tab")
    public void user_clicks_on_tab(String tab) {
        switch (tab){
            case "What's New":
                magentoHomePage.whatsNewTab.click();
                break;
            case "Women":
                magentoHomePage.womenTab.click();
                break;
            case "Gear":
                magentoHomePage.gearTab.click();
                break;
            case "Men":
                magentoHomePage.menTab.click();
                break;
            case "Training":
                magentoHomePage.trainingTab.click();
                break;
            case "Sale":
                magentoHomePage.saleTab.click();
                break;
        }
    }
    @Then("user validates the {string} title")
    public void user_validates_the_title(String title) {
      Assert.assertEquals("Title is incorrect",title,driver.getTitle());



    }



}
