package util.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestBase;

public class CheckoutPage extends TestBase {

    @FindBy(xpath = "//span[text()='Please enter valid Email ID/Mobile number']")
    public WebElement ErrorOnWrongMobileOrEmail;

    @FindBy(xpath = "//input[@type = 'text']")
    public WebElement EnterMobileNo;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement EnterPassword;

    @FindBy(xpath = "//span[text() = 'Your username or password is incorrect']")
    public WebElement WrongUserNamePsw;

    public void CheckoutPage()
    {
        PageFactory.initElements(driver,this);
    }
}
