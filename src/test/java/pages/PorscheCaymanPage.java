package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PorscheCaymanPage {
    WebDriver driver;
    public PorscheCaymanPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[contains(@class,'text-md text-')]")
    public WebElement caymanModelPrice;
    @FindBy(xpath = "(//input[@name='options-IIR'])[2]")
    public WebElement powerSeatBtn;

    @FindBy(xpath = "//p[@class='text-contrast-medium m-0 font-semibold']")
    public WebElement powerSeatPrice;

    @FindBy(xpath = "(//div[contains(@class,'text-md text-')])[2]")
    public WebElement updatedCaymanModelPrice;

}
