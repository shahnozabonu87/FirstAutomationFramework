package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ElarHomePage {
    WebDriver driver;
    public ElarHomePage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//*[@id=\"app\"]/aside/nav/ul[2]/li[2]/a")
    public WebElement companyBtn;

    @FindBy(xpath = "//a[@class='link-btm-menu']")
    public WebElement addCompanyBtn;




}
