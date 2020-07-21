import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestBase;

import java.util.List;

/**
 *
 * Created by: Pretty.sanwale
 *
 * TestScenario 1:
 * 1. Open ​https://www.amazon.in/
 * 2. Enter username and password
 * 3. Enter Captcha and login
 *
 * TestScenario 2:
 * 1. Search for item
 * 2. Select the item
 * 3. add quantity more then 1
 * 4. check out
 *
 * TestScenario 3:
 * 1. On your order page provide all the details
 * 2. Select payment method as PayTM
 * 3. get the OTP
 * 4. Pay
 *
 *
 * ****/

public class Assignment2 extends TestBase {
        WebElement element;
        String filePath = "src/main/java/config/Data_v0.1.xlsx";
        int retry = 0;
        String watchName = "Daniel";
        String watchQuantity = "2";
        String userUpiId = "pretty@icic";

        @BeforeClass
        public void setUp(){
            initialize();
        }

        @AfterClass
        public void teardown() {
            driver.quit();
        }

        @DataProvider
        public Object[][] getLoginData(){
                Object[][] data = getExcelData(filePath,0);
                return data;
        }

        @Test(dataProvider = "getLoginData")
        public void Test01(String username, String password){
                System.out.println("--------------------Test01 started --------------------------");
                element = driver.findElement(By.xpath(signIn));
                mouseOver(element);
                driver.findElement(By.xpath(email)).sendKeys(username);
                driver.findElement(By.id(continuebutton)).click();
                driver.findElement(By.id(password1)).sendKeys(password);
                driver.findElement(By.id(login)).click();
                driver.findElement(By.id(searchBox)).sendKeys(item_text_to_search);
                driver.findElement(By.xpath(searchIcon)).click();
                List<WebElement> watchList = driver.findElements(By.xpath(watchimagelist));
                findItemInList(watchList, watchName);
                switchToWindow();
                element = driver.findElement(By.xpath(quantity));
                selectQuantity(element, watchQuantity);
                driver.findElement(By.xpath(buyNow)).click();
                driver.findElement(By.xpath(upiMethod)).isDisplayed();
                boolean flag = driver.findElement(By.xpath(upiMethod)).isSelected();
                if(flag != true){
                        driver.findElement(By.xpath(upiMethod)).click();
                }
                try {
                        driver.findElement(By.xpath(upiId)).sendKeys(userUpiId);
                        element = driver.findElement(By.xpath(verifyUpi));
                        clickOnElement(element);
                        wait(element, 5);
                        element = driver.findElement(By.xpath(UpiErrorMsg));
                        if(element.isDisplayed()==true){
                                System.out.println("Upi id is not correct");
                                driver.close();
                        }else{
                            driver.findElement(By.xpath(verified)).isDisplayed();
                            driver.findElement(By.xpath(submitOrderButtonid)).click();
                        }
                }catch (Exception e){
                }


        }
}


