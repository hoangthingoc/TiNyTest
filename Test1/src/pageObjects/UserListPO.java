package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageCommon.TestBase;

public class UserListPO extends TestBase{
	public WebDriver wd;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//a[@class='engage-item__sideBarItem___1o21W SideBarItem__sideBarItem___3lsEc']")
	public WebElement addPeopleMenu;
	
	@FindBy(xpath = "//input[@field='firstName' and @refkey=1]")
	public WebElement firstName1TextBox;
	
	@FindBy(xpath = "//input[@field='firstName' and @refkey=2]")
	public WebElement firstName2TextBox;
	
	@FindBy(xpath = "//input[@field='firstName' and @refkey=3]")
	public WebElement firstName3TextBox;
	
	@FindBy(xpath = "//input[@field='lastName' and @refkey=1]")
	public WebElement lastName1TextBox;

	@FindBy(xpath = "//input[@field='lastName' and @refkey=2]")
	public WebElement lastName2TextBox;

	@FindBy(xpath = "//input[@field='lastName' and @refkey=3]")
	public WebElement lastName3TextBox;
	
	@FindBy(xpath = "//input[@field='email' and @refkey=1]")
	public WebElement email1TextBox;
	
	@FindBy(xpath = "//input[@field='email' and @refkey=2]")
	public WebElement email2TextBox;
	
	@FindBy(xpath = "//input[@field='email' and @refkey=3]")
	public WebElement email3TextBox;
	
	@FindBy(xpath = "//div/span[.='Add People']")
	public WebElement addPeopleButton;
	
	@FindBy(xpath = "//div[@class='tu mv3 fw6 f3 flex items-center green']")
	public WebElement congratulationsText;
	
	
	
	public UserListPO(){
		
	}
	
	public UserListPO(WebDriver wd){
		this();
		this.wd = wd;
		PageFactory.initElements(wd, this);
		this.wait=new WebDriverWait(wd, WAIT_SECONDS);
		
	}
	
}
