package org.mobile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Mobile {
	@DataProvider(name="mobile")
	public Object[][]mobiledetails(){
		return new Object[][] {{"miphone"}};}
	static WebDriver driver;
	static Long Start;
	
	
@BeforeClass (groups="default")
	public static void BrowserLunch () {
		WebDriverManager.chromedriver().setup();
driver= new ChromeDriver();
driver.get("https://www.flipkart.com/");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}
	
@BeforeMethod(groups="default")
public static void StartingTime() {
	System.out.println("BeforeTime");
	Start= System.currentTimeMillis();}
@AfterMethod(groups="default")
public static void endtime() {
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	System.out.println("endtime");}
@Test(priority=1,groups="smoke")
public static void cancel() {
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	System.out.println("cancel");
	try {
	WebElement login = driver.findElement(By.xpath("//button[text()='✕']"));
	login.isDisplayed();
login.click();}
catch(Exception e) {
	System.out.println("cancel");}}
	
@AfterClass(groups="default")
public static void BrowserQuit() {
	System.out.println("Browserquit");}

	@Test(priority=2,groups="smoke",dataProvider = "mobile")
	public static void search(String name) {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		System.out.println("search");
		WebElement search=driver.findElement(By.xpath("//input[@name='q']"));
	search.sendKeys(name,Keys.ENTER);}
		@Test(priority=3,groups="smoke")
		public static void Workbook() throws IOException {
			WebElement click=driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]"));
			String mi = click.getText();
			File file=new File(".//target/mobliedetails.xlxs");
			FileOutputStream f=new FileOutputStream(file);
			HSSFWorkbook w=new HSSFWorkbook();
			HSSFSheet s = w.createSheet("miname");
			HSSFRow r = s.createRow(0);
			HSSFCell c = r.createCell(0);
			c.setCellValue(mi);
			FileOutputStream f1 =new FileOutputStream(file);
			w.write(f1);
			System.out.println("Workbook");
		}
		@Test(priority=4,groups="smoke")
		public void mobile() {
			driver.findElement(By.xpath("(//div[@class='_4rR01T'])[1]")).click();
			System.out.println("MobileOption");
		}
		@Test(priority=5,groups="smoke")
		public void WindowHandle() {
			String mi = driver.getWindowHandle();
			Set<String> mobile = driver.getWindowHandles();
			for(String x :mobile) {
				if(!x.equals(mi)) {
			driver.switchTo().window(x);}}
			System.out.println("Window");}
		
		@Test(priority=6,groups="smoke")
		public void ScreenShot() throws IOException {
			TakesScreenshot tk=(TakesScreenshot) driver;
		     File b = tk.getScreenshotAs(OutputType.FILE);
		     File d=new File("C:\\Users\\vetrivel\\eclipse-workspace\\FW\\screenshot\\fk.png");
		     FileUtils.copyFile(b,d);
		     System.out.println("ScreenShot");
		}

		
			}
			

	




