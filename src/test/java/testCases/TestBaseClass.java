package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestBaseClass 
{
	public static WebDriver driver; // declare WebDriver class (to make common variable later we will declare it static)
	public Logger logger; // declare Logger classLog4j
	public Properties p;  // declare properties class
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"sanity","master","Regression"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		// load log4j2 in TestBaseClass
		logger= LogManager.getLogger(this.getClass()); // this variable responsible for to generate the logs
		
		// if execution environment is remote from selenium grid use below code
		if (p.getProperty("execution_evn").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			// os
			if (os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch (br.toLowerCase()) 
			{
			case "chrome": capabilities.setBrowserName(("chrome")); break;
			case "edge": capabilities.setBrowserName(("MicrosoftEdge")); break;
			case "firefox": capabilities.setBrowserName(("firefox")); break;
			default: System.out.println("No matching brower"); return;
			}
			
			driver= new RemoteWebDriver(new URL("http://192.168.1.224:4444"),capabilities);
		}
		
		// if execution environ ment is local from selenium grid use below code
		if (p.getProperty("execution_evn").equalsIgnoreCase("local")) 
		{
			// load browsers from master.xml to set run time bowser in setUp method
			switch (br.toLowerCase()) 
			{
				case "chrome" : driver=new ChromeDriver(); break;
				case "edge" : driver=new EdgeDriver(); break;
				case "firefox" : driver=new FirefoxDriver(); break;
				default : System.out.println("Invalid browser name.."); return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//driver.get("https://tutorialsninja.com/demo/"); // we can access this from properties file now
		driver.get(p.getProperty("appURL")); // reading URL from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = {"sanity","master", "Regression"})
	public void tearDown()
	{
		driver.close();
	}
	
	@SuppressWarnings("deprecation")
	public String randomeString() 
	{
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	@SuppressWarnings("deprecation")
	public String randomeNumber() 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomeAlphaNumberic() 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return (generateString+"@"+generatedNumber);
	}

	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
