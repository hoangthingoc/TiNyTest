package pageCommon;

import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import admin.Controls;
import pageCommon.Constant;

public class TestBase {

	static boolean functionalFail = false;
	protected static String systemdir = System.getProperty("user.dir");
	protected static String homedir = System.getProperty("user.home");
	static {
		System.setProperty("webdriver.gecko.driver", systemdir + "/src/lib/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", systemdir + "/src/lib/chromedriver.exe");
	}

	static final int WAIT_PAGE_LOAD_COMPLETED_SECONDS = 50;
	static final int WAIT_PAGE_LOAD_TIMEOUT_SECONDS = 40;
	static final int WAIT_IMPLICIT_TIMEOUT_SECONDS = 8;
	static final int WAIT_SCRIPT_TIMEOUT_SECONDS = 8;
	protected static final int WAIT_SECONDS = 3;
	static final int RERUN_COUNT = 1;

	public static String currentLoggedStep = "";
	public static final int WAIT_SHORT_MINUTES = 80;
	public static final int WAIT_SHORT_SECONDS = 1;
	public static final int WAIT_MEDIUM_SECONDS = 3;
	public static final int WAIT_LONG_SECONDS = 10;
	public static final String SAEVAS_FOLDER_PATH = homedir + "/Downloads";
	public static final String DOWNLOADS_FOLDER_PATH = homedir + "/Downloads";

	// public static final String CAPTURE_SCREEN_FOLDER_PATH =
	// "D:\\Users\\Cem\\Google Drive\\TestNG Reports\\Web\\";
	public static final String CAPTURE_SCREEN_FOLDER_PATH = systemdir + "/test-output/WebCaptures";

	protected String sTestCaseName = this.getClass().getName();
	protected String sTestSuiteFileName = "";
	protected Integer iTestCaseRowOnTestSuiteFile = 0;
	public String testSuiteURL = Constant.TestSuiteUrl.LOGIN;

	protected String browsers = "chrome";
	public static final String userNameOfStaffAccount_Preprod = "thingoc.lampart@gmail.com";
	public static final String passwordOfStaffAccount_Preprod = "abc@123";

	static protected ExtentReports reporter; // SimpleReportFactory.getReporter();
	static protected ExtentTest testReport;
	String sReportFile = "";
	String sEnviromentName = "QA";

	// data csv file
	public static String csvFileName = "ChoTotLogin.csv";// it will change when run only one TC
	public static String csvFilePath = "\\src\\data\\";
	public static HashMap<String, HashMap<String, String>> dataCSVInput = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> dataTCCurrent = new HashMap<String, String>();

