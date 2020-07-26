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
 * Read the data from external source
 *  Handle different browser compatibility
 *
 */
public class TestBase implements CommonConstants{
	
	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
		
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("src/main/java/config/FlipkartData.properties");
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

	public void wait(WebElement elem, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time * 1000);
		wait.until(ExpectedConditions.visibilityOf(elem));
	
	}

	//click on element
	public void scriptClick(WebElement elem){
		((JavascriptExecutor) driver).executeScript("arguments[0].click;", elem);
	}

	//Scroll to the bottom of page
	public  void scrollToBottom(){
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

    //Scroll to the element
	public  void scrollToElement(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void switchToWindow(){
		for(String window :driver.getWindowHandles()){
			driver.switchTo().window(window);
		}
	}

	// To read the Excel data
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

	public void pauseMe(int time) throws InterruptedException {
		Thread.sleep(time * 1000);
	}

	public void takeScreenshot() throws IOException {
		File SrcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("src/main/java/config/PlacedOrder.png"));

	}

	public void dismissAlert(){
		try {
			driver.switchTo().alert().dismiss();
		}catch(Exception e){

		}

	}

	public void mouseOver(WebElement elem){
		Actions actions = new Actions(driver);
		actions.moveToElement(elem).build().perform();
	}

	public List<String> findItemInList(List<WebElement> itemList, List<WebElement> priceList, String item){
		List<String> cameraDetails = new ArrayList<String>();
		String CameraName = "";
		String Price = "";
		try{
			for (int i = 0; i < itemList.size(); i++) {
				CameraName = itemList.get(i).getAttribute("alt");
				Price = priceList.get(i).getText();
				if (CameraName.contains(item)) {
					cameraDetails.add(CameraName);
					cameraDetails.add(Price);
					itemList.get(i).click();
					break;
				}
			}
		}catch (Exception e){
			itemList.get(0).click();
		}
		return cameraDetails;
	}


	

}
