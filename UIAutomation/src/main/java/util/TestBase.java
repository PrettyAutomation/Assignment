package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

/**
 * TestBase class
 *
 */
public class TestBase implements CommonConstants{
	
	public static WebDriver driver;
	public Properties prop;
	
	
	public TestBase() {
		
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("src/main/java/config/config1.properties");
			prop.load(fis);
			
		} catch( Exception e) {
			
			e.printStackTrace();	
		}		
	}
	
	public void initialize() {
		
		String browserName = prop.getProperty("browser");
	
		if(browserName.equals("chrome")) {
		
		   System.setProperty("webdriver.chrome.driver","src/main/java/driver/chromedriver");
		   driver = new ChromeDriver();	
		   
		}else {
			
			System.setProperty("webdriver.gecko.driver","put the path of firefox driver.exe file");
			   driver = new FirefoxDriver();	
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	      	
	}
	
	public ArrayList<String> getExcelDate(final String filePath, final int sheetNo) {
		
		ArrayList<String> data = new ArrayList<String>();
		FileInputStream fis;
		Workbook workbook;
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNo);
			int rownum = sheet.getLastRowNum();
			for(int i=0; i< rownum; i++) {
			  data.add(sheet.getRow(i).getCell(0).getStringCellValue());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;	
	}
	
	public void wait(WebElement elem, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time * 1000);
		wait.until(ExpectedConditions.visibilityOf(elem));
	
	}

	public  void scrollToElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public int lowestPriceElement(float minPriceItem, List<String> priceList){
		int count = 1;
		for(int i=1; i<=priceList.size(); i++){
			try {
				float otherItemPrice = Float.parseFloat(priceList.get(i));
				if (minPriceItem > otherItemPrice) {
					minPriceItem = otherItemPrice;
					count = i;
				}
			}catch (Exception e){

			}
		}
		System.out.println("lowest price product is " + minPriceItem);
		return count;
	}

	public void switchToWindow(){
		for(String window :driver.getWindowHandles()){
			driver.switchTo().window(window);
		}
	}

	public void pauseMe(int time) throws InterruptedException {
		Thread.sleep(time);
	}

	public void takeScreenshot() throws IOException {
		File SrcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("src/main/java/config/PlacedOrder1.png"));

	}


	

}
