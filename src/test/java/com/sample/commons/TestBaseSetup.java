package com.sample.commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@Ignore
public class TestBaseSetup {
	private WebDriver driver;
	private DesiredCapabilities desiredCapabilities;

	protected final String chromeBrowser = "chrome";
	protected final String firefoxBrowser = "firefox";
	protected final String appUrl = "http://dir.bg";
	protected final String remoteUrl = "http://192.168.99.100:4444/wd/hub";

	protected void initializeRemoteWebDriver(String browserType, String appURL,
			String remoteAddress) {
		try {
			setRemoteDriver(browserType, appURL, remoteUrl);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	protected WebDriver getDriver() {
		return this.driver;
	}

	private void setRemoteDriver(String browserType, String appURL,
			String remoteUrl) throws MalformedURLException {
		switch (browserType) {

		case "chrome":
			this.desiredCapabilities = DesiredCapabilities.chrome();
			this.driver = initRemoteDriver(appURL, this.desiredCapabilities,
					remoteUrl);
			break;

		case "firefox":
			this.desiredCapabilities = DesiredCapabilities.firefox();
			this.driver = initRemoteDriver(appURL, this.desiredCapabilities,
					remoteUrl);
			break;

		default:
			System.out.println("Browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			this.desiredCapabilities = DesiredCapabilities.firefox();
			this.driver = initRemoteDriver(appURL, this.desiredCapabilities,
					remoteUrl);
		}

	}

	private static WebDriver initRemoteDriver(String appURL,
			DesiredCapabilities desiredCapabilities, String remoteAddress)
			throws MalformedURLException {
		System.out.println("Launching " + desiredCapabilities.getBrowserName() + " browser..");

		WebDriver driver = new RemoteWebDriver(new URL(remoteAddress),
				desiredCapabilities);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);

		return driver;
	}
}
