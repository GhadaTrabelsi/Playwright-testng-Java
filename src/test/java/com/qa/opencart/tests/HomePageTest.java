package com.qa.opencart.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import junit.framework.Assert;

public class HomePageTest extends BaseTest{


	
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle,AppConstants.HOME_PAGE_TITLE);
	}
	@Test
	public void homePageUrlTest() {
		String actualUrl = homePage.getHomePageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("url"));
	}
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
		};
	}
	/*@DataProvider te permet d'exécuter un test avec plusieurs ensembles de données.
Il évite la duplication de tests et améliore la maintenabilité de ton code.
Chaque ligne de données dans le tableau correspond à une exécution unique du test.*/
	
	@Test(dataProvider = "getProductData")
	public void searchTest(String productName) {
		String actualHeader = homePage.doSearch(productName);
		Assert.assertEquals(actualHeader, "Search - "+productName);
		
	}
	
	
}
