package stepDefinations;


import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.pages.PageObject;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchcustomerPage;


public class steps extends BaseClass {
	
	@Before
	public void setup() throws IOException
	{
		//Logger setup
		Logger=LogManager.getLogger("nopCommerce");   //Added Logger
		//BasicConfigurator.configure(); 
		//PropertyConfigurator.configure("log4j.properties");   //Added Logger
		
		//Reading Properties
		configProp= new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
		driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
		System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
		driver = new InternetExplorerDriver();
		}
		
		
		
		
		Logger.info("******** Launching browser ********");
	}
	
	
	
	@Given("^User launch chrome browser$")
	public void user_launch_chrome_browser(){
		
		
		lp=new LoginPage(driver);
		/*
		 * getDriver().get("www.google.com"); getDriver().manage().window().maximize();
		 */
	}
	
	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url){
	    // Write code here that turns the phrase above into concrete actions
		
		Logger.info("******** Opening URL ********");
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String Password) {
		Logger.info("******** Providing login details ********");
	    lp.setUsername(email);
	    lp.setPassword(Password);
	}

	@When("^Click on Login$")
	public void click_on_Login() throws InterruptedException {
		Logger.info("******** Started login ********");
	    lp.clickLogin();
	    Thread.sleep(3000);
	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title) {
	    if(driver.getPageSource().contains("Login was unsuccessful.")) {
	    	driver.close();
	    	Logger.info("******** Login passed ********");
	    	Assert.assertTrue(false);
	    }else {
	    	Logger.info("******** Login failed ********");
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	}

	@When("^User click on Log out link$")
	public void user_click_on_Log_out_link() {
		Logger.info("******** Click on logout link ********");
	    lp.clickLogout();
	}

	@Then("^close browser$")
	public void close_browser() {
		Logger.info("******** Closing browser ********");
	    driver.quit();
	}
	
	
	
	// Customer feature step definitions...
	@Then("^User can view Dashboard$")
	public void user_can_view_Dashboard() {
		addCust= new AddcustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}

	@When("^User clicks on Customers menu$")
	public void user_clicks_on_Customers_menu() throws InterruptedException {
	   Thread.sleep(6000);
	   Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	   addCust.clickOnCustomersMenu();
	   
	}

	@When("^click on customers Menu Item$")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		 Thread.sleep(3000);
		 addCust.clickOnCustomersMenuItem();
	}

	@When("^click on Add new button$")
	public void click_on_Add_new_button() throws InterruptedException {
		 addCust.clickOnAddNew();
		 Thread.sleep(2000);
	}

	@Then("^User can view Add new customer page$")
	public void user_can_view_Add_new_customer_page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
	}

	@When("^user enter customer info$")
	public void user_enter_customer_info() throws InterruptedException {
		
	  Logger.info("******** Adding new customer ********");
	  Logger.info("********Providing customer details ********");
	  String email=randomestring()+"@gmail.com";
	  addCust.setEmail(email);
	  addCust.setPassword("test123");
	  //Registered - default
	  //The customer cannot be in both 'Guests' and 'Registered' customer roles
	  //Add the customer to 'Guests' or 'Registered' customer role
	  addCust.setCustomerRoles("Guest");
	  Thread.sleep(3000);
	  
	  addCust.setManagerofVendor("Vendor 2");
	  addCust.setGender("Male");
	  addCust.setFirstName("Pavan");
	  addCust.setLastName("Kumar");
	  addCust.setDob("7/05/1985"); //Format : D/MM/YYYY
	  addCust.setCompanyName("busyQA");
	  addCust.setAdminContent("This is for testing.....");
	  
	  
	  
	  
	}

	@When("^Click on save button$")
	public void click_on_save_button() throws InterruptedException {
		Logger.info("******** Saving customer data ********");
	    addCust.clickonSave();
	    Thread.sleep(2000);
	}

	@Then("^user can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}
	
	
	
	//steps for searching a customer using Email ID
	@When("^Enter search details as email \"([^\"]*)\" first name \"([^\"]*)\" last name \"([^\"]*)\"$")
	public void enter_search_details_as_email_first_name_last_name(String email, String firstName, String lastName) {
		
		Logger.info("********Searching customer by email or name ********");
		searchCust=new SearchcustomerPage(driver);
		searchCust.setinput(email,firstName,lastName);
	}


	@When("^Click on search button$")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("^User should found details in the Search table$")
	public void user_should_found_Email_in_the_Search_table() {
	    boolean status=searchCust.searchCustomerByEmail();
	    Assert.assertEquals(true, status);
	}

}
