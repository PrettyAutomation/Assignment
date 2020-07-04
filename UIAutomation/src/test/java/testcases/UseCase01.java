package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.TestBase;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by: Pretty.sanwale
 *
 * TestScenario 1:
 * 1. Open ​http://automationpractice.com/
 * 2. Go to Popular items section
 * 3. Check items with the lowest price and add into the Cart. (Don't use any sorting function)
 * 4. Now browse to your Cart and take a screenshot of the product that got added to your Cart
 * 5. Verify that product is Successfully added.
 *
 * ****/

public class UseCase01 extends TestBase {
    WebElement element;

    @BeforeClass
    public void setUp(){
        initialize();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Test01(){
        try {

            System.out.println("---------------------useCase01 started------------");
            ArrayList<String> priceList = new ArrayList<String>();

            element = driver.findElement(By.xpath(popular));
            if (!element.isSelected()) {
                element.click();
            }
            scrollToElement(element);

            // Get the list of prices of items present on the popular section
            List<WebElement> list = driver.findElements(By.xpath(Items));
            for (int i = 1; i <= list.size(); i++) {
                try {

                    String OriginalValue = driver.findElement(By.xpath(price1 + i + actualPriceSecondPart)).getText().substring(1);
                    priceList.add(OriginalValue);
                }catch (Exception e){

                }
            }

            //To get the Lowest price item
            String firsItemPrice = driver.findElement(By.xpath(price1 + 1 + actualPriceSecondPart)).getText().substring(1);
            float minPriceItem = Float.parseFloat(firsItemPrice);
            int count = lowestPriceElement(minPriceItem,priceList);

            //select the lowest price item
            driver.findElement(By.xpath(price1 + count + itemSecondPart)).click();

            switchToWindow();
            pauseMe(2000);

            //Check if item is added to cart successfully
                if(driver.getPageSource().contains("Product successfully added to your shopping cart")){
                    System.out.println("Product is successfully added");
                }else {
                    System.out.println("Product is successfully not added");
                }
                driver.findElement(By.xpath(closeWindow)).click();
                driver.findElement(By.xpath(viewShoppingCart)).click();
                takeScreenshot();
                Assert.assertFalse(driver.findElement(By.xpath(emptyCart)).isDisplayed());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