	public static boolean isElementPresent(WebElement el) {
		try {
			return el.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public static void assertExtentEquals(Object s1, Object s2) throws Exception {
		if (!(s1.equals(s2)))
			logFunctionalFail(s1.toString() + " is NOT equal " + s2.toString());
	}

	public static void assertExtentEquals(Object s1, Object s2, String message) throws Exception {
		if (!(s1.equals(s2)))
			logFunctionalFail(message + " - " + s1.toString().trim() + " is NOT equal " + s2.toString().trim());
	}

	public static void assertExtentNotEquals(Object s1, Object s2, String message) throws Exception {
		if (s1.equals(s2))
			logFunctionalFail(message + " - " + s1.toString().trim() + " is equal " + s2.toString().trim());
	}

	public static void assertExtentNotEquals(Object s1, Object s2) throws Exception {
		if (s1.equals(s2))
			logFunctionalFail(s1.toString().trim() + " is equal " + s2.toString().trim());
	}

	public static void assertExtentTrue(Boolean b) throws Exception {
		if (b == false)
			logFunctionalFail("Result is NOT TRUE ");
	}

	public static void assertExtentTrue(Boolean b, String sMessage) throws Exception {
		if (b == false)
			logFunctionalFail(sMessage);
	}

	public static void assertExtentFalse(Boolean b) throws Exception {
		if (b == true)
			logFunctionalFail("Result is NOT FALSE ");
	}

	public static void assertExtentFalse(Boolean b, String sMessage) throws Exception {
		if (b == true)
			logFunctionalFail(sMessage);
	}

	public static void extentEquals(Object s1, Object s2) throws Exception {
		extentEqual(s1, s2);
	}

	public static void extentEqual(Object s1, Object s2) throws Exception {
		functionalFail = false;
		if (!(s1.equals(s2))) {
			logFail(s1.toString() + " isn't equal " + s2.toString());
			functionalFail = true;
		}
	}

	public static Boolean CheckmyURL(WebDriver wd, String URL) {
		String sUrl = wd.getCurrentUrl().toString();
		if (sUrl.contains(URL)) {
			return true;
		}
		return false;
	}

	public void setTextboxValue(WebElement el, String value) throws Exception {
		waitElement(el);
		if (isElementPresent(el)) {
			el.sendKeys(value);
			sleep(WAIT_SHORT_SECONDS);
		} else
			logFunctionalFail(Constant.Commons.OBJECT_NOT_PRESENT);
	}

	public void clickOnButton(WebDriver wd, WebDriverWait wait, WebElement el) throws Exception {
		waitElement(el);
		if (isElementPresent(el)) {
			click(wd, wait, el);
			sleep(WAIT_SHORT_SECONDS);
		} else
			logFunctionalFail(Constant.Commons.OBJECT_NOT_PRESENT);
	}

	public void selectRadoButton(WebDriver wd, WebDriverWait wait, WebElement el) throws Exception {
		waitElement(el);
		if (isElementPresent(el)) {
			click(wd, wait, el);
			sleep(WAIT_SHORT_SECONDS);
		} else
			logFunctionalFail(Constant.Commons.OBJECT_NOT_PRESENT);
	}

	public void setCheckboxValue(WebDriver wd, WebDriverWait wait, WebElement el, boolean isValue) throws Exception {
		waitElement(el);
		scrollIntoView(wd, wait, el);
		sleep(WAIT_SHORT_SECONDS);
		if (isElementPresent(el)) {
			if (isValue == true) {
				boolean isChecked = el.getAttribute("class").contains("unchecked");
				if (isChecked) {
					click(wd, wait, el);
				}
			} else {
				boolean isChecked = el.getAttribute("class").contains("checked");
				if (isChecked) {
					click(wd, wait, el);
				}
			}
			sleep(WAIT_SHORT_SECONDS);
		} else
			logFunctionalFail(Constant.Commons.OBJECT_NOT_PRESENT);
	}

	@Parameters({ "browsers", "testsuite" })
	@BeforeSuite
	public void beforeSuite(@Optional("chrome") String browsers, @Optional("REG") String testsuite) {

		sReportFile = Paths.get(System.getProperty("user.dir") + "/test-output/PW Extent Report " + testsuite + ".html")
				.toString();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(sReportFile);
		// htmlReporter.loadConfig(System.getProperty("user.dir") +
		// "/extent-config.xml");
		htmlReporter.config().setReportName("PW - Automation Report");

		htmlReporter.config()
				.setCSS(".end-time:before {content: 'End Time: ';} "
						+ "\n .start-time:before {content: 'Start Time: '; display: inline-block;}"
						+ "\n .grey.lighten-1:before {content: 'Time taken: '; display: inline-block;}"
						+ "\n .end-time, .category-status > .fail {background: #5a50ef; display: inline-block;}");

		// if(Controls.appConfig.appBaseUrl.contains("qa-secure1"))
		// sEnviromentName = "QA";
		// else if(Controls.appConfig.appBaseUrl.contains("preprod-secure1"))
		// sEnviromentName = "PREPROD";
		// else
		// sEnviromentName = "PROD";

		htmlReporter.config().setJS("$(document).ready(function() {"
				+ "var list = $('#test-view-charts').find('.block.text-small');\n" + "var left_list_0 = list[0];\n"
				+ "var left_list_1 = list[1];\n" + "var list_span_0 = $(left_list_0).find('span');\n"
				+ "var span_value_1 = parseInt($(list_span_0[0]).text());\n"
				+ "var list_span_1 = $(left_list_1).find('span');\n"
				+ "var span_value_2 = parseInt($(list_span_1[0]).text());\n"
				+ "var span_value_3 = parseInt($(list_span_1[1]).text());\n"
				+ "var total = span_value_1 + span_value_2 +span_value_3;\n"
				+ "$($('.block.text-small')[0]).prepend('<span>'+total+' Total test(s) in " + sEnviromentName + ", "
				+ testsuite + "</span></br>');\n});");
		reporter = new ExtentReports();

		reporter.attachReporter(htmlReporter);
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			reporter.setSystemInfo("Run Nodes", inetAddress.getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			reporter.setSystemInfo("Run Nodes", "LocalHost");
		}

		reporter.setSystemInfo("Browser", browsers);
		// String host = addr.getHostName();
		reporter.setSystemInfo("Env.", Controls.appConfig.appBaseUrl);
	}
//		// read data in csv
//		try {
//			String sClassName = this.getClass().getName().toString();
//			String[] listName = sClassName.split("\\.");
//			String csvFileName = listName[listName.length - 1];
//			String extension = ".csv";
//			// String sTCId = sClassName.substring(sClassName.indexOf('.') + 2,
//			// sClassName.length());
//
//			if (csvFileName.trim() == "")
//				logExceptionFail(Constant.Commons.CHECKING_DATA_FILE);
//			else if (!csvFileName.contains(extension)) {
//				csvFileName = csvFileName.substring(0, csvFileName.length() - 3);
//				csvFileName += extension;
//			} else
//				logExceptionFail(Constant.Commons.CHECKING_DATA_FILE);
//			if (!"REG".equals(testsuite)) {
//				csvFileName = testsuite.replace(" ", "") + extension;
//			}
//			CSVRead csvRead = new CSVRead();
//			dataCSVInput = csvRead.csvReadData(System.getProperty("user.dir") + csvFilePath + csvFileName).get(0);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
////
//	}

	// load browser config from xml, or default to just firefox for standalone
	// testing
		@Parameters("browsers")
		@BeforeClass
		public void beforeClass(@Optional("chrome") String browsers) {
			this.browsers = browsers;
			testReport = reporter.createTest(this.getTestCaseName(), this.getTestCaseUrl());
			try {

				String sClassName = this.getClass().getName().toString();
				String[] listName = sClassName.split("\\.");
				String sTCId = listName[listName.length - 1];
				String extension = ".csv";
				if (!sTCId.contains(extension) && sTCId.trim() != "") {
					csvFileName = sTCId.substring(0, sTCId.length() - 3);
					csvFileName += extension;
					CSVRead csvRead = new CSVRead();
					dataCSVInput = csvRead.csvReadData(System.getProperty("user.dir") + csvFilePath + csvFileName, sTCId).get(0);
					if (dataCSVInput != null) {
						HashMap<String, String> dataTCTemp = dataCSVInput.get(sTCId);
						if (dataTCTemp != null) {
							dataTCCurrent = dataTCTemp;
						}
						// else {
						// dataTCCurrent =
						// dataCSVInput.get(Constant.KeysValueData.VALUE_DEFAULT_KEY);
						// }
						return;
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	// public static String getTCId()
	// {
	// String sClassName = this.getClass().getName().toString();
	// String sTCId = sClassName.substring(sClassName.indexOf('.') + 2,
	// sClassName.length());
	//
	// return sTCId;
	// }
	//

	// based on browser config, queue up the desired web drivers
	@DataProvider(name = "scenarioProvider", parallel = false)
	 public Object[][] createScenarioData() throws Exception {
	 Set<Object[]> scenarios = new java.util.HashSet<Object[]>();

	 for (String browser : browsers.split(",")) {
	 for (int i = 0; i < RERUN_COUNT; i++) {
	
//	 for (User user : Controls.users) {
	 WebDriver wd;
	
	 switch (browser.toLowerCase()) {
	
	 case "firefox":
	 if (Controls.gridConfig[0].useGrid) {
	 wd = new RemoteWebDriver(new URL(Controls.gridConfig[0].address),
	 DesiredCapabilities.firefox());
	 } else {
	 // Create prefs for FirefoxProfile
	
	 FirefoxProfile profile = new FirefoxProfile();
	 profile.setPreference("browser.startup.page", 0);
	 profile.setPreference("browser.startup.homepage", "about:blank");
	 profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
	
	 DesiredCapabilities cap = DesiredCapabilities.firefox();
	 cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 cap.setCapability(FirefoxDriver.PROFILE, profile);
	 wd = new FirefoxDriver(cap);
	
	 //wd = new FirefoxDriver();
	
	 // Runtime.getRuntime().exec( "cscript
	 // C:/Cem/Selenium/SidebySide.vbs" );
	 }
	 break;
	 // Run for testscript report "Chrome"
	 case "chrome":
	 //case "Chrome":
	 if (Controls.gridConfig[0].useGrid) {
	 wd = new RemoteWebDriver(new URL(Controls.gridConfig[0].address),
	 DesiredCapabilities.chrome());
	 } else {
	 // Create prefs map to store all preferences
	 Map<String, Object> prefs = new HashMap<String, Object>();
	 prefs.put("profile.default_content_settings.popups", 0);
	 prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting",
	 1);
	 prefs.put("download.prompt_for_download", false);
	 ChromeOptions options = new ChromeOptions();
	 options.setExperimentalOption("prefs", prefs);
	 DesiredCapabilities cap = DesiredCapabilities.chrome();
	 cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 cap.setCapability(ChromeOptions.CAPABILITY, options);
	
	 wd = new ChromeDriver(options);
	 }
	 break;
	 default:
	 RuntimeException x = new RuntimeException("unknown browser configuration " +
	 browser);
	 x.printStackTrace();
	 throw x;
	 }
	
	 wd.manage().timeouts().pageLoadTimeout(WAIT_PAGE_LOAD_TIMEOUT_SECONDS,
	 TimeUnit.SECONDS);
	 wd.manage().timeouts().implicitlyWait(WAIT_IMPLICIT_TIMEOUT_SECONDS,
	 TimeUnit.SECONDS);
	 wd.manage().timeouts().setScriptTimeout(WAIT_SCRIPT_TIMEOUT_SECONDS,
	 TimeUnit.SECONDS);
	
	 scenarios.add(new Object[] { wd, new WebDriverWait(wd, WAIT_SECONDS)});
	
//	 }
	
	 }
	 }
	 return scenarios.toArray(new Object[0][0]);
	 }

	public String getTestCaseName() {
		// return "<a href=" + testSuiteURL + "/>" + sTestCaseName +"</a>";
		return sTestCaseName;
	}

	public String getTestCaseUrl() {
		return "Test Suite File: " + sTestSuiteFileName + " Row: " + iTestCaseRowOnTestSuiteFile.toString() + "<br/>"
				+ "<a href=" + testSuiteURL + "/>" + testSuiteURL + "</a>";
	}

	public static void waitAndClick(WebDriverWait wait, WebElement el) throws Exception {
		wait.until(ExpectedConditions.visibilityOf(el));
		wait.until(ExpectedConditions.elementToBeClickable(el));
		Thread.sleep(250);
		el.click();
	}

	public static void waitAndClick(WebDriverWait wait, WebElement el, int minimumWait) throws Exception {
		Thread.sleep(minimumWait);
		waitAndClick(wait, el);
	}

	protected void waitAndSendKeys(WebDriverWait wait, WebElement el, String text) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(el));
		el.sendKeys(text);
	}

	protected void waitForSuccessToast(WebDriver wd, WebDriverWait wait) throws Exception {
		wd.switchTo().defaultContent();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.toast.toast-success")));

		wd.findElement(By.className("toast-close-button")).click();
	}

	protected void waitForFailureToast(WebDriver wd, WebDriverWait wait) throws Exception {
		wd.switchTo().defaultContent();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.toast.toast-error")));

		wd.findElement(By.className("toast-close-button")).click();
	}

	public File getLatestFilefromDir() {
		File dir = new File(DOWNLOADS_FOLDER_PATH);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public File getLatestFilefromDir(String dirPath, @Optional("pdf") String fileType) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}

		if (FilenameUtils.getExtension(lastModifiedFile.getPath()).equals(fileType)) {
			return lastModifiedFile;
		} else
			return null;
	}

	public File getLatestFilefromDir(String dirPath, Date lastModifiedTime, @Optional("pdf") String fileType) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		// la file minh tai ve phai lon hon ngay minh lay

		if (FilenameUtils.getExtension(lastModifiedFile.getPath()).equals(fileType)) {
			if (new Date(lastModifiedFile.lastModified()).compareTo(lastModifiedTime) < 0)
				return null;
			return lastModifiedFile;
		} else
			return null;
	}

	public List<File> getTwoLatestFilefromDir(String dirPath, Date lastModifiedTime, @Optional("csv") String fileType) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		List<File> fileExcels = new ArrayList<File>();
		if (files == null || files.length == 0) {
			return null;
		}
		// Sort mảng tăng dần
		File temp;
		for (int i = 0; i < files.length - 1; i++) {
			for (int j = i + 1; j < files.length; j++) {
				if (files[j].lastModified() < files[i].lastModified()) {
					temp = files[i];
					files[i] = files[j];
					files[j] = temp;
				}
			}
		}
		// get 2 file mới nhất
		File lastModifiedFile = files[files.length - 1];
		File lastModifiedFile2 = files[files.length - 2];
		if ((FilenameUtils.getExtension(lastModifiedFile.getPath()).equals(fileType)
				&& (FilenameUtils.getExtension(lastModifiedFile2.getPath()).equals(fileType)))) {
			if (new Date(lastModifiedFile.lastModified()).compareTo(lastModifiedTime) < 0
					&& new Date(lastModifiedFile2.lastModified()).compareTo(lastModifiedTime) < 0) {
				return null;
			}
			fileExcels.add(lastModifiedFile);
			fileExcels.add(lastModifiedFile2);
		} else
			return null;
		return fileExcels;
	}

