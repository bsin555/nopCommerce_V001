package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.waitHelper;

public class SearchcustomerPage {
	
public WebDriver ldriver;
waitHelper waithelper;
public String fname;
public String lname;
public String email;

	public SearchcustomerPage(WebDriver rdriver) {
		ldriver = rdriver ;
		PageFactory.initElements(ldriver, this);
		waithelper=new waitHelper(ldriver);
	}
	
	
	By SearchEmail_text = By.xpath("//input[@id='SearchEmail']");
	By SearchFirstName_text = By.xpath("//input[@id='SearchFirstName']");

	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement searchCustomer;
	
	@FindBy(how = How.XPATH, using = "//table[@class='table table-bordered table-hover table-striped dataTable no-footer']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@class='table table-bordered table-hover table-striped dataTable no-footer']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@class='table table-bordered table-hover table-striped dataTable no-footer']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void setEmail(String email)
	{
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		waithelper.WaitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		waithelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void setinput(String iemail, String ifname, String ilname)
	{email=iemail;
	fname=ifname;
	lname=ilname;
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
		txtFirstName.sendKeys(fname);
		txtLastName.sendKeys(lname);
	}
	public void clickSearch()
	{
		searchCustomer.click();
	}
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() {
		return(tableColumns.size());
	}
	
	public boolean searchCustomerByEmail() {
		
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			
			if(!email.equals("")) {
			String emailid=table.findElement(By.xpath("//table[@class='table table-bordered table-hover table-striped dataTable no-footer']//tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals(email)) {
				flag=true;
			}
			else
				flag=false;
			}
		
		if(!fname.equals("") || !lname.equals("")) {
			String name=table.findElement(By.xpath("//table[@class='table table-bordered table-hover table-striped dataTable no-footer']//tbody/tr["+i+"]/td[3]")).getText();
			System.out.println(name);
			String names[] = name.split(" ");
			if((names[0].equals(fname)) && names[1].equals(lname)) {
				flag=true;
			}
			else
				flag=false;
			}
	
	
	}
		
		return flag;
		
	}

}
