import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestBase;
import java.util.List;
import static org.testng.Assert.*;


/*
• Open the Flipkart portal from the link https://www.flipkart.com
• “Login to Flipkart portal and search for an item and add to cart and purchase it and logout”
• Details of data to be tested: search for a Camera and purchase a random (not the first/last) Camera from the search result
• Verify information (name/price/description...) on product search screen, comparing that info between product search screen vs checkout screen
• Must use assertion during verification
• TC must pass/fail base on assertion
• Handle screen rotation efficiently.
*/

public class FlipkartTest01 extends TestBase {

        public WebElement elem;

        @BeforeTest
        public void loginWeb(){
            initialize();
        }

        @AfterTest
        public void tearDown(){
            driver.quit();
        }

        @DataProvider
        public Object[][] getLoginData(){
            Object [][] data = getExcelData(ExcelPath, 0);
            return data;
        }

        @Test (dataProvider = "getLoginData")
        public void searchCamera(String emailOrMobile, String password) throws InterruptedException {

                switchToWindow();
                driver.findElement(By.xpath(close)).click();
                elem = driver.findElement(By.xpath(searchBox));
                assertNotNull(elem);
                elem.sendKeys(prop.getProperty(searchItem));
                elem = driver.findElement(By.xpath(submit));
                elem.click();

                scrollToBottom();
                driver.navigate().refresh();
                pauseMe(2);

                List<WebElement> cameraList = driver.findElements(By.xpath(listOfCameraResult));
                int cameraListSize = cameraList.size();

                List<WebElement> priceList = driver.findElements(By.xpath(listOfPrice));
                int priceListSize = priceList.size();


                List<String> itemDetail = null;
                if(cameraListSize > 2 && priceListSize>1){
                    itemDetail = findItemInList(cameraList,priceList,prop.getProperty(perticularCameraItem));
                    Assert.assertNotNull(itemDetail);
                }

                switchToWindow();

                String CartPageCameraName = driver.findElement(By.xpath(CartPageNameXpath)).getText();
                String CartPagePrice = driver.findElement(By.xpath(CartPagePriceXpath)).getText();

                Assert.assertTrue(CartPageCameraName.contains(itemDetail.get(0)));
                Assert.assertEquals(itemDetail.get(1), CartPagePrice);

                try {
                      takeScreenshot();
                      driver.findElement(By.xpath(BuyNow)).click();
                      elem = driver.findElement(By.xpath(Continue));
                      Assert.assertNotNull(elem);
                }catch (Exception e){
                     e.printStackTrace();
                     System.out.println("issue with product availability");
                }

                switchToWindow();
                dismissAlert();

                driver.findElement(By.xpath(Continue)).click();
                Assert.assertTrue(driver.findElement(By.xpath(ErrorOnWrongMobileOrEmail)).isDisplayed());

                driver.findElement(By.xpath(EnterMobileNo)).sendKeys(emailOrMobile);
                driver.findElement(By.xpath(Continue)).click();
                driver.findElement(By.xpath(EnterPassword)).sendKeys(password);
                driver.findElement(By.xpath(submit)).click();

                try{
                if(driver.findElement(By.xpath(WrongUserNamePsw)).isDisplayed()){
                     System.out.println("Wrong Info provided");
                }
                }catch (Exception e){
                  driver.navigate().back();
                  elem = driver.findElement(By.xpath(MyAccount));
                  mouseOver(elem);
                  pauseMe(1);
                  driver.findElement(By.xpath(svgForLogout)).click();
                  pauseMe(3);
                  driver.findElement(By.xpath(Logout)).click();
                  pauseMe(3);
                  Assert.assertTrue(driver.findElement(By.xpath(login)).isDisplayed());
                  System.out.println("Logout successfully");
                }
        }
}
