package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) { txtFirstname.sendKeys(fname);}
	public void setLastName(String lname) { txtLastName.sendKeys(lname);}
	public void setEmail(String email) { txtEmail.sendKeys(email);}
	public void setTelephone(String tele) { txtTelephone.sendKeys(tele);}
	public void setPassword(String pwd) { txtPassword.sendKeys(pwd);}
	public void setConfirmPassword(String cpwd) { txtConfirmPassword.sendKeys(cpwd);}
	public void checkPolicy() { chkPolicy.click();}
	public void clickContinue() 
	{ 
		//solution 1
		btnContinue.click();
		
//		//solution 2
//		btnContinue.submit();
//		
//		//solution 3
//		Actions act=new Actions(driver);
//		act.moveToElement(btnContinue).click();
//		
//		//solution 4
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", btnContinue);
//		
//		//solution 5
//		btnContinue.sendKeys(Keys.RETURN);
//		
//		//solution 6
//		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg() 
	{
		try 
		{
			return(msgConfirmation.getText());
		} 
		catch (Exception e) 
		{
			return (e.getMessage());
		}
	}
}




