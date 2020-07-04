package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.TestBase;

import java.util.ArrayList;
import java.util.List;

/**
 * created by : Pretty.sanwale
 *
 * TestScenario 2:
 * 1. Open ​http://automationpractice.com/
 * 2. Go to Popular items section
 * 3. Check for the number of items which have a discount if available and also verify that the
 * final price after discount is correct or not.
 *
 * ***/

public class UseCase02 extends TestBase {
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
        System.out.println("---------------------useCase02 started------------");
        try {
            element = driver.findElement(By.xpath(popular));
            if (!element.isSelected()) {
                element.click();
            }
            scrollToElement(element);

            List<WebElement> list = driver.findElements(By.xpath(Items));
            for(int i=1; i<=list.size(); i++) {
                try {
                    String originalPrice = driver.findElement(By.xpath(price1 + i + originalPriceSecondPart)).getText().substring(1);
                    String discountPercent = driver.findElement(By.xpath(price1+ i + discountPercentSecondPart)).getText();
                    String discountPercent1 = discountPercent.substring(1,discountPercent.length()-1);
                    String actualPrice = driver.findElement(By.xpath(price1 + i + actualPriceSecondPart)).getText().substring(1);

                    float originalPrice1 = Float.parseFloat(originalPrice);
                    float actualPrice1 = Float.parseFloat(actualPrice);
                    float discountPercent2 = Float.parseFloat(discountPercent1);

                    float expectedPrice = originalPrice1 - originalPrice1 * (discountPercent2/100);
                    if(expectedPrice == actualPrice1){
                        System.out.println("final price after discount is correct");
                    }else{
                        System.out.println("final price after discount is not correct");
                    }

                }catch (Exception e){
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
