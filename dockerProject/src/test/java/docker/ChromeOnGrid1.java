package docker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class ChromeOnGrid1 {
	
	@Test
	public void test1() throws MalformedURLException
	{
	DesiredCapabilities dc=DesiredCapabilities.chrome();
	URL url=new URL("http://192.168.99.100:4444/wd/hub");
	RemoteWebDriver driver=new RemoteWebDriver(url,dc);
	
	driver.get("https://opensource-demo.orangehrmlive.com/");
	System.out.println(driver.getTitle());
	driver.quit();
	}

}
