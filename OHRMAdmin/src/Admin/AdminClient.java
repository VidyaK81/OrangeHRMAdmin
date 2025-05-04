package Admin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AdminClient {
	WebDriver driver;
	AdminUtility AUObj;
	
 @Test(priority=1)
  public void openAdminPage() throws InterruptedException {
	  System.out.println("InTest1");
	  AUObj.loginToOHRM("Admin", "admin123");
	  Thread.sleep(3000);
	  int menuCnt = AUObj.optionCnt();
	  System.out.println("Number of options in Menu:-" + menuCnt);
	  Assert.assertEquals(menuCnt, 12);
	  AUObj.clickonAdmin();
	  Thread.sleep(3000);
  }
  @Test(priority=2)
  public void searchEmpByUserName() throws InterruptedException {
	  System.out.println("In Search");
	  AUObj.searchByUserName("Admin");
	  Thread.sleep(2000);
	  AUObj.countRrcords("Admin as User name");
	  AUObj.resetSearch();
 }
 @Test(priority=3)
 public void searchEmpByUserRole() throws InterruptedException {
	 System.out.println("In Search by role");
	 AUObj.searchByUserRole("Admin");
	 AUObj.countRrcords("Admin as User role");
	 AUObj.resetSearch();
 }
 @Test(priority=4)
 public void searchEmpByStatus() throws InterruptedException
 {
	 System.out.println("In Search by Status");
	 AUObj.searchByUserStatus("Enabled");
	 AUObj.countRrcords("Enabled");
	 AUObj.resetSearch();
 }
  @BeforeTest
  public void beforeTest() {
	  System.out.println("In before Test"); 
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  AUObj = new AdminUtility(driver);
  }
  
  @AfterTest
  public void afterTest() {
	  System.out.println("In after Test"); 
	 driver.close();
  }

}
