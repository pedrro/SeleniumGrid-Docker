package com.sample.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePageObject {
	private final String expectedTitle = "My.dir.bg";
	private final String logoutValue = "Изход";

	private boolean isUserLogged = false;
	
	@CacheLookup
	@FindBy(id = "login_name")
	private WebElement inputUserName;

	@CacheLookup
	@FindBy(id = "login_pass")
	private WebElement inputPassword;

	@CacheLookup
	@FindBy(id = "submit_but")
	private WebElement loginButton;

	@CacheLookup
	@FindBy(className = "fpBlock2")
	private WebElement loginFormLocator;

	@FindBy(id = "usernick")
	private WebElement successMessageLocator;

	@FindBy(css = ".fpBlockBody2 b")
	private WebElement failureMessageLocator;

	@FindBy(id = "details")
	private WebElement logoutArea;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public boolean verifySignInPageTitle() {
		return getPageTitle().contains(expectedTitle);
	}

	public void enterUsername(String username) {
		waitForElementToBeDisplayed(inputUserName);
		inputUserName.sendKeys(username);
	}

	public void enterPassword(String password) {
		waitForElementToBeDisplayed(inputPassword);
		inputPassword.sendKeys(password);
	}

	public void clickLoginButton() {
		waitForElementToBeDisplayed(loginButton);
		loginButton.click();
	}

	public boolean successMessagePresent() {
		waitForElementToBeDisplayed(successMessageLocator);
		isUserLogged = true;
		
		return successMessageLocator.isDisplayed();
	}
	
	public boolean isUserLogged() {
		return isUserLogged;
	}

	public boolean failureMessagePresent() {
		waitForElementToBeDisplayed(failureMessageLocator);
		isUserLogged = false;
		
		return failureMessageLocator.isDisplayed();
	}

	public void logout() {
		waitForElementToBeDisplayed(successMessageLocator);
		successMessageLocator.click();

		selectValueFromUnorderedList(logoutArea, logoutValue);
	}

	private void selectValueFromUnorderedList(WebElement unorderedList,
			final String value) {
		List<WebElement> options = unorderedList.findElements(By.tagName("li"));

		for (WebElement option : options) {
			if (value.equals(option.getText())) {
				option.click();
				break;
			}
		}
	}
}