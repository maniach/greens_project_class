package org.projectClassSample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;

public class LaptopsGrtrThan40k {
	public WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		System.getProperty("webdriver.edge.driver", "browser_drivers/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@DataProvider(name = "product name")
	public Object[][] data() {
		return new Object[][] {
			{"laptop"}
		};
	}
	
	@Test(dataProvider = "product name")
	public void sortingPrices(String searchTxt) throws InterruptedException {
		driver.get("https://www.amazon.in/");
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(searchTxt, Keys.ENTER);
		List<WebElement> table = driver.findElements(By.cssSelector("[class*='data-price']"));
		ArrayList<String> Prices1 = new ArrayList<String>();
		List<Integer> IntList1 = new ArrayList<Integer>();
		for(int i=0;i<table.size();i++){
			Prices1.add(table.get(i).getText().replace("$", "").replace(",", ""));
		}
		System.out.println("prices are "+Prices1);
		System.out.println("Page 1 consists of " +Prices1.size()+ "  price elements");
		Thread.sleep(3000);

		/*converting string list Prcies1 to integer list */
		for (String s: Prices1){
		    IntList1.add(Integer.valueOf(s));  
		}
		System.out.println("Integerlist1 is "+IntList1);    

		/*Array list named as sortedPrices and passing integer list into it to sort*/
		ArrayList<Integer> sortedPrices = new ArrayList<Integer>(IntList1);
		Collections.sort(sortedPrices);
		System.out.println("Sorted list is "+sortedPrices);
		/*clicking on sort drop-down and arranging in ascending order*/  
		Select s = new Select(driver.findElement(By.cssSelector("[id='srp-sortby']")));
		s.selectByValue("1");
		Thread.sleep(5000);
		List<WebElement>table2= driver.findElements(By.cssSelector("[class*='data-price']"));
		List<Integer> IntList2 = new ArrayList<Integer>();
		ArrayList<String> Prices2 = new ArrayList<String>();
		for(int i=0;i<table2.size();i++){
		    Prices2.add(table2.get(i).getText().replace("$", "").replace(",", ""));
		}
		    System.out.println("price2 are "+Prices2);
		/*converting string list to integer list */
		for (String s1: Prices2){
		    IntList2.add(Integer.valueOf(s1));  
		}
		    System.out.println("Integer2 list is "+IntList2);	
	}

}
