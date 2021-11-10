package automationFramework;

import org.openqa.selenium.By;

public class Constants {
	
	public static String homeURL = "https://test.salesforce.com";
	public static String userName = "uktrialjs.thwythpd5pgu@trial.sage.com.sandbox1";
	public static String password = "password!234!";
	
	public static String pathToGeckoDriver = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
	
	public static int timeOut_5Seconds = 5;
	public static int timeOut_15Seconds = 15;
	
	//Locator constants
	public static String userNameField = "username";
	public static String passwordField = "password";
	public static String LoginButton = "Login";
	public static String AddTabButton = "AllTab_Tab";
	public static String WorkforceExperience = "Workforce eXperience";
	public static String PerformanceDropdown = "/services/a2z4K000000YUQPQA4/processes";
	public static By ObjectivesLocator = By.xpath("//a[@title='Objectives']");
	public static By NewObjectiveButton = By.xpath("//div[@id='process-views']/ui-view/div[3]/div/div/div[2]/wx-button/div/wx-button-open/button['New']");
	
	public static String ModalDialog = "modal-dialog";
	public static By NewObjectiveDialog = By.xpath("//form[@name='formmodal']");
	public static String ObjNameTextBox = "name";
	public static String ObjDescriptionTextArea = "description";
	public static String ObjMeasureTextArea = "measure";
	public static By ObjStrategicGoalDropdown = By.xpath("//select[@operation-options='strategicObjective']");
	public static By ObjGoalOperations = By.xpath("//option[@label='Operations']");
	public static By ObjContributesToDropdown = By.xpath("//select[@operation-options='contributesTo']");
	public static By ObjIntroduceNewProductRange = By.xpath("//option[@label='Introduce new product range']");
	public static String StartDateParent = "startdate";
	public static String EndDateParent = "endDate";
	public static String NextReviewDateParent = "nextreviewdate";
	public static String DateInput = "dateinput"; 
	public static String WeightInput = "weight";
	
	public static By SaveObjectiveButton = By.xpath("../../div[3]/div[1]/button['Save'][not(@disabled)]");;
	public static By NewObjectiveModalHeader = By.xpath("//h4[contains(.,'New Objective')]");
	public static By CancelObjectiveButton = By.xpath("../../div[3]/div[1]/button['Cancel']");
	public static By ConfirmCancelModalHeader = By.xpath("//h4[contains(.,'Are you sure')]");
	public static By ConfirmCancelOKButton = By.xpath("../../../..//button['ok']");
	
	public static By DraftTab = By.xpath("//wx-tabs/div/ul/li['Draft']");
	public static By SObjStatusField = By.xpath("../div[1]/table/tbody/tr/td[1]");
	public static By SObjStartDateField = By.xpath("../div[1]/table/tbody/tr/td[2]");
	public static By SObjEndDateField = By.xpath("../div[1]/table/tbody/tr/td[4]");
	public static By SObjReviewDateField = By.xpath("../div[1]/table/tbody/tr/td[3]");
	public static By ViewDetailsField = By.xpath("../div[2]/div[1]/span[1]");
	public static By SObjDescriptionField = By.xpath("../div[2]//p[1]");
	public static By SObjMeasureField = By.xpath("../div[2]//p[2]");
	
	public static String DateFormat = "dd/MM/YYYY";
	
	public static Objective testObj_1 = new Objective("Test new product", "Build automation suite for new product range",
			"%coverage, pass/fail status", "Operations", "Introduce new product range", 
			"10/11/2021", "10/05/2022", "10/02/2022", "50", false, false);
		
}
