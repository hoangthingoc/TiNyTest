package pageMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePO;
import pageObjects.LoginPO;


public class HomePM extends HomePO{
	
//	public void clickLoginNowButton(WebDriver wd, WebDriverWait wait, HomePO homePO) throws Exception 
//	{
//		clickOnButton(wd,wait,homePO.loginNowButton);
//	}
	
	public void clickPeopleSettingIcon(WebDriver wd, WebDriverWait wait, HomePO homePO) throws Exception 
	{
		clickOnButton(wd,wait,homePO.peopleSettingIcon);
	}
	
//	public void clickMenuMoreButton(WebDriver wd, WebDriverWait wait, HomePO homePO) throws Exception 
//	{
//		clickOnButton(wd,wait,homePO.menuMoreButton);
//	}
//
//	public void ClickLoginOrRegisterMenuTab(WebDriver wd, WebDriverWait wait, HomePO homePO) throws Exception 
//	{
//		clickOnButton(wd,wait,homePO.loginOrRegisterMenuTab);
//	}
//	
//	public void ClickSetupProfileMenuTab(WebDriver wd, WebDriverWait wait, HomePO homePO) throws Exception 
//	{
//		clickOnButton(wd,wait,homePO.setupProfileMenuTab);
//	}
//	
//	public void clickPeopleSettingIcon(WebDriver wd, WebDriverWait wait, LoginPO loginPO) throws Exception 
//	{
//		clickOnButton(wd,wait,loginPO.);
//	}
	
}
