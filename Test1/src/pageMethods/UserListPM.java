package pageMethods;

import org.apache.bcel.classfile.Constant;
//import org.apache.bcel.classfile.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.LoginPO;
import pageObjects.UserListPO;

//import com.mysql.cj.Constants;

import pageObjects.UserListPO;

public class UserListPM extends UserListPO{

	
	public void clickAddPeopleMenu(WebDriver wd, WebDriverWait wait, UserListPO userListPO) throws Exception 
	{
		clickOnButton(wd,wait,userListPO.addPeopleMenu);
	}
	
	public void setFirstName1TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.firstName1TextBox, value);
	}
	
	public void setFirstName2TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.firstName2TextBox, value);
	}
	
	public void setFirstName3TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.firstName3TextBox, value);
	}

	public void setLastName1TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.lastName1TextBox, value);
	}
	
	public void setLastName2TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.lastName2TextBox, value);
	}
	
	public void setLastName3TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.lastName3TextBox, value);
	}
	
	public void setEmail1TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.email1TextBox, value);
	}
	
	public void setEmail2TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.email2TextBox, value);
	}
	public void setEmail3TextBoxValue(UserListPO userListPO, String value) throws Exception {
		setTextboxValue(userListPO.email3TextBox, value);
	}
	
	public void clickAddPeopleButton(WebDriver wd, WebDriverWait wait, UserListPO userListPO) throws Exception 
	{
		clickOnButton(wd,wait,userListPO.addPeopleButton);
	}
	
	public void verifyCongratulationTextExist(UserListPO userListPO, String message) throws Exception 
	{		
		String actual = userListPO.congratulationsText.getText().toString().replace("check_circle", "").trim();
//		String actual = userListPO.congratulationsText.getText().toString().trim();
//		String actual = userListPO.congratulationsText.getAttribute("Congratulations");
		assertExtentEquals(actual, message);
	}

}
