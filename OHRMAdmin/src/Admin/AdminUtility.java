package Admin;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminUtility {
	
	WebDriver driver;
	List<WebElement> options;
	
	//Page factory - locators
	@FindBy (name="username") WebElement username;
	@FindBy(name="password") WebElement password;
	@FindBy(css="button[type=\"submit\"]") WebElement btnLogin;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input") WebElement empSearch;
	@FindBy(css="button[type='submit']") WebElement btnEmpSearch;
	@FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]") WebElement btnReset;
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span") WebElement adminMenu;
	@FindBy(xpath = "//label[text()='User Role']/following::div[1]") WebElement userRole;
	@FindBy(xpath = "//label[text()='Status']/following::div[1]") WebElement userStatus;
	@FindBy(css ="span[class = 'oxd-text oxd-text--span']") WebElement recCntMsg;
	public AdminUtility (WebDriver d)
	{
		driver = d;
		PageFactory.initElements(driver,this);
	}
	
	//Login to OrangeHRM
	public void loginToOHRM(String un,String psw)
	{
		username.sendKeys(un);
		password.sendKeys(psw);
		btnLogin.click();
		
	}
	//Count the number of menu options
	public int optionCnt() throws InterruptedException
	{
	    options= driver.findElements(By.cssSelector("li[class='oxd-main-menu-item-wrapper']"));
		return options.size();
	}
	
	//Click on Admin option
    public void clickonAdmin() throws InterruptedException
    {
     adminMenu.click();	
    }
    //Search by user name
    public void searchByUserName(String un)
    {
	   empSearch.sendKeys(un);
	   btnEmpSearch.click();
    }
   
    //Search by role
    public void searchByUserRole(String role) throws InterruptedException
     {
	   userRole.click();
	   List<WebElement> userRoledd = driver.findElements(By.xpath("//div[@role='listbox']//span"));
	   System.out.println("userRole size is"+ userRoledd.size());
	   for(WebElement r : userRoledd)
	   {
		   System.out.println("Inside for" + r.getText());
		   if(r.getText().equals(role))
		   {
			   System.out.println("Inside role if");
			   r.click();
			   break;
		   }
		 }
	    btnEmpSearch.click();
    }
   
   public void searchByUserStatus(String status) throws InterruptedException
   {
	   userStatus.click();
	   List<WebElement> userStatus = driver.findElements(By.xpath("//div[@role='listbox']//span"));
	   System.out.println("userstatus size is"+ userStatus.size());
	   for(WebElement stat : userStatus)
	   {
		   System.out.println("Inside for" + stat.getText());
		   if(stat.getText().equals(status))
		   {
			   System.out.println("Inside status if");
			   stat.click();
			   break;
		   }
		 }
	   btnEmpSearch.click();
	  }
   
   public void countRrcords(String serachBy) {
	   String recText = recCntMsg.getText();
	   System.out.println("Records-" + recText);
	   int leftParaIndex = recText.indexOf("(");
	   int rightParaIndex = recText.indexOf(")");
	   String value = recText.substring(leftParaIndex+1, rightParaIndex);
	   System.out.println("Number of records when search by " +serachBy + ":-" + value);
   }
   public void resetSearch() {
	   btnReset.click();
   }
}
