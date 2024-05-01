package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PorscheHomePage {
    WebDriver driver;
    public PorscheHomePage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "(//input[@name='Cayman'])[2]")
    public WebElement caymanCheckbox;
    @FindBy(xpath = "(//p-heading[contains(text(),'718 Cayman')])[1]//following-sibling::p-text")
    public WebElement caymanPrice;
    @FindBy(xpath = "//p-icon[@id='model-type-arrow-icon-to-configurator-CAYMAN_982120']")
    public WebElement caymanClick;


}
