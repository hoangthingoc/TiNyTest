package pageCommon;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CommonPM{

	static final int WAIT_SECONDS = 30;
	

	public void verifyText(WebDriver wd, String text) {
		List<WebElement> list = wd.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
		Assert.assertTrue(list.size() > 0);
	}

	public void waitForElement(WebDriver wd, WebDriverWait wait, WebElement webElement) {
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void takeScreenshotRegression(WebDriver wd, String className) throws IOException {
		File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
		String fileNameMonth = dateFormat.format(now);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM_dd_yyyy");
		String fileNameDate = dateFormat1.format(now);
		FileUtils.copyFile(screenshot, new File(pageCommon.TestBase.CAPTURE_SCREEN_FOLDER_PATH + fileNameMonth + "\\"
				+ fileNameDate + "\\ScreenShotsRegression\\" + className + ".jpg"));
		pageCommon.TestBase.reportLogScreenshot(pageCommon.TestBase.CAPTURE_SCREEN_FOLDER_PATH + fileNameMonth + "\\"
				+ fileNameDate + "\\ScreenShotsRegression\\" + className + ".jpg");
		System.out.print("Screenshot is captured and stored.");
	}

	public void takeScreenshotSmoke(WebDriver wd, String className) throws IOException {
		File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_yyyy");
		String fileNameMonth = dateFormat.format(now);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM_dd_yyyy");
		String fileNameDate = dateFormat1.format(now);
		FileUtils.copyFile(screenshot, new File(pageCommon.TestBase.CAPTURE_SCREEN_FOLDER_PATH + fileNameMonth + "\\"
				+ fileNameDate + "\\ScreenShotsSmoke\\" + className + ".jpg"));
		pageCommon.TestBase.reportLogScreenshot(pageCommon.TestBase.CAPTURE_SCREEN_FOLDER_PATH + fileNameMonth + "\\"
				+ fileNameDate + "\\ScreenShotsSmoke\\" + className + ".jpg");
		System.out.print("Screenshot is captured and stored.");
	}

	public void waitElement(WebElement target) throws InterruptedException {

		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				TimeUnit.SECONDS.sleep(1);
				target.getLocation();
				success = true;
			} catch (Exception e)
			{
				retry++;
			}
		}
	}

	public void waitElementID(WebDriver wd, String id) throws InterruptedException {

		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				wd.findElement(By.id(id));
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	public void waitElementCSS(WebDriver wd, String CSS) throws InterruptedException {

		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				wd.findElement(By.cssSelector(CSS));
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	public void waitElementXPATH(WebDriver wd, String XPATH) throws InterruptedException {

		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				wd.findElement(By.xpath(XPATH));
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	
	
	
	public void selectOptionWithText(WebDriver wd, WebDriverWait wait, String textToSelect) {
		try {
			WebElement myautotext = new WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '"+textToSelect+"')]")));
			myautotext.click();  
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void selectOptionWithTextbyDivTag(WebDriver wd, WebDriverWait wait, String textToSelect) {
		try {
			WebElement myautotext = new WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '"+textToSelect+"')]")));
			myautotext.click();  
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	public void selectOptionWithTextbyLiTag(WebDriver wd, WebDriverWait wait, String textToSelect) {
		try {
			WebElement myautotext = new WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), '"+textToSelect+"')]")));
			myautotext.click();  
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void verifyTitle(WebDriver wd, String page) {
		String actualTitle = wd.getTitle();
		String[] parts = actualTitle.split(" - ");

		String part2 = parts[1];

		Assert.assertEquals(page, part2);
	}
	
	
	
	public double exchangeStringToDouble(String fullmonetaryunit ) {
		fullmonetaryunit=  fullmonetaryunit.replaceAll(",", "");
		double myvalue =  Double.parseDouble(fullmonetaryunit);
		System.out.println(myvalue);
		return myvalue;
	}
	public double exchangeStringToMonetary(String fullmonetaryunit ) {
		String[] str = fullmonetaryunit.split("[$]");
		fullmonetaryunit=  str[1].replaceAll(",", "");
		double myvalue =  Double.parseDouble(fullmonetaryunit);
		System.out.println(myvalue);
		return myvalue;
	}
	public double roundTwoDoubleDigits(double number) {
		return ((double)Math.round(number * 100) )/ 100;
	}
	

	public WebElement getElementByDisplayText(WebDriver wd, String DispayText) {
		return wd.findElement(By.xpath("//*[text()=, '" + DispayText + "']"));
	}
	
	
}
