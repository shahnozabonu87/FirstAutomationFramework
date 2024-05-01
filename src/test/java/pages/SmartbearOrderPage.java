package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SmartbearOrderPage {
    WebDriver driver;
    public SmartbearOrderPage (){
        driver= Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productSelect;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantityInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    public WebElement priceInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
    public WebElement discountInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
    public WebElement total;
    @FindBy(xpath = "//input[@value='Calculate']")
    public WebElement calculateBtn;
    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement streetInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement cityInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement stateInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_0")
    public WebElement visaBtn;
    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_1")
    public WebElement mastercardBtn;
    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_2")
    public WebElement amexBtn;
    @FindBy(xpath = "//input[@name='ctl00$MainContent$fmwOrder$TextBox6']")
    public WebElement cardNumberInput;
    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expDate;
    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processBtn;
    @FindBy(id = "strong")
    public WebElement successMsj;

    @FindBy(linkText = "View all orders")
    public WebElement viewOrdersTab;






}
