import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestScript {
	static WebElement element;
	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","./resources/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://stackoverflow.com/");
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		element = driver.findElement(
				By.xpath("//a[contains(.,'Browse questions')]"));
		js.executeScript("arguments[0].scrollIntoView();",element);
		js.executeScript("arguments[0].click();",element);
		
		element = driver.findElement(By.xpath("//a[@id='nav-tags']"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		
		List<WebElement> tags = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='tags-browser']//div[contains(@class,'s-card')]//a[contains(@class,'post-tag')]")));
		
		String tagName = tags.get(2).getText();
		System.out.println("Third Popular TagName : "+ tagName);
		
		
		element = driver.findElement(By.xpath("//div[@id='tags-browser']//div[contains(@class,'s-card')][3]//a[contains(@title,'last 24')]"));
		String newQuestions=wait.until(ExpectedConditions.visibilityOf(element)).getText().split(" ")[0];

        System.out.println("New Questions Asked in last 24 hours : "+newQuestions);
	}

}
