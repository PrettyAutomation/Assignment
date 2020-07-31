package util.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestBase;

public class CartPage extends TestBase {

    @FindBy(xpath = "//span[@class='_35KyD6']")
    public WebElement CartPageNameXpath;

    @FindBy(xpath = "//div[@class= '_1vC4OE _3qQ9m1']")
    public WebElement CartPagePriceXpath;

    @FindBy(xpath = "//button[@type = 'button']")
    public WebElement BuyNow;

    @FindBy(xpath = "//span[text() = 'CONTINUE']")
    public WebElement Continue;

    @FindBy(xpath = "//div[text() = 'My Account']")
    public WebElement MyAccount;

    @FindBy(xpath = "//*[local-name() ='svg']/*[local-name()='path'][1]")
    public WebElement svgForLogout;

    @FindBy(xpath = "//span[text()='Logout']")
    public WebElement Logout;

    @FindBy(xpath = "//a[text() = 'Login']")
    public WebElement Login;

    public void CartPage()
    {
        PageFactory.initElements(driver,this);
    }
}
