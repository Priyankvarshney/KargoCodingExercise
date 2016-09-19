package com.Kargo.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.Kargo.Operations.Operations_Selenium;
import com.Kargo.helper.AboutUsHelper;
import com.Kargo.helper.XpathHelper;

/*
 * Test script implemented as part of Coding Exercise for Kargo.com interview process.
 * Below is the problem statement:
 * - Navigate to google.com
 - Search for Kargo
 - Locate and click on "Website"
 - Navigate to about page
 - Go to google.com
 - Search for the following keywords (keywords should be in a txt file and script should open file and grab each keyword to execute)
   > Mobile ad firm
   > Mobile advertising
   > Mobile marketing
   > Mobile internet 
 */


public class VerifyAboutUs {

	@Test
	public void verifyAboutUSAndFileKeywords(){
		WebDriver driver=null;
		try{
			AboutUsHelper helper=new AboutUsHelper();
			driver=new FirefoxDriver();
			driver.get("http://google.com");
			Operations_Selenium operations=new Operations_Selenium(driver);
			operations.sendKeys(XpathHelper.googleSearchTextField, "Kargo");
			operations.click(XpathHelper.googleSearchSubmitButton);
			operations.click(XpathHelper.kargoHomePage);
			operations.click(XpathHelper.kargoMainMenuButton);
			operations.click(XpathHelper.kargoAboutUsOption);
			driver.get("http://google.com");
			
			ArrayList<String> keyWords=helper.getKeywordsFromTxtFile();
			if(keyWords!=null){
				Iterator<String> itr=keyWords.iterator();
				while(itr.hasNext()){
					String keyToSearch=itr.next();
					operations.clearText(XpathHelper.googleSearchTextField);
					operations.sendKeys(XpathHelper.googleSearchTextField, keyToSearch);
					operations.click(XpathHelper.googleSearchSubmitButton);
					if(!helper.verifyPageTitle(driver.getTitle(), keyToSearch+" - Google Search")){
						Assert.fail("Expetected Page Title as "+keyToSearch+" - Google Search"+" but actual is "+driver.getTitle()+". Page Navigation incorrect");
						driver.quit();
					}else{
						System.out.println("Expected page title found after the keyword search: "+operations.getPageTitle());
					}
				}
			}
			driver.quit();


		}catch(Exception e){
			System.out.println("Exception Occured: "+e.getMessage());
			driver.quit();
			Assert.fail("Exception Occured: "+e.getMessage());
		}

	}


}
