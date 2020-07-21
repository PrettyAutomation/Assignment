package util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

	public Object[][] getExcelData(final String filePath, final int sheetNo) {
		FileInputStream fis;
		Workbook workbook;
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNo);
			int rowNum = sheet.getLastRowNum();
			int cellNum = sheet.getRow(0).getLastCellNum();
			Object[][] data = new Object[rowNum][cellNum];
			for (int i=0; i< rowNum; i++){
				for(int j =0; j< cellNum; j++){
					data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
					System.out.println(data);
				}
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object[0][0];
	}

	
	public void wait(WebElement elem, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time * 1000);
		wait.until(ExpectedConditions.visibilityOf(elem));
	
	}

	public void mouseOver(WebElement elem){
		Actions actions = new Actions(driver);
		actions.moveToElement(elem);
		actions.click().build().perform();
	}

	public void selectQuantity(WebElement elem, String quantity){
		Select select = new Select(elem);
		List<WebElement> options = select.getOptions();
		if(options.size()>1) {
			select.selectByValue(quantity);
		}else{
			select.selectByValue("1");
		}
	}

	public void clickOnElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void switchToWindow(){
		for(String window :driver.getWindowHandles()){
			driver.switchTo().window(window);
		}
	}

	public void findItemInList(List<WebElement> itemList, String item){
		try{
			String watchName = "";
			for (int i = 0; i < itemList.size(); i++) {
				watchName = itemList.get(i).getAttribute("alt");
				if (watchName.contains(item)) {
					itemList.get(i).click();
					break;
				}
			}
		}catch (Exception e){
			itemList.get(0).click();
		}
	}
}
