package trackshipment;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static EdgeDriver driver;

	@Parameters({ "url" })
	@BeforeMethod
	public void precondition(String url) {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	@AfterMethod
	public void postcondition() throws InterruptedException {
		Thread.sleep(1000);
		driver.close();
		driver.quit();
	}
	@DataProvider(name = "waybillNo", indices = { 0, 1 })
	public String[][] setData() throws IOException {
		String[][] readData = ReadExcelData.readData();
		return readData;
	}
}
