package pageMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageCommon.Constant;
import pageObjects.LoginPO;


public class LoginPM extends LoginPO{

	public void clickSignInLink(WebDriver wd, WebDriverWait wait, LoginPO loginPO) throws Exception 
	{
		clickOnButton(wd,wait,loginPO.signInLink);
	}
	public void setUserNameTextboxValue(LoginPO loginPO, String value) throws Exception {
		setTextboxValue(loginPO.userNameTextBox, value);
	}

	public void setPasswordTextboxValue(LoginPO loginPO, String value) throws Exception {
		setTextboxValue(loginPO.passwordTextBox, value);
	}
	
	public void clickSignInButton(WebDriver wd, WebDriverWait wait, LoginPO loginPO) throws Exception 
	{
		clickOnButton(wd,wait,loginPO.signInButton);
	}
	
	

	
//	public void VerifyErrorMessage1Label(LoginPO loginPO, String value) throws Exception {
//		String actual = loginPO.errorMessage1Label.getText().toString().trim();
//		assertExtentEquals(actual, value);
//	}
//	
//	public void VerifyErrorMessage2Label(LoginPO loginPO, String value) throws Exception {
//		String actual = loginPO.errorMessage2Label.getText().toString().trim();
//		assertExtentEquals(actual, value);
//	}
//	
//	public void VerifyErrorMessage3Label(LoginPO loginPO, String value) throws Exception {
//		String actual = loginPO.errorMessage3Label.getText().toString().trim();
//		assertExtentEquals(actual, value);
//	}
	
	
	public void GoToThePage(WebDriver wd, String url) throws Exception 
	{
		wd.get(getURL(url));
		pageCommon.TestBase.checkPageIsReady(wd);
		wd.manage().window().maximize();
	}


	private String getURL(String url)
	{
		String result=null;
		switch (url.trim()) {
		case "1":
			result = Constant.Commons.URL;
			break;
		
		}
	return result;
	}
}




