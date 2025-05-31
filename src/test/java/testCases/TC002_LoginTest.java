package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends TestBaseClass
{
	@Test(groups = {"sanity","master"})
	public void verify_login() 
	{
		logger.info("**** TC002 Login test started ***");
		
		try
		{
		// HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();	
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setLoginEmail(p.getProperty("email"));
		lp.setLoginPassword(p.getProperty("password"));	
		lp.clickLogin();
		System.out.println("Clicked on login btn..........");
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage= macc.isMyAccountPageExist();
		System.out.println("My account is displayed..........");

		
		Assert.assertEquals(targetPage, true,"Login Fail");
		}
		catch (Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("****** Finished TC002_LoginTest *****");
	
	
	}
}
