package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;//1.String Locators - ObjectRepositiry
	
	
	private String search = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
    private String loginLink = "//a[normalize-space()='Login']";
	private String accountLink = "//span[normalize-space()='My Account']";
   
	//2. page constructor:
	
	public HomePage(Page page) {
	this.page = page;
	
	}
	
	//3. page actions/methods
	
	public String getHomePageTitle() {
		String title =  page.title();
		System.out.println("page title is " +title);
		return title;
	}
	
	public String getHomePageUrl() {
		String url =  page.url();
		System.out.println("the url is "+url);
		return url;
	}
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String header=  page.textContent(searchPageHeader);
		System.out.println("the search header is "+header);

		return header;
		
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(accountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
}
