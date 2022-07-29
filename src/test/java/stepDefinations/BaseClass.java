package stepDefinations;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import pageObjects.SearchcustomerPage;
import pageObjects.AddcustomerPage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddcustomerPage addCust;
	public SearchcustomerPage searchCust;
	public static Logger Logger;
	public Properties configProp;
	
	
	
	
	//Created for generating random string for Unique email
	public static String randomestring() {
		String generatedString1= RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

}
