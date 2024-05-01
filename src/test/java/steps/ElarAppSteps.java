package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.ElarAddCompanyPage;
import pages.ElarHomePage;
import pages.ElarLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElarAppSteps {
    WebDriver driver= Driver.getDriver();
    ElarLoginPage elarLoginPage = new ElarLoginPage();
    ElarHomePage elarHomePage = new ElarHomePage();
    ElarAddCompanyPage elarAddCompanyPage = new ElarAddCompanyPage();
    public static HashMap<String, Integer> dataStore = new HashMap<>();
    public static HashMap<String, String> dataStore1 = new HashMap<>();
    int length = 0;

    @Given("User navigates to Elar App Application")
    public void user_navigates_to_elar_app_application() {
       driver.get(ConfigReader.getProperty("elarappUrl"));
    }

    @When("user inputs  username {string} and password {string}")
    public void user_inputs_username_and_password(String username, String password) {
      elarLoginPage.usernameInputBox.sendKeys(username);
      elarLoginPage.passwordInputBox.sendKeys(password);
      elarLoginPage.loginBtn.click();
    }


    @When("user clicks on company list button")
    public void user_clicks_on_company_list_button() {
        elarHomePage.companyBtn.click();

    }
    @When("user adds the company")
    public void user_adds_the_company() {
       elarHomePage.addCompanyBtn.click();
    }
    @When("user inputs  fifty characters {string}  in the name field")
    public void user_inputs_fifty_characters_in_the_name_field(String value) {
     elarAddCompanyPage.nameField.sendKeys(value);
     length=value.length();
    // dataStore.put("nameFieldLength",value.length());
    }

    @When("user clears the field")
    public void user_clears_the_field() {
    //    String value = "1234567890abcdefghijklmnopqrstuvwxyz|||||--&&&1234";

      //  int nameField = dataStore.get("nameFieldLength");
        for (int i=0; i< length; i++)
        {
            elarAddCompanyPage.nameField.sendKeys(Keys.BACK_SPACE);
        }

    }



//    @When("user inputs {string} characters in the name field")
//    public void user_inputs_characters_in_the_name_field(String value) {
//
//        elarAddCompanyPage.nameField.sendKeys(value);
//        dataStore.put("nameFieldLength",value.length());
//
//    }


    @Then("user validates the inline error message")
    public void user_validates_the_inline_error_message() {
      Assert.assertEquals("This field is required.",elarAddCompanyPage.errorMsjReqField.getText());
    }
    @When("user inputs fifty one characters {string}   in the name field")
    public void user_inputs_fifty_one_characters_in_the_name_field(String value) {
     elarAddCompanyPage.nameField.sendKeys(value);

    }



    @When("user inputs {string}  characters in the name field")
    public void user_inputs_characters_in_the_name_field(String value51) {

        elarAddCompanyPage.nameField.sendKeys(value51);
    }

    @Then("user validates the name field accepts only {string} characters")
    public void user_validates_the_name_field_accepts_only_characters(String value50) {
        elarAddCompanyPage.nameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(value50,elarAddCompanyPage.nameField.getAttribute("value"));
    }
    @When("user inputs {string}  in the name field")
    public void user_inputs_in_the_name_field(String value) {
        elarAddCompanyPage.nameField.sendKeys(value);

    }
    @Then("user validates the name field accepts provided {string}")
    public void user_validates_the_name_field_accepts_provided(String expectedValue) {
        elarAddCompanyPage.nameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expectedValue,elarAddCompanyPage.nameField.getAttribute("value"));

    }
    @When("user inputs valid characters as {string} in the name field")
    public void user_inputs_valid_characters_as_in_the_name_field(String value) {
       elarAddCompanyPage.nameField.sendKeys(value);
    }

    @When("user hover over the cursor on the name field")
    public void user_hover_over_the_cursor_on_the_name_field() {
        Actions action = new Actions(driver);
        action.moveToElement(elarAddCompanyPage.nameField);
        String actualRes = elarAddCompanyPage.nameField.getCssValue("cursor");
        dataStore1.put("text",actualRes );

    }

    @Then("user validates the cursor changes to text selection")
    public void user_validates_the_cursor_changes_to_text_selection() {
        Assert.assertEquals("text","text");
    }


    @When("user inputs invalid text {string}")
    public void user_inputs_invalid_text(String value) {
        elarAddCompanyPage.nameField.sendKeys(value);
    }




    @Then("user expecting error message {string}")
    public void user_expecting_error_message(String expectedText) {
        Assert.assertEquals(expectedText,elarAddCompanyPage.invalidInputErrorMsj.getText());
    }


    @Then("user verifies Company type  field is {string} by default")
    public void verify_company_type_field_is_by_default(String expected) {
    Assert.assertEquals(expected,elarAddCompanyPage.brokerCompany.getText());
    }

    @Then("user clicks on  drop down  in Company type field")
    public void user_clicks_on_drop_down_in_company_type_field() {
      elarAddCompanyPage.companyType.click();
    }




    @Then("user validates after clicking drop down {string} , {string} and {string} status pop up")
    public void user_validates_after_clicking_drop_down_carrier_company_and_status_pop_up(String expected1, String expected2,String expected3) {

        WebElement statusDropDown = elarAddCompanyPage.companyType;
        Select select = new Select(statusDropDown);


        List<WebElement> status= select.getOptions();
        List<String> actualStatuses = new ArrayList<>();
        for( WebElement statusName:status)
        {
            actualStatuses.add(statusName.getText());
        }
        System.out.println(actualStatuses);
        List<String> expectedlStatuses = new ArrayList<>();
        expectedlStatuses.add(expected1);
        expectedlStatuses.add(expected2);
        expectedlStatuses.add(expected3);
        System.out.println(expectedlStatuses);
        Assert.assertEquals(expectedlStatuses,actualStatuses);

    }





    @Then("user clicks on {string} status")
    public void user_clicks_on_status(String company) {
        WebElement statusDropDown = elarAddCompanyPage.companyType;
        Select select = new Select(statusDropDown);
        String brokerCompanyByDefault= elarAddCompanyPage.brokerCompany.getText();
        select.selectByValue("carrier company");
        String carrierCompanyByDefault = select.getFirstSelectedOption().getText();
        select.selectByValue("broker+Carrier company");
        String brokerCarrierCompanyByDefault=select.getFirstSelectedOption().getText();
        select.selectByValue("broker company");
        String brokerCompanyByDefault2= select.getFirstSelectedOption().getText();
    }
    @Then("user validates status of field are switching Broker company to Carrier company,Carrier company to Broker+Carrier company")
    public void user_validates_status_of_field_are_switching_broker_company_to_carrier_company_carrier_company_to_broker_carrier_company() {

    }
    @Then("user verifies status field is required and active by default")
    public void user_verifies_status_field_is_required_and_active_by_default() {
        WebElement statusDropDown = elarAddCompanyPage.statusLocation;
        Select select = new Select(statusDropDown);
        String actualActiveByDefault=select.getFirstSelectedOption().getText();
        Assert.assertEquals("Active",actualActiveByDefault);
    }

    @Then("user clicks on drop down in status field")
    public void user_clicks_on_drop_down_in_status_field() {

        elarAddCompanyPage.activeByDefaultStatus.click();

    }
    @Then("user validates after clicking drop down {string} and {string} status pop up")
    public void user_validates_after_clicking_drop_down_and_status_pop_up(String expected1, String expected2) {
        WebElement statusDropDown = elarAddCompanyPage.activeByDefaultStatus;
        Select select = new Select(statusDropDown);

        List<WebElement> status= select.getOptions();
        List<String> actualStatuses = new ArrayList<>();
        for( WebElement statusName:status)
        {
            actualStatuses.add(statusName.getText());
        }
        List<String> expectedlStatuses = new ArrayList<>();
        expectedlStatuses.add(expected1);
        expectedlStatuses.add(expected2);
        Assert.assertEquals(expectedlStatuses,actualStatuses);
    }

    @Then("user validates green color for active and red color for non-active")
    public void user_validates_green_color_for_active_and_red_color_for_non_active() {
        WebElement statusDropDown = elarAddCompanyPage.statusLocation;
        Select select = new Select(statusDropDown);

        WebElement selectedOption=  select.getFirstSelectedOption();// active = green
        String activeByDefault=  selectedOption.getText();

        String activeActualColor = selectedOption.getCssValue("color");
        String expectedActiveColor = "rgba(52, 195, 143, 1)";

        Assert.assertEquals(expectedActiveColor,activeActualColor);

        select.selectByValue("non-active");
        WebElement nonActive=  select.getFirstSelectedOption();
        String nonactiveActualColor = nonActive.getCssValue("color");
        String expectedNonactive = "rgba(244, 106, 106, 1)";
        Assert.assertEquals(expectedNonactive,nonactiveActualColor);

    }
    @Then("user validates status field are switching {string} to {string}")
    public void user_validates_status_field_are_switching_to(String active,String nonActive) {
        WebElement statusDropDown = elarAddCompanyPage.statusLocation;
        Select select = new Select(statusDropDown);
        String activeByDefault= elarAddCompanyPage.activeByDefaultStatus.getText();
        select.selectByValue("non-active");
        String nonActiveByDefault = select.getFirstSelectedOption().getText();
        Assert.assertNotEquals(nonActiveByDefault,activeByDefault);
        select.selectByValue("active");
        String activeByDefault2=  select.getFirstSelectedOption().getText();
        Assert.assertNotEquals(activeByDefault2,nonActiveByDefault);


    }
    @When("user hovers over the cursor on the status field")
    public void user_hovers_over_the_cursor_on_the_status_field() {
        WebElement statusDropDown = elarAddCompanyPage.statusLocation;
        Actions action = new Actions(driver);
        action.moveToElement(statusDropDown);

    }

    @Then("user validates hover over the cursor on status field is cursor hand")
    public void user_validates_hover_over_the_cursor_on_status_field_is_cursor_hand() {
        WebElement statusDropDown = elarAddCompanyPage.statusLocation;
        Assert.assertEquals(statusDropDown.getCssValue("cursor"),"pointer");

    }
    @When("user inputs more than twelve  characters {string} in the phone field")
    public void user_inputs_more_than_twelve_characters_in_the_phone_field(String value) {
        elarAddCompanyPage.phoneField.sendKeys(value);

    }

    @Then("user validates phone field accepts only {string} with two dashes")
    public void user_validates_phone_field_accepts_only_with_two_dashes(String expectedText) {
        elarAddCompanyPage.phoneField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expectedText,elarAddCompanyPage.phoneField.getAttribute("value"));


    }
    @When("user hovers over the cursor on the phone field")
    public void user_hovers_over_the_cursor_on_the_phone_field() {

        Actions action = new Actions(driver);
        action.moveToElement(elarAddCompanyPage.phoneField);
//        String actualRes = elarAddCompanyPage.phoneField.getCssValue("cursor");
//        dataStore1.put("text",actualRes );

    }



    @Then("user validates cursor icon change to text selection")
    public void user_validates_cursor_icon_change_to_text_selection() {
        Assert.assertEquals("text",elarAddCompanyPage.phoneField.getCssValue("cursor"));

    }
    @When("user inputs less then twelve characters {string} in the phone field")
    public void user_inputs_less_then_twelve_characters_in_the_phone_field(String value) {

        elarAddCompanyPage.phoneField.sendKeys(value);

    }


    @When("user inputs {string} in the phone field")
    public void user_inputs_in_the_phone_field(String value) {
        elarAddCompanyPage.phoneField.sendKeys(value);
    }




    @Then("user validates error message {string} under phone field")
    public void user_validates_error_message_under_phone_field(String expectedErrorMsg) {
        if(expectedErrorMsg.equals("Min length is 12 characters, currently it is 11.")) {
            Assert.assertEquals(expectedErrorMsg, elarAddCompanyPage.errorMsgMinLength.getText());
        }else{
            Assert.assertEquals(expectedErrorMsg, elarAddCompanyPage.enterDigitsMsj.getText());
        }
    }






    @When("user clears the phone field")
    public void user_clears_the_phone_field() throws InterruptedException {
        //    String value = "1234567890abcdefghijklmnopqrstuvwxyz|||||--&&&1234";

        //  int nameField = dataStore.get("nameFieldLength");
        elarAddCompanyPage.phoneField.click();
        for (int i=0; i< length; i++)
        {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
            Thread.sleep(1000);
            actions.sendKeys(Keys.DELETE).build().perform();

//          elarAddCompanyPage.phoneField.sendKeys(Keys.BACK_SPACE);
//            elarAddCompanyPage.phoneField.sendKeys(Keys.DELETE);
        }

    }



    @Then("user validates error message {string}")
    public void user_validates_error_message(String expectedErrorMsg) {

        Assert.assertEquals(expectedErrorMsg,elarAddCompanyPage.getErrorMsjReqField.getText());


    }

    @When("user inputs twelve characters {string}")
    public void user_inputs_twelve_characters(String value) {
      elarAddCompanyPage.phoneField.sendKeys(value);
        length=value.length();
    }


    @When("user input ten digits {string} in the Ext\\(Phone) field")
    public void user_input_ten_digits_in_the_ext_phone_field(String value) {
        elarAddCompanyPage.phoneField.sendKeys("123-456-7890");
        elarAddCompanyPage.extPhoneField.sendKeys(value);

    }



    @Then("user validates Ext\\(Phone) field accepting ten digits {string}")
    public void user_validates_ext_phone_field_accepting_ten_digits(String expectedText) {
        elarAddCompanyPage.extPhoneField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expectedText,elarAddCompanyPage.phoneField.getAttribute("value"));
    }
    @When("user inputs any letter {string} in the ext\\(phone) field")
    public void user_inputs_any_letter_in_the_ext_phone_field(String s) {
        elarAddCompanyPage.phoneField.sendKeys("123-456-7890");
        elarAddCompanyPage.extPhoneField.sendKeys(s);

    }



    @Then("user validates Ext\\(Phone) field error message {string}")
    public void user_validates_ext_phone_field_error_message(String expected) {
    Assert.assertEquals(expected,elarAddCompanyPage.enterDigitsMsj.getText());
    }
    @When("user input more than thirty five characters {string} in the Contact name\\(Phone ) field")
    public void user_input_more_than_thirty_five_characters_in_the_contact_name_phone_field(String s) {
        elarAddCompanyPage.phoneField.sendKeys("123-456-7890");
       elarAddCompanyPage.contactNameField.sendKeys(s);

    }




    @Then("user validates Contact name\\(Phone) field accepts only thirty five {string} characters")
    public void user_validates_contact_name_phone_field_accepts_only_thirty_five_characters(String expectedText) {
        elarAddCompanyPage.contactNameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expectedText,elarAddCompanyPage.contactNameField.getAttribute("value"));
    }


    @When("user input valid characters {string} in the Contact name\\(Phone) field")
    public void user_input_valid_characters_in_the_contact_name_phone_field(String s) {
        elarAddCompanyPage.phoneField.sendKeys("123-456-7890");
        elarAddCompanyPage.contactNameField.sendKeys(s);

    }



    @Then("user validate Contact name\\(Phone) field is accepting valid characters {string}")
    public void user_validate_contact_name_phone_field_is_accepting_valid_characters(String expected) {

        elarAddCompanyPage.contactNameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expected,elarAddCompanyPage.contactNameField.getAttribute("value"));
    }

    @When("user input characters  {string} in the Contact name\\(Phone) field")
    public void user_input_characters_in_the_contact_name_phone_field(String s) {
        elarAddCompanyPage.phoneField.sendKeys("1234567890");
        elarAddCompanyPage.contactNameField.sendKeys(s);

    }
    @Then("user validates Contact name\\(Phone) field error message {string}")
    public void user_validates_contact_name_phone_field_error_message(String expected) {

        Assert.assertEquals(expected,elarAddCompanyPage.invalidInput.getText());

    }
    @When("user hovers over the cursor on   Contact name\\(Phone) field")
    public void user_hovers_over_the_cursor_on_contact_name_phone_field() {

        Actions action = new Actions(driver);
        action.moveToElement(elarAddCompanyPage.contactNameField);

    }
    @When("user inputs data {string} in the Fax field")
    public void user_inputs_data_in_the_fax_field(String v) {
     elarAddCompanyPage.faxField.sendKeys(v);


    }
    @Then("user validates Fax field accepting twelve characters {string}")
    public void user_validates_fax_field_accepting_twelve_characters(String expected) {
        elarAddCompanyPage.faxField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expected,elarAddCompanyPage.faxField.getAttribute("value"));


    }
    @When("user inputs nine digits {string} on the Fax field")
    public void user_inputs_nine_digits_on_the_fax_field(String v) {
      elarAddCompanyPage.faxField.sendKeys(v);
    }


    @Then("user validates error message {string} for Fax field")
    public void user_validates_error_message_for_fax_field(String expected) {

   Assert.assertEquals(expected,elarAddCompanyPage.errorMsgMinLength.getText());
    }
    @When("user hovers over the cursor on the Fax field")
    public void user_hovers_over_the_cursor_on_the_fax_field() {

       Actions actions = new Actions(driver) ;
       actions.moveToElement(elarAddCompanyPage.faxField);

    }

    @When("user inputs more than thirty five characters {string} in the street field")
    public void user_inputs_more_than_thirty_five_characters_in_the_street_field(String v) {

         elarAddCompanyPage.streetField.sendKeys(v);

    }
   /* @Then("user validates street field accepts only thirty five {string} characters")
    public void user_validates_street_field_accepts_only_thirty_five_characters(String expected) {
        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expected,elarAddCompanyPage.streetField.getAttribute("value"));
    }*/


    @Then("user validates street field accepts {string} all valid  characters")
    public void user_validates_street_field_accepts_all_valid_characters(String expected) {
        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expected,elarAddCompanyPage.streetField.getAttribute("value"));
    }


    @When("user inputs valid characters {string} in the street field")
    public void user_inputs_valid_characters_in_the_street_field(String v) {

        elarAddCompanyPage.streetField.sendKeys(v);
    }
    @Then("user validates street field accepts all valid {string} characters")
    public void user_validates_street_field_accepts_all_valid_character_values(String expected) {

        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(expected,elarAddCompanyPage.streetField.getAttribute("value"));

    }

    @When("user inputs data {string} in the street field")
    public void user_inputs_data_in_the_street_field(String v) {
length =v.length();
      elarAddCompanyPage.streetField.sendKeys(v);
    }

    @When("user clears the street field")
    public void user_clears_the_street_field() throws InterruptedException {

        elarAddCompanyPage.streetField.click();
        for (int i = 0; i < length; i++) {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
            Thread.sleep(1000);
            actions.sendKeys(Keys.DELETE).build().perform();

        }
    }
    @Then("user validates street field  error message {string}")
    public void user_validates_street_field_error_message(String ex) {

    Assert.assertEquals(ex,elarAddCompanyPage.errorMsgMinLength.getText());


   }

    @When("user inputs valid characters with add sign {string} sign  in the street field")
    public void user_inputs_valid_characters_with_add_sign_abc_sign_in_the_street_field(String s) {

    elarAddCompanyPage.streetField.sendKeys(s);

    }
    @When("user hovers over the cursor on the street field")
    public void user_hovers_over_the_cursor_on_the_street_field() {
       Actions actions = new Actions(driver);
       actions.moveToElement(elarAddCompanyPage.streetField);

    }
    @Then("user validates cursor changes to text selection on the street field")
    public void user_validates_cursor_changes_to_text_selection_on_the_street_field() {
        Assert.assertEquals("text",elarAddCompanyPage.streetField.getCssValue("cursor"));
    }


    @When("user inputs data with invalid characters {string} in the street field")
    public void user_inputs_data_with_invalid_characters_in_the_street_field(String s) {
      elarAddCompanyPage.streetField.sendKeys(s);

    }



    @Then("user validates street field should not accept  invalid characters")
    public void user_validates_street_field_should_not_accept_invalid_characters() {
        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals("Invalid input",elarAddCompanyPage.streetField.getAttribute("value"));
    }

    @When("user inputs data with invalid characters {string} in the  Apt,suite,company,c\\/o field")
    public void user_inputs_data_with_invalid_characters_in_the_apt_suite_company_c_o_field(String s) {
        elarAddCompanyPage.aptField.sendKeys(s);


    }


    @Then("user validates Apt,suite,company,c\\/o field should not accept  invalid characters")
    public void user_validates_apt_suite_company_c_o_field_should_not_accept_invalid_characters() {
        Assert.assertEquals("Invalid input",elarAddCompanyPage.streetField.getAttribute("value"));
    }


    @When("user inputs valid characters {string} in the Apt,suite,company,c\\/o field")
    public void user_inputs_valid_characters_in_the_apt_suite_company_c_o_field(String value) {
        elarAddCompanyPage.aptField.sendKeys(value);
    }


    @Then("user validates Apt,suite,company,c\\/o field accepts {string} all valid  characters")
    public void user_validates_apt_suite_company_c_o_field_accepts_all_valid_characters(String ex) {
        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        Assert.assertEquals(ex,elarAddCompanyPage.aptField.getAttribute("value"));
    }

    @When("user inputs data with invalid character {string} in the  Apt,suite,company,c\\/o field")
    public void user_inputs_data_with_invalid_character_in_the_apt_suite_company_c_o_field(String s) {
      elarAddCompanyPage.aptField.sendKeys(s);
    }


    @Then("user validates  Apt,suite,company,c\\/o field error message {string}")
    public void user_validates_apt_suite_company_c_o_field_error_message(String expected) {
        Assert.assertEquals(expected,elarAddCompanyPage.invalidInputErrorMsj.getText());
    }

    @When("user hovers over the cursor on the Apt,suite,company,c\\/o field")
    public void user_hovers_over_the_cursor_on_the_apt_suite_company_c_o_field() {
       Actions actions = new Actions(driver);
       actions.moveToElement(elarAddCompanyPage.aptField);
    }



    @Then("user validates cursor changes to text selection on the Apt,suite,company,c\\/o field")
    public void user_validates_cursor_changes_to_text_selection_on_the_apt_suite_company_c_o_field() {
      Assert.assertEquals("text",elarAddCompanyPage.aptField.getCssValue("cursor"));
    }

    @When("user inputs more than twenty eight characters {string} in the City field")
    public void user_inputs_more_than_twenty_eight_characters_in_the_city_field(String s) {
        elarAddCompanyPage.cityField.sendKeys(s);
    }



    @Then("user validates City field should accept only twenty eight {string} characters")
    public void user_validates_city_field_should_accept_only_twenty_eight_characters(String ex) {
        elarAddCompanyPage.streetField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
   Assert.assertEquals(ex,elarAddCompanyPage.cityField.getAttribute("value"));
    }
    @When("user clears the city field")
    public void user_clears_the_city_field() throws InterruptedException {
        elarAddCompanyPage.cityField.click();
        for (int i = 0; i < length; i++) {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.BACK_SPACE).build().perform();
            Thread.sleep(1000);
            actions.sendKeys(Keys.DELETE).build().perform();

        }
    }
    @When("user inputs data {string} in the City field")
    public void user_inputs_data_in_the_city_field(String s) {
        elarAddCompanyPage.cityField.sendKeys(s);
    }
    @Then("user validates error message {string} for city field")
    public void user_validates_error_message_for_city_field(String expected) {
        Assert.assertEquals(expected,elarAddCompanyPage.invalidInput.getText());

    }

    @When("user hovers over the cursor on the City field")
    public void user_hovers_over_the_cursor_on_the_city_field() {
        Actions actions = new Actions(driver);
        actions.moveToElement(elarAddCompanyPage.cityField);
    }



    @Then("user validates cursor changes to text selection on the City field")
    public void user_validates_cursor_changes_to_text_selection_on_the_city_field() {
       Assert.assertEquals("text",elarAddCompanyPage.cityField.getCssValue("cursor"));
    }

    @When("user clears the Zip code field")
    public void user_clears_the_zip_code_field() {

    }


}