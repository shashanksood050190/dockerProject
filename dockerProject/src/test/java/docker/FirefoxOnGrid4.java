package docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class FirefoxOnGrid4 {
	
	@Test
	public void test4() throws MalformedURLException
	{
	DesiredCapabilities dc=DesiredCapabilities.firefox();
	URL url=new URL("http://192.168.99.100:4444/wd/hub");
	RemoteWebDriver driver=new RemoteWebDriver(url,dc);
	
	driver.get("https://www.flipkart.com/");
	System.out.println(driver.getTitle());
	driver.quit();
	
	}

}
