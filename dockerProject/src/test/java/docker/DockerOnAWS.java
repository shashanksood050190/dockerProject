//docker run -d -p 4446:4444 --name selenium-hub -P selenium/hub
//docker run -d -P --link selenium-hub:hub selenium/node-chrome-debug
//docker run -d -P --link selenium-hub:hub selenium/node-firefox-debug

package docker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class DockerOnAWS {

	public WebDriver driver;

	@BeforeMethod
	public void launchDriver() throws MalformedURLException {
		DesiredCapabilities dr = null;
		dr = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL("http://13.126.33.229:4444/wd/hub"), dr);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Test1() throws MalformedURLException {

		driver.navigate().to("https://automationtalks.com/");
		System.out.println("test1 title is " + driver.getTitle());

	}

	// @Test
	public void Test2() {
		driver.navigate().to("https://automationtalks.com/");
		System.out.println("test2 title is " + driver.getTitle());

	}

	// @Test
	public void Test3() {
		driver.navigate().to("https://automationtalks.com/");
		System.out.println("test3 title is " + driver.getTitle());
		Assert.assertEquals("Expected Title", driver.getTitle());

	}

	@AfterMethod
	public void closeDriver(ITestResult result) throws MalformedURLException, JiraException {
		if (result.getStatus() == ITestResult.FAILURE) {
			BasicCredentials creds = new BasicCredentials("shashanksood.jecrc", "Jira@0501");
			JiraClient jira = new JiraClient("http://localhost:8081/", creds);
			Issue issueName = jira.createIssue("AUT", "Bug")
					.field(Field.SUMMARY,
							result.getMethod().getMethodName() + "is failed due to" + result.getThrowable().toString())
					.field(Field.DESCRIPTION, "get the description").execute();
			System.out.println("Issue is created in jira with issue story " + issueName.getKey());
		}
		driver.quit();
	}
}
