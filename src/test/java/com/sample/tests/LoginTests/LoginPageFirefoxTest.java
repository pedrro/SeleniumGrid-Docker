package com.sample.tests.LoginTests;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.sample.commons.TestBaseSetup;
import com.sample.pageobjects.HomePage;
import com.sample.pageobjects.LoginPage;

public class LoginPageFirefoxTest extends TestBaseSetup {
	private WebDriver driver;
	private LoginPage login;
	private HomePage home;

	@Before
	public void setUp() throws MalformedURLException {
		initializeRemoteWebDriver(firefoxBrowser, appUrl, remoteUrl);
		driver = getDriver();
	}

	@Test
	public void correctUserNameOrPassword() {
		home = new HomePage(driver);
		login = home.clickLoginButton();
		login.enterUsername("g5140549@trbvm.com");
		login.enterPassword("2477261f");
		login.clickLoginButton();

		assertTrue(login.successMessagePresent());

		login.logout();
	}

	@Test
	public void wrongUserNameOrPassword() {
		home = new HomePage(driver);
		login = home.clickLoginButton();
		login.enterUsername("username");
		login.enterPassword("password");
		login.clickLoginButton();

		assertTrue(login.failureMessagePresent());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}