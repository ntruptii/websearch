package org.challenges.challenge9;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Reuseables{
	private static WebDriver driver;
	SearchEngines search;
	
	public void openBrowser(String url){
		final String webdriverPathsystemProperty = "webdriver.chrome.driver";
		if (System.getProperty(webdriverPathsystemProperty) == null) {
			throw new IllegalStateException("Please specify chromedriver file path via system property " + webdriverPathsystemProperty + ".");
		}
		driver= new ChromeDriver();
		driver.get(url);
	}
	public List<SearchResult> enterText(String searchTerm,SearchEngines Engine) throws InterruptedException{
		
		List<SearchResult> searchResult = new ArrayList<SearchResult>();
		List<WebElement> searchterm = new ArrayList<WebElement>();
		search = Engine;
		switch(search){
		case Google:
			WebElement tbox_GoogleSearch = driver.findElement(By.id("lst-ib"));
			tbox_GoogleSearch.sendKeys(searchTerm);
			Thread.sleep(10000);
			searchterm = driver.findElements(By.xpath(".//ul[@class='sbsb_b']/li"));			
			break;
		case Yahoo:
			WebElement tbox_YahooSearch = driver.findElement(By.id("UHSearchBox"));
			tbox_YahooSearch.sendKeys(searchTerm);
			Thread.sleep(10000);
			searchterm = driver.findElements(By.xpath(".//ul[@class='Hover P(0) M(0)']/li"));			
			break;
		case Bing:
			WebElement tbox_BingSearch = driver.findElement(By.id("sb_form_q"));
			tbox_BingSearch.sendKeys(searchTerm);
			Thread.sleep(10000);
			searchterm = driver.findElements(By.xpath(".//ul[@class='sa_drw']/li"));			
			break;
			default:
			break;	
		}
		try {
			for (int i = 0; i < 5; i++) {
				searchResult.add(new SearchResult(searchterm.get(i).getText()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't find 5 search results.Please enter another search term ");
		}
		return searchResult;
	}
// To close web driver
	public void CloseBrowser() {
		driver.close();
		
	}
	
}