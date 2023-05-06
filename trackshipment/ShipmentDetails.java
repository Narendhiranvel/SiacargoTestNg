package trackshipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ShipmentDetails extends BaseClass {

	@Test(dataProvider = "waybillNo")
	public void shipment(String waybillOne, String waybillTwo, String waybillThree) throws InterruptedException {

		String homePage = driver.getTitle();
		System.out.println("You are in -" + homePage + "- home page");
		driver.findElement(By.xpath("(//span[@class='icon-tag'])[2]")).click();
		Set<String> trackShipWindow = driver.getWindowHandles();
		List<String> trackShipWindow2 = new ArrayList<String>(trackShipWindow);
		driver.switchTo().window(trackShipWindow2.get(1));
		Thread.sleep(1000);

		String trackShipment = driver.getTitle();
		if (trackShipment.contains("Track Shipment")) {
			System.out.println("You have navigated to Track Shipment Page");
		} else {
			System.out.println("You are not in Track Shipment page ");
		}

		WebElement textbox1 = driver.findElement(By.xpath("//input[@id = 'Prefix1']"));
		String textBox1Value = textbox1.getAttribute("value");
		String waybillNumber1 = waybillOne;
		if (textBox1Value.equals("618")) {
			WebElement textbox2 = driver.findElement(By.xpath("//input[@id = 'Suffix1']"));
			textbox2.sendKeys(waybillNumber1);
		} else {
			WebElement textbox2 = driver.findElement(By.xpath("//input[@id = 'Suffix1']"));
			textbox2.sendKeys(waybillNumber1);
			driver.findElement(By.id("btnQuery")).click();

			WebElement awbNotFound = driver.findElement(By.xpath("//td[@class ='error-awb']"));
			String anf = awbNotFound.getText();
			System.out.println(anf);
		}

		Thread.sleep(1000);
		driver.findElement(By.id("btnQuery")).click();
		Thread.sleep(1000);

		WebElement awbNo = driver.findElement(By.xpath("//td[@class= 'size-14']"));
		String awbText = awbNo.getText();

		if (awbText.contains(waybillNumber1)) {
			System.out.println(awbText + " Verified Successfully (AWB number and Air Waybill 1 number are same)");
		} else {
			System.out.println("Verification unsuccessful");
		}

		Thread.sleep(1000);
		driver.findElement(By.id("btnSearchAgain")).click();
		Thread.sleep(1000);

		driver.findElement(By.id("Suffix2")).sendKeys(waybillTwo);
		driver.findElement(By.id("Suffix3")).sendKeys(waybillThree);
		Thread.sleep(1000);

		WebElement errorMessage = driver.findElement(By.id("Label7"));
		String errorMess = errorMessage.getText();
		String errorMessage2 = errorMess.replace("*", "");
		if (errorMessage2.contains("Please check entries highlighted")) {
			System.out.println("Error message: " + errorMessage2);
		} else {
			driver.findElement(By.id("btnQuery")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
		}
	}
}
