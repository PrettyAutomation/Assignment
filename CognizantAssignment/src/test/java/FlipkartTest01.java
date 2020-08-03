import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestBase;
import util.pages.CartPage;
import util.pages.CheckoutPage;
import util.pages.HomePage;

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
    public HomePage homePage = new HomePage();
    public CartPage cartPage = new CartPage();
    public CheckoutPage checkoutPage = new CheckoutPage();
    public Logger logger = LogManager.getLogger(FlipkartTest01.class);

        @BeforeTest
        public void loginWeb(){
            initialize();
            //gridSetup();
            PageFactory.initElements(driver,homePage);
            PageFactory.initElements(driver,cartPage);
            PageFactory.initElements(driver,checkoutPage);
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

                // On HomePage Search for the item
                switchToWindow();
                homePage.close.click();
                assertNotNull(homePage.searchBox);
                homePage.searchBox.sendKeys(prop.getProperty(searchItem));
                homePage.submit.click();

                scrollToBottom();
                driver.navigate().refresh();

                //get the list of camera name and respective their price on HomePage
                int cameraListSize = homePage.listOfCameraResult.size();
                int priceListSize = homePage.listOfPrice.size();


               //get the list of camera name and respective their price on HomePage
                List<String> itemDetail = null;
                if(cameraListSize > 2 && priceListSize>1){
                    itemDetail = findItemInList(homePage.listOfCameraResult,homePage.listOfPrice, prop.getProperty(perticularCameraItem));
                    Assert.assertNotNull(itemDetail);
                }

                switchToWindow();

                //to verify the name and price on cart page and Home page are same

                Assert.assertTrue(cartPage.CartPageNameXpath.getText().contains(itemDetail.get(0)));
                Assert.assertEquals(itemDetail.get(1), cartPage.CartPagePriceXpath.getText());

                // Click on Buy now if any error message occur due to not availability of item catch by the below code and stop the testcase.
                try {
                      takeScreenshot();
                      cartPage.BuyNow.click();
                      Assert.assertNotNull(cartPage.Continue);
                }catch (Exception e){
                     e.printStackTrace();
                     logger.error("issue with product availability");
                     driver.quit();
                }

                // Switch to Checkout page
                switchToWindow();
                dismissAlert();
                cartPage.Continue.click();
                Assert.assertTrue(checkoutPage.ErrorOnWrongMobileOrEmail.isDisplayed());

                checkoutPage.EnterMobileNo.sendKeys(emailOrMobile);
                cartPage.Continue.click();
                checkoutPage.EnterPassword.sendKeys(password);
                homePage.submit.click();

                try{
                if(checkoutPage.WrongUserNamePsw.isDisplayed()){
                     logger.error("Wrong Info provided");
                }
                }catch (Exception e){
                  //Logout from flipkart and verify
                  driver.navigate().back();
                  mouseOver(cartPage.MyAccount);
                  cartPage.svgForLogout.click();
                  cartPage.Logout.click();
                  Assert.assertTrue(cartPage.Login.isDisplayed());
                  logger.info("Logout successfully");
                }
        }
}
