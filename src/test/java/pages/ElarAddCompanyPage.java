package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ElarAddCompanyPage {
    WebDriver driver;
    public ElarAddCompanyPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@name='company_name']")
    public WebElement nameField;
    @FindBy(xpath = "(//span[@class='input-errors'])[1]")
    public WebElement errorMsjReqField;
    @FindBy (xpath = "//span[ contains(text(),'Invalid input')][1]")
    public WebElement invalidInputErrorMsj;

    @FindBy(xpath = "//option[@value='broker company']")
    public WebElement brokerCompany;

    @FindBy(xpath = "//select[@name='company_type']")
    public WebElement companyType;

    @FindBy(xpath = "//select[@name='status']")
    public WebElement activeByDefaultStatus;

    @FindBy(xpath = "//select[@name='status']")
    public WebElement statusLocation;

    @FindBy(xpath = "(//input[@class='input-phone input-form border disabled-company'])[1]")
    public WebElement phoneField;

    @FindBy(xpath = "(//span[@class='input-errors'])[1]")
    public WebElement errorMsgMinLength;

    @FindBy(xpath = "(//input[@class='input-form input-ext border disabled-company'])[1]")
    public WebElement extPhoneField;

    @FindBy(xpath = "(//span[@class='input-errors'])[1]")
    public WebElement enterDigitsMsj;

    @FindBy(xpath = "(//input[@type='text'])[6]")
    public WebElement contactNameField;

    @FindBy(xpath = "//span[contains(text(),'This field is required.')]")
    public WebElement getErrorMsjReqField;

    @FindBy(xpath = "//span[contains(text(),'Invalid input')]")
    public WebElement invalidInput;

    @FindBy(xpath = "(//input[@class='input-form disabled-not-ext border disabled-company'])[1]")
    public WebElement faxField;

    @FindBy(xpath = "(//input[@class='input-form border disabled-yard'])[1]")
    public WebElement streetField;

   @FindBy(xpath = "(//input[@class='input-form border disabled-yard'])[2]")
    public WebElement aptField;


    @FindBy(xpath = "(//input[@class='input-form border disabled-yard'])[3]")
    public WebElement cityField;


}
