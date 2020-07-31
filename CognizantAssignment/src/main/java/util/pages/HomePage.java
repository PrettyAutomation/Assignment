package util.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.TestBase;

import java.util.List;

public class HomePage extends TestBase {

    @FindBy(xpath = "//button[@class='_2AkmmA _29YdH8']")
    public  WebElement close;

    @FindBy(xpath = "//input[@title='Search for products, brands and more']")
    public  WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']")
    public  WebElement submit;

    @FindBy(xpath = "//img[@class='_1Nyybr _30XEf0']")
    public List<WebElement> listOfCameraResult;

    @FindBy(xpath = "//div[@class = '_1vC4OE _2rQ-NK']")
    public List<WebElement> listOfPrice;


    public void HomePage(){
        PageFactory.initElements(driver,this);
    }

}
