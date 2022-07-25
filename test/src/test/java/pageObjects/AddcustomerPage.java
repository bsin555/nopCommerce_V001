package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
public WebDriver ldriver;
	
	public AddcustomerPage(WebDriver rdriver) {
		ldriver = rdriver ;
		PageFactory.initElements(rdriver, this);
	}
	
	
	By lnkCustomer_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//p[contains(text(),' Customers')]");
	
	By btnAddnew = By.xpath("//a[@href='/Admin/Customer/Create']");
	
	By txtEmail= By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//input[@aria-describedby='SelectedCustomerRoleIds_taglist']");
	
	By lstitemAdministrators=By.xpath("//option[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//option[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//option[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//option[contains(text(),'Vendors')]");
	
	By drpmgrofVendor=By.xpath("//select[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFemaleGender=By.id("Gender_Female");
	
	By txtFirstName =By.xpath("//input[@id='FirstName']");
	By txtLastName =By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName=By.xpath("//input[@id='Company']");
	
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	
	//Action methods
	
	public String getPageTitle() {
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomer_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		ldriver.findElement(btnAddnew).click();
		
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException
	{
		
		if(!role.equals("Vendors")) //If Role not vendor should not delete register as 
		{
			ldriver.findElement(By.xpath("//span[contains(text(),'Registered')]")).click();
		}
		
		Thread.sleep(3000);
		ldriver.findElement(txtcustomerRoles).click();
		
		WebElement listitem;
		
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Guests"))
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		else if(role.equals("Registered"))
		{
			listitem=ldriver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors"))
		{
			listitem=ldriver.findElement(lstitemVendors);
		}
		else
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		
		//listitem.click();
		//Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click()", listitem);
	}
	
	
	public void setManagerofVendor(String value)
	{
		Select drp= new Select (ldriver.findElement(drpmgrofVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click(); 
			
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
			
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();
			
		}
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickonSave()
	{
		ldriver.findElement(btnSave).click();
	}

}
