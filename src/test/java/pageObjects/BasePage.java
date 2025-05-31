package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage // parent of all page object classes to achive re usability
{
	WebDriver driver;
	
	public BasePage(WebDriver driver) // Parent class Constructor 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