	protected void windowsSideBySide() throws IOException {
		Runtime.getRuntime().exec("cscript C:/Cem/Selenium/SidebySide.vbs");
	}

	public static void setText(WebDriver wd, WebDriverWait wait, WebElement target, String text)
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(target));
			wait.until(ExpectedConditions.elementToBeClickable(target));

			target.clear();
			target.sendKeys(text);
		} catch (Exception e) {

		}
	}

	public static void click(WebDriver wd, WebDriverWait wait, WebElement target) throws InterruptedException {

		try {
			// wait.until( new Predicate<WebDriver>() { public boolean
			// apply(WebDriver wd) { return
			// ((JavascriptExecutor)wd).executeScript("return
			// document.readyState").equals("complete"); } } );
			wait.until(ExpectedConditions.visibilityOf(target));
			wait.until(ExpectedConditions.elementToBeClickable(target));

			target.click();

			// alternate method: active if click() is problematic
			// JavascriptExecutor executor = (JavascriptExecutor) wd;
			// executor.executeScript("arguments[0].getLocation();", target);
		} catch (Exception e) {
			try {
				System.out.println("Element Exception");
				JavascriptExecutor executor = (JavascriptExecutor) wd;
				executor.executeScript("arguments[0].click();", target);
			} catch (org.openqa.selenium.StaleElementReferenceException e1) {
				System.out.println("Stale element found(" + target + " Recovering...");
				String element = target.toString();
				String[] list = element.split("\\)] -> |: |\\]");
				String locator = list[2];
				switch (locator) {
				case "id":
					WebElement foundById = wd.findElement(By.id(list[3]));
					JavascriptExecutor executor = (JavascriptExecutor) wd;
					executor.executeScript("arguments[0].click();", foundById);
					break;
				case "xpath":
					WebElement foundByXpath = wd.findElement(By.xpath(list[3]));
					JavascriptExecutor executor1 = (JavascriptExecutor) wd;
					executor1.executeScript("arguments[0].click();", foundByXpath);
					break;
				case "css selector":
					WebElement foundByCss = wd.findElement(By.cssSelector(list[3]));
					JavascriptExecutor executor2 = (JavascriptExecutor) wd;
					System.out.println(list[3]);
					executor2.executeScript("arguments[0].click();", foundByCss);
					break;
				case "name":
					WebElement foundByName = wd.findElement(By.name(list[3]));
					JavascriptExecutor executor3 = (JavascriptExecutor) wd;
					executor3.executeScript("arguments[0].click();", foundByName);
					break;
				default:
					System.out.println("WebElement Issue");
					throw new NullPointerException();
				}
			}
		}
		// finally {
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(Constant.Commons.LOADING_ICON_CLASS)));
		//
		// }
	}

	public void getLocation(WebDriver wd, WebDriverWait wait, WebElement target) {
		int attemptsNoSuchElement = 0;
		while (attemptsNoSuchElement < 10) {
			try {
				String className = super.getClass().getName();
				PageFactory.initElements(wd, className);
				target.getLocation();
				break;
			} catch (NoSuchElementException e) {
				int attemptsNotVisible = 0;
				while (attemptsNotVisible < 10) {
					try {
						String className = super.getClass().getName();
						PageFactory.initElements(wd, className);
						wait.until(ExpectedConditions.visibilityOf(target));
						JavascriptExecutor executor = (JavascriptExecutor) wd;
						executor.executeScript("arguments[0].getLocation();", target);
						break;
					} catch (ElementNotVisibleException e1) {
						int attemptsStale = 0;
						while (attemptsStale < 10) {
							try {
								String className = super.getClass().getName();
								PageFactory.initElements(wd, className);
								wait.until(ExpectedConditions.elementToBeClickable(target));
								JavascriptExecutor executor = (JavascriptExecutor) wd;
								executor.executeScript("arguments[0].getLocation();", target);
								break;
							} catch (StaleElementReferenceException e2) {
							}
							attemptsStale++;
						}
					}
				}
				attemptsNotVisible++;
			}
		}
		attemptsNoSuchElement++;
	}

	public void clear(WebDriver wd, WebDriverWait wait, WebElement target) {
		int attemptsNoSuchElement = 0;
		while (attemptsNoSuchElement < 10) {
			try {
				String className = super.getClass().getName();
				PageFactory.initElements(wd, className);
				JavascriptExecutor executor = (JavascriptExecutor) wd;
				executor.executeScript("arguments[0].clear();", target);
				break;
			} catch (NoSuchElementException e) {
				int attemptsNotVisible = 0;
				while (attemptsNotVisible < 10) {
					try {
						String className = super.getClass().getName();
						PageFactory.initElements(wd, className);
						wait.until(ExpectedConditions.visibilityOf(target));
						JavascriptExecutor executor = (JavascriptExecutor) wd;
						executor.executeScript("arguments[0].clear();", target);
						break;
					} catch (ElementNotVisibleException e1) {
						int attemptsStale = 0;
						while (attemptsStale < 10) {
							try {
								String className = super.getClass().getName();
								PageFactory.initElements(wd, className);
								wait.until(ExpectedConditions.elementToBeClickable(target));
								JavascriptExecutor executor = (JavascriptExecutor) wd;
								executor.executeScript("arguments[0].clear();", target);
								break;
							} catch (StaleElementReferenceException e2) {
							}
							attemptsStale++;
						}
					}
				}
				attemptsNotVisible++;
			}
		}
		attemptsNoSuchElement++;
	}

	public void waitListElement(List<WebElement> target) throws InterruptedException {
		boolean success = false;
		int retry = 0;
		while (!success && retry < WAIT_LONG_SECONDS) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
				((WebElement) target).getLocation();
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	public void waitElement(WebElement target) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				TimeUnit.MILLISECONDS.sleep(250);
				target.getLocation();
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	public void waitElementID(WebDriver wd, String ID) throws InterruptedException {

		boolean success = false;
		int retry = 0;

		while (!success && retry < WAIT_SECONDS) {
			try {
				TimeUnit.SECONDS.sleep(1);
				WebElement target = wd.findElement(By.id(ID));
				target.getLocation();
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
				TimeUnit.SECONDS.sleep(1);
				WebElement target = wd.findElement(By.cssSelector(CSS));
				target.getLocation();
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
				TimeUnit.SECONDS.sleep(1);
				WebElement target = wd.findElement(By.xpath(XPATH));
				target.getLocation();
				success = true;
			} catch (Exception e) {
				retry++;
			}
		}
	}

	protected void waitExpectedElement(WebDriver wd, WebDriverWait wait, WebElement el) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}

	protected void waitExpectedElementVisibility(WebDriver wd, WebDriverWait wait, WebElement el) throws Exception {
		// wait = new WebDriverWait(wd,30);
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	protected void waitExpectedTextOfElement(WebDriver wd, WebDriverWait wait, WebElement el, String stext)
			throws Exception {
		// wait = new WebDriverWait(wd,15);
		wait.until(ExpectedConditions.textToBePresentInElementValue(el, stext));
	}

	protected void waitExpectedValueTextOfElement(WebDriver wd, WebDriverWait wait, WebElement el, String stext)
			throws Exception {
		// wait = new WebDriverWait(wd,15);
		wait.until(ExpectedConditions.textToBePresentInElementValue(el, stext.trim()));
	}

	// Work in progress
	// public void wait4FrameAndSwitch2(WebDriver wd, WebDriverWait wait) {
	// try {
	// // wait.until( new Predicate<WebDriver>() { public boolean
	// // apply(WebDriver wd) { return
	// // ((JavascriptExecutor)wd).executeScript("return
	// // document.readyState").equals("complete"); } } );
	// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(wd.findElement(By.xpath(".//iframe"))));
	// wd.switchTo().defaultContent();
	// wd.switchTo().frame(wd.findElement(By.xpath(".//iframe")));
	// } catch (Exception e) {
	// try {
	// wd.findElement(By.xpath(".//iframe")).getLocation();
	// wd.switchTo().defaultContent();
	// wd.switchTo().frame(wd.findElement(By.xpath(".//iframe")));
	// } catch (Exception e1) {
	// System.out.println("Can't locate iframe");
	// throw new NullPointerException();
	// }
	//
	// }
	// }

	public static void logFailed(String text) throws Exception {
		currentLoggedStep = text;
		if (currentLoggedStep.contains("Step"))
			testReport.log(com.aventstack.extentreports.Status.FAIL, "Script Failed. " + currentLoggedStep);
		else {
			String[] SeparateText = currentLoggedStep.split("[.]");// without
																	// exception
			testReport.log(com.aventstack.extentreports.Status.FAIL, "Functional Failed. " + SeparateText[0]);
		}
	}

	public void urlCheck(WebDriver wd, WebDriverWait wait, String URL) {
//		 wait.until( new Predicate<WebDriver>() { public boolean
//		 apply(WebDriver wd) { return
//		 ((JavascriptExecutor)wd).executeScript("return
//		 document.readyState").equals("complete"); } } );
		String url1 = wd.getCurrentUrl().toString();
		System.out.println(url1);
		if (url1.equals(URL)) {
			System.out.println("Confirmed URL");
		} else {
			throw new RuntimeException("Can't confirm URL: " + URL);
		}
	}

	public static void selectItemInDropListbox(WebElement elDropListbox, String itemName) throws Exception {
		elDropListbox.findElement(By.xpath("//option[contains(text(),'" + itemName + "')]")).click();
	}

	public void scrollTop(WebDriver wd) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) wd;
			je.executeScript("window.scrollTo(0, 0)");
		} catch (Exception e) {
			System.out.println("Can't scroll the element into view");
			throw new NullPointerException();
		}
	}

	public void scrollBottom(WebDriver wd) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) wd;
			je.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			System.out.println("Can't scroll the element into view");
			throw new NullPointerException();
		}
	}

	public void scrollIntoView(WebDriver wd, WebDriverWait wait, WebElement element) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) wd;
			je.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			try {
				element.getLocation();
				JavascriptExecutor je = (JavascriptExecutor) wd;
				je.executeScript("arguments[0].scrollIntoView(true);", element);
			} catch (Exception e1) {
				System.out.println("Can't scroll the element into view");
				throw new NullPointerException();
			}

		}
	}

	public void mouseHoverJScript(WebDriver wd, WebElement HoverElement) {
		try {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "
					+ "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) wd).executeScript(mouseOverScript, HoverElement);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
	}

	public void onClickJScript(WebDriver wd, WebDriverWait wait, WebElement ClickElement) {
		try {
			// wait.until( new Predicate<WebDriver>() { public boolean
			// apply(WebDriver wd) { return
			// ((JavascriptExecutor)wd).executeScript("return
			// document.readyState").equals("complete"); } } );
			wait.until(ExpectedConditions.visibilityOf(ClickElement));
			wait.until(ExpectedConditions.elementToBeClickable(ClickElement));

			JavascriptExecutor executor = (JavascriptExecutor) wd;
			executor.executeScript("arguments[0].click();", ClickElement);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while clicking" + e.getStackTrace());
		}
	}

	/*
	 * @modified Bruce.ThuyDo 04/13/2018
	 * 
	 */
//	@AfterClass
	public void closeBrowser(WebDriver wd) {
		try {
			if (wd != null) {
				// log("Step Final: capture screenshot and close the browser.");

				String className = this.getClass().getName();
				try {
					CommonPM commonPM = new CommonPM();
					commonPM.takeScreenshotSmoke(wd, className);

				} catch (Exception e) {
					System.out.println("Unable to take screenshot before closing browser.");
				}
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
				TimeUnit.SECONDS.sleep(WAIT_SHORT_SECONDS);
				// wd.close();
				wd.quit();
				wd = null;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error occurred while closing browser." + e.getStackTrace());
		} finally {

		}
	}

	public static Duration differencetime(String text, String text2) throws Exception {
		DateTimeFormatter format = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

		LocalTime time1 = LocalTime.parse(text, format);
		LocalTime time2 = LocalTime.parse(text2, format);

		Duration duration = Duration.between(time1, time2);
		return duration;
	}

	public static void log(String text) throws Exception {
		Reporter.log(text);
		currentLoggedStep = text;
		// testReport.log(Status.INFO,text);
		testReport.log(com.aventstack.extentreports.Status.PASS, currentLoggedStep);
	}

	public static void log2(String text) throws Exception {
		currentLoggedStep = text;
		testReport.log(com.aventstack.extentreports.Status.PASS, currentLoggedStep);
	}

	public static void logFail(String text) throws Exception {
		testReport.log(com.aventstack.extentreports.Status.FAIL, text);
		// Assert.fail(text);
	}

	public static void logExceptionFail(String text) throws Exception {
		logFail("Script Failed. " + text);
		AssertJUnit.fail(text);
	}

	public static void logFunctionalFail(String text) throws Exception {
		logFail("Functional Failed. " + text);
	}

	public static void logExtentReportFail(String text) {
		testReport.log(com.aventstack.extentreports.Status.FAIL, text);
	}

	public static void openNewTab(WebDriver wd) throws Exception {
		((JavascriptExecutor) wd).executeScript("window.open()");
	}

	public static void reportLogScreenshot(String absolute) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<img class='report-img' height='150' width='150' data-featherlight='file:///" + absolute
				+ "' src='file:///" + absolute + "'>");
	}

	public static WebElement findElementByText(WebDriver wd, String text, @Optional("link") String type) {
		return wd.findElement(By.xpath("//" + type + "[.='" + text + "']"));
	}

	/*
	 * public static void waitUntilJSReady(WebDriver wd) { WebDriverWait wait = new
	 * WebDriverWait(wd,15); JavascriptExecutor jsExec = (JavascriptExecutor) wd;
	 * //Wait for Javascript to load ExpectedCondition<Boolean> jsLoad = driver ->
	 * ((JavascriptExecutor) wd)
	 * .executeScript("return document.readyState").toString().equals("complete");
	 * 
	 * //Get JS is Ready boolean jsReady = (Boolean)
	 * jsExec.executeScript("return document.readyState").toString().equals(
	 * "complete");
	 * 
	 * //Wait Javascript until it is Ready! if(!jsReady) {
	 * System.out.println("JS in NOT Ready!"); //Wait for Javascript to load
	 * wait.until(jsLoad); } else { System.out.println("JS is Ready!"); } }
	 */

	public boolean waitForJStoLoad(WebDriver wd, WebDriverWait wait) {

		wait = new WebDriverWait(wd, WAIT_PAGE_LOAD_COMPLETED_SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) wd;

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/*
	 * private void SelectExpectedValue(WebElement el, String s) { Select oSelect =
	 * new Select(el); oSelect.selectByValue(s); //select expected value }
	 */

	public static void sleep(long timeout) throws Exception {
		try {
			Thread.sleep(timeout * 1000);
		} catch (Exception e) {
			log("Exception is sleep method" + e.getMessage());
		}
	}

	public static void checkPageIsReady(WebDriver wd) {
		JavascriptExecutor js = (JavascriptExecutor) wd;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
			return;
		}
		for (int i = 0; i < WAIT_PAGE_LOAD_COMPLETED_SECONDS; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
				break;
			}
		}
	}

	@AfterMethod
	protected void afterMethod(ITestResult result) throws Exception {
		/*
		 * Date now = new Date(); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("MM_yyyy"); String fileNameMonth = dateFormat.format(now);
		 * SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM_dd_yyyy"); String
		 * fileNameDate = dateFormat1.format(now); String className =
		 * this.getClass().getName(); String imgName = CAPTURE_SCREEN_FOLDER_PATH +
		 * fileNameMonth + "\\" + fileNameDate +
		 * "\\ScreenShotsSmoke\\" + className + ".jpg";
		 * 
		 * if (result.getStatus() == ITestResult.FAILURE) { testReport.log(Status.FAIL,
		 * "Test Failed " + testReport.addScreenCaptureFromPath(imgName) ); } else if
		 * (result.getStatus() == ITestResult.SUCCESS){ testReport.log(Status.PASS,
		 * "Test passed "+ testReport.addScreenCaptureFromPath(imgName)); } else {
		 * testReport.log(Status.SKIP, "Test skipped " +
		 * testReport.addScreenCaptureFromPath(imgName) ); }
		 */
		reporter.flush();
	}

	// @Parameters({"browsers","testsuite"})
	// @AfterSuite
	// protected void afterSuite(@Optional("chrome") String browsers,
	// @Optional("REG") String testsuite) throws Exception{
	// String mailContent = "<div>"
	// + "Dear All, <br><br>"
	// + "The automation test have completed in " + Controls.appConfig.appBaseUrl
	// + " from the browser " + browsers + " for the test suite " + testsuite
	// + ". Attached is the automation test results.<br> <br>"
	// + "Thanks,<br> Automation Team</div>"; // a html
	// String sTestNGReport =
	// Paths.get("./test-output/emailable-report.html").toString();

	// ArrayList<String> attachFile = new ArrayList<String>();
	// attachFile.add(sReportFile);
	// attachFile.add(sTestNGReport);

	// ArrayList<String> mailTo = new ArrayList<String>();

	/*
	 * for (String mail: Controls.mailConfig.mailTos) { mailTo.add(mail); }
	 */

	// mailTo.add("thingoc@lampart-vn.com");

	// SendMailAttachedFile sendMail = new SendMailAttachedFile();
	// sendMail.sendEmailWithAttachments(mailContent,attachFile,mailTo);
}
// }
