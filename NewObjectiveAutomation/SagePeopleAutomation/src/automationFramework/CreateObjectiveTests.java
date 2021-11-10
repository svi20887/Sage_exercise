package automationFramework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class CreateObjectiveTests 
{
	WebDriver driver;	
	SageDriver sageDriver = new SageDriver();
	
  @Test
  public void CreateNewObjective() 
  {
	  sageDriver.AddTab(Constants.WorkforceExperience);
	  sageDriver.GoToObjectivesView();
	  sageDriver.CreateNewObjective(Constants.testObj_1);
	  sageDriver.SaveNewObjective();
	  	  
	  Assert.assertTrue(Constants.testObj_1.equals(sageDriver.GetSavedObjective(Constants.testObj_1.objName)));
  }
  
  @Test
  public void CancelCreateObjective()
  {
	  sageDriver.AddTab(Constants.WorkforceExperience);
	  sageDriver.GoToObjectivesView();
	  sageDriver.CreateNewObjective(Constants.testObj_1);
	  sageDriver.CancelNewObjective();
	  
	  Assert.assertFalse(sageDriver.IsObjectiveSaved(Constants.testObj_1.objName));
  }
  
  @BeforeMethod
  public void beforeMethod() 
  {
	  System.setProperty("webdriver.gecko.driver", Constants.pathToGeckoDriver);
		driver = new FirefoxDriver();
		sageDriver.webDriver = driver;
		driver.manage().window().maximize();
		driver.get(Constants.homeURL);
		sageDriver.Login(Constants.userName, Constants.password);
  }

  @AfterMethod
  public void afterMethod() 
  {
	  driver.close();
  }  

}
