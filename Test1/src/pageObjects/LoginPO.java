package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageCommon.TestBase;

public class LoginPO extends TestBase{
	public WebDriver wd;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//*/a[@href='/signin']")
	public WebElement signInLink;	//Click on Login Link
	
	@FindBy(xpath = "//*/input[@name='session[email]']")
	public WebElement userNameTextBox;	//Enter the username is an email
	
	@FindBy(xpath = "//*/input[@id='session_password']")
	public WebElement passwordTextBox;	//Enter the password
	
	@FindBy(xpath = "//*/button[@type='submit']")
	public WebElement signInButton;	//Sing in
	

	
	
	
//	@FindBy(xpath = "//*/div[contains(text(),'Phone: Số điện thoại không hợp lệ.')]")
//	public WebElement errorMessage1Label;	//BÁO LỖI NHẬP KHÔNG ĐÚNG SỐ ĐIỆN THOẠI - Enter phone number wrong
//	
//	@FindBy(xpath = "//*/div[contains(.=,'Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại.')]")
//	public WebElement errorMessage2Label;	//BÁO LỖI NHẬP KHÔNG ĐÚNG SỐ ĐIỆN THOẠI - Enter phone number wrong
//	
//	@FindBy(xpath = "//*/div[contains(text(),'Phone: Số điện thoại không hợp lệ. Password: Mật khẩu phải có ít nhất 5 kí tự')]")
//	public WebElement errorMessage3Label;	//BÁO LỖI PASSWORD PHẢI CÓ ÍT NHẤT 5 KÍ TỰ - At least for the password is 5 characters

	
	public LoginPO fill(String Username, String Password) throws Exception {
		setUsernameField(Username);
		setPasswordField(Password);
		return this;
	}
	
	public LoginPO setUsernameField(String usernameValue) throws Exception {
		//waitExpectedElement(driver, wait, username);
		waitElement(userNameTextBox);
		userNameTextBox.sendKeys(usernameValue);
		return this;
	}
	
	public LoginPO setPasswordField(String passwordValue) throws Exception {
		//waitExpectedElement(driver, wait, password);
		waitElement(passwordTextBox);
		passwordTextBox.sendKeys(passwordValue);
		return this;
	}
	
	public LoginPO() {

	}

	public LoginPO(WebDriver wd) {
		this();
		this.wd = wd;
		PageFactory.initElements(wd, this);
		this.wait = new WebDriverWait(wd, WAIT_SECONDS);
	}
}
