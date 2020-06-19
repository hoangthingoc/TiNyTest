package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageCommon.TestBase;

public class HomePO extends TestBase{
	public WebDriver wd;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//i[@class='icon-people-setting']")
	public WebElement peopleSettingIcon;	//People Setting icon

	public HomePO() {

	}

	public HomePO(WebDriver wd) {
		this();
		this.wd = wd;
		PageFactory.initElements(wd, this);
		this.wait = new WebDriverWait(wd, WAIT_SECONDS);
	}
}
