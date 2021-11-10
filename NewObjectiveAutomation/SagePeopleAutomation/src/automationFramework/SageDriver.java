package automationFramework;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SageDriver 
{
	public WebDriver webDriver;
	
	public void Login(String userName, String password)
	{
		webDriver.findElement(By.id(Constants.userNameField)).sendKeys(userName);
		webDriver.findElement(By.id(Constants.passwordField)).sendKeys(password);
		webDriver.findElement(By.id(Constants.LoginButton)).click();
	}
	
	public void AddTab(String tabCategory)
	{
		WaitUntilElementFound(By.id(Constants.AddTabButton),Constants.timeOut_5Seconds);
		webDriver.findElement(By.id(Constants.AddTabButton)).click();
		By categoryLocator = By.xpath("//img[@title='" + tabCategory +"']");
		WaitUntilElementFound(categoryLocator, Constants.timeOut_5Seconds);
		webDriver.findElement(categoryLocator).click();
	}

	public void CreateNewObjective(Objective newObj)
	{		
		WaitUntilElementFound(Constants.NewObjectiveButton, Constants.timeOut_15Seconds);
		
		webDriver.findElement(Constants.NewObjectiveButton).click();
		
		WaitUntilElementFound(By.className(Constants.ModalDialog), Constants.timeOut_15Seconds);
		
		webDriver.findElement(By.name(Constants.ObjNameTextBox)).sendKeys(newObj.objName);
		webDriver.findElement(By.name(Constants.ObjDescriptionTextArea)).sendKeys(newObj.description);
		webDriver.findElement(By.name(Constants.ObjMeasureTextArea)).sendKeys(newObj.measure);
		var goalDropdown = new Select(webDriver.findElement(Constants.ObjStrategicGoalDropdown));
		goalDropdown.selectByVisibleText(newObj.strategicGoal);
		var contriDropdown = new Select(webDriver.findElement(Constants.ObjContributesToDropdown));
		contriDropdown.selectByVisibleText(newObj.contributesTo);		
		EnterDateInObjectiveDialog(Constants.StartDateParent, newObj.startDate);
		EnterDateInObjectiveDialog(Constants.EndDateParent, newObj.endDate);
		EnterDateInObjectiveDialog(Constants.NextReviewDateParent, newObj.nextReviewDate);	
		webDriver.findElement(By.name(Constants.WeightInput)).sendKeys(newObj.weight);		
	}

	public void EnterDateInObjectiveDialog(String fieldName, String value)
	{
		var date = webDriver.findElement(By.name(fieldName)).findElement(By.name(Constants.DateInput));
		date.clear();
		date.sendKeys(value);
		new Actions(webDriver).moveToElement(date).moveByOffset(200, 0).click().perform();
	}
	
	public void ScrollToBottom()
	{
		webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
	}
	
	public void CancelNewObjective()
	{
		ScrollToBottom();		
		
		var cancelButtons = webDriver.findElement(Constants.NewObjectiveModalHeader).findElements(Constants.CancelObjectiveButton);
		cancelButtons.get(1).click();
		webDriver.findElement(Constants.ConfirmCancelModalHeader).findElement(Constants.ConfirmCancelOKButton).click();		

		WaitUntilElementInvisible(Constants.ConfirmCancelModalHeader, Constants.timeOut_15Seconds);
		WaitUntilElementInvisible(Constants.NewObjectiveDialog, Constants.timeOut_15Seconds);				
	}
	
	public void SaveNewObjective()
	{
		ScrollToBottom();		
		
		List<WebElement> saveButtons = webDriver.findElement(Constants.NewObjectiveModalHeader).findElements(Constants.SaveObjectiveButton);		
		saveButtons.get(4).click();
				
		WaitUntilElementInvisible(Constants.NewObjectiveDialog, Constants.timeOut_15Seconds);		
	}
	
	public boolean IsObjectiveSaved(String objName)
	{	
		WaitUntilElementFound(Constants.DraftTab, Constants.timeOut_5Seconds);
		webDriver.findElement(Constants.DraftTab).click();				
		return !webDriver.findElements(By.xpath("//span[contains(.,'" + objName +"')]")).isEmpty();
	}
	
	public void GoToObjectivesView()
	{
		WaitUntilElementFound(By.id(Constants.PerformanceDropdown), Constants.timeOut_15Seconds);
		webDriver.findElement(By.id(Constants.PerformanceDropdown)).click();
		webDriver.findElement(Constants.ObjectivesLocator).click();		
	}
	
	public Objective GetSavedObjective(String objName)
	{
		WaitUntilElementFound(Constants.DraftTab, Constants.timeOut_5Seconds);
		webDriver.findElement(Constants.DraftTab).click();
		
		Objective savedObjective = new Objective();
		
		savedObjective.objName = objName;
		
		var savedObj = webDriver.findElement(By.xpath("//span[contains(.,'" + objName +"')]"));
		savedObjective.status = savedObj.findElement(Constants.SObjStatusField).getText();
		savedObjective.startDate = ConvertDateFormat(savedObj.findElement(Constants.SObjStartDateField).getText(), Constants.DateFormat);
		savedObjective.endDate = ConvertDateFormat(savedObj.findElement(Constants.SObjEndDateField).getText(), Constants.DateFormat);
		savedObjective.nextReviewDate = ConvertDateFormat(savedObj.findElement(Constants.SObjReviewDateField).getText(), Constants.DateFormat);
		var details = savedObj.findElement(Constants.ViewDetailsField);
		details.click();
		savedObjective.description = savedObj.findElement(Constants.SObjDescriptionField).getText();
		savedObjective.measure = savedObj.findElement(Constants.SObjMeasureField).getText();	
		
		return savedObjective;		
	}
	
	public String ConvertDateFormat(String toConvert, String format)
	{
		try
		{
		DateFormat sdf = new SimpleDateFormat(format);
		DateFormat sourceFormat = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(sourceFormat.parse(toConvert)).toString();
		}
		catch(ParseException ex)
		{
			System.out.println("Couldnt parse date"+ex.getMessage());
			return null;
		}
	}
	
	public void WaitUntilElementFound(By elementLocator, int timeOutSeconds) 
	{
	WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOutSeconds));

	wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
	}
	
	public void WaitUntilElementInvisible(By elementLocator, int timeOutSeconds) 
	{
	WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOutSeconds));

	wait.until(webDriver -> webDriver.findElements(elementLocator).isEmpty());
	}

}
