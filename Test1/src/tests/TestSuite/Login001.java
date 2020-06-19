package tests.TestSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import pageCommon.CommonPM;
import pageCommon.Constant;
import pageCommon.RetryAnalyzer;
import pageMethods.HomePM;
import pageObjects.HomePO;
import pageMethods.LoginPM;
import pageObjects.LoginPO;
import pageObjects.UserListPO;
import pageMethods.UserListPM;

/**
 * 
 * @author Ngoc Hoang <ngocht.hcmus@gmail.com>
 *
 */

public class Login001 extends pageCommon.TestBase {
	LoginPM pmLogin = new LoginPM();
	CommonPM commonPM = new CommonPM();
	LoginPM loginPM = new LoginPM();
	HomePM homePM = new HomePM();
	UserListPM userListPM = new UserListPM();
	
	public Login001() {
		 sTestCaseName = "Login001: Verify add people successfull.";
		 sTestSuiteFileName = "Login.xlsx";
		 iTestCaseRowOnTestSuiteFile = 1;
		 testSuiteURL =	Constant.TestSuiteUrl.LOGIN;

	}

	@Test(dataProvider = "scenarioProvider", retryAnalyzer = RetryAnalyzer.class)
	public void ChoTotLogin_001(WebDriver wd, WebDriverWait wait) throws Exception {
		try {
			String dUrl = dataTCCurrent.get(Constant.KeysValueData.URL_KEY);
			String dUserName = dataTCCurrent.get(Constant.KeysValueData.USERNAME_KEY);
			String dPassword = dataTCCurrent.get(Constant.KeysValueData.PASSWORD_KEY);
			String dFirstName1= dataTCCurrent.get(Constant.KeysValueData.FIRSTNAME1_KEY);
			String dFirstName2= dataTCCurrent.get(Constant.KeysValueData.FIRSTNAME2_KEY);
			String dFirstName3= dataTCCurrent.get(Constant.KeysValueData.FIRSTNAME3_KEY);
			String dLastName1= dataTCCurrent.get(Constant.KeysValueData.LASTNAME1_KEY);
			String dLastName2= dataTCCurrent.get(Constant.KeysValueData.LASTNAME2_KEY);
			String dLastName3= dataTCCurrent.get(Constant.KeysValueData.LASTNAME3_KEY);
			String dEmail1= Constant.generatingRandomString() + dataTCCurrent.get( Constant.KeysValueData.EMAIL1_KEY);
			String dEmail2= Constant.generatingRandomString() + dataTCCurrent.get(Constant.KeysValueData.EMAIL2_KEY);
			String dEmail3= Constant.generatingRandomString() + dataTCCurrent.get(Constant.KeysValueData.EMAIL3_KEY);
			String dMessage1= dataTCCurrent.get(Constant.KeysValueData.MESSAGE1_KEY);

			log("TC Name :  Verify login successful when enter a valid loginID and password values");
			
			//=============OPEN THE BROWSER AND GO TO THE WEBSITE=============
			log("Step 1: Navigate to homepage and go to the Login function");
			loginPM.GoToThePage(wd, dUrl);
			
			//===================LOGIN=======================
			log("Step 2: Login on the website");
			checkPageIsReady(wd);	
			LoginPO loginPO = PageFactory.initElements(wd, LoginPO.class);
			
			log("Step 2.1: Click on Login Link:");
			loginPM.clickSignInLink(wd, wait, loginPO);

			log("Step 2.2: Enter a valid username");
			loginPM.setUserNameTextboxValue(loginPO, dUserName);

			log("Step 2.3: Enter a valid password");
			loginPM.setPasswordTextboxValue(loginPO, dPassword);

			log("Step 2.4: Click on [Sign in] button");
			loginPM.clickSignInButton(wd, wait, loginPO);
			
			//====================HOME PAGE==============
			checkPageIsReady(wd);
			HomePO homePO = PageFactory.initElements(wd, HomePO.class);
			
			log("Step 3: Click on People Setting icon");
			homePM.clickPeopleSettingIcon(wd, wait, homePO);
			
			//=================USERS LIST==============
			checkPageIsReady(wd);
			UserListPO userListPO = PageFactory.initElements(wd, UserListPO.class);
		
			log("Step 4.1: Click on Add People menu");
			userListPM.clickAddPeopleMenu(wd, wait, userListPO);

			
			log("Step 4.2: Add the 1st user into list");
			userListPM.setFirstName1TextBoxValue(userListPO, dFirstName1);
			userListPM.setLastName1TextBoxValue(userListPO, dLastName1);
			userListPM.setEmail1TextBoxValue(userListPO, dEmail1);

			log("Step 4.3: Add the 2nd user into list");
			userListPM.setFirstName2TextBoxValue(userListPO, dFirstName2);
			userListPM.setLastName2TextBoxValue(userListPO, dLastName2);
			userListPM.setEmail2TextBoxValue(userListPO, dEmail2);
			
			log("Step 4.4: Add the 3rd user into list");
			userListPM.setFirstName3TextBoxValue(userListPO, dFirstName3);
			userListPM.setLastName3TextBoxValue(userListPO, dLastName3);
			userListPM.setEmail3TextBoxValue(userListPO, dEmail3);
			
			log("Step 4.5: Click on Add People button");
			userListPM.clickAddPeopleButton(wd, wait, userListPO);
			
			
			//==================VERIFY=======================
			
			log ("Step 5: Verify Congratulation display");
			userListPM.verifyCongratulationTextExist(userListPO, dMessage1);
			
		} catch (Exception ex) {
			logExceptionFail(ex.getMessage());
			
		} finally {
			closeBrowser(wd);
		}
	}
}

