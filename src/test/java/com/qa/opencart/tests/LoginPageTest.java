package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class LoginPageTest extends BaseTest{

	@Test (priority = 1)
	
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actualtitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
@Test (priority = 2)
	
	public void forgotPaswLinkExistTest() {
		loginPage = homePage.navigateToLoginPage();
		Assert.assertTrue(loginPage.isForgotPaswLinkExist());
	}
	
@Test (priority = 3)

public void appLoginTest() {
	loginPage = homePage.navigateToLoginPage();
	Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));

}

	
}
