package docker;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SetupDockerGrid {
	
	@BeforeTest
	public void startDockerGrid() throws Exception
	{
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
		Thread.sleep(45000);
	}
	
	@AfterTest
	public void stopDockerGrid() throws Exception
	{
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(10000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	}


}
