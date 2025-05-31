package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

// one class is single test case
public class TC001_AccountRegistrationTest extends TestBaseClass
{
	// Extend TestBaseClass here to get access the methods
	
	// removed @BeforeClass and @AfterClass method from here and add in TestBaseClass to achive re-usability
	
	@Test(groups = "regression")
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		try
		{
		HomePage hp = new HomePage(driver); // create object of pageObject HomePage class to access the methods
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on Registration link");

		
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		
		logger.info("Providing custoner details");

		arp.setFirstName(randomeString().toUpperCase()); // Random string with uppercase letters
		arp.setLastName(randomeString().toUpperCase());
		
		//arp.setEmail("abc@gamil.com"); // randomly generate the email static email not working
		arp.setEmail(randomeString()+"@gmail.com");  // random email generating
		arp.setTelephone("1234567890");
		
		// for password store randomeAlphaNumberic value in one variable and pass in pswd and confirm pswd
		String password = randomeAlphaNumberic();
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		
		arp.checkPolicy();
		arp.clickContinue();
		
		logger.info("Validating Expected message...");
		String conMsg = arp.getConfirmationMsg();
		Assert.assertEquals(conMsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test failed..");
			//logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("Finished TC001_AccountRegistrationTest");

	}
	
	// removed some randomStringMethod from here and add in TestBaseClass to achieve re-usability

	
	
}
