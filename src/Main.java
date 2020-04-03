import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException{

		System.setProperty("webdriver.chrome.driver", "//Users//fida10//selenium//drivers//chrome//chromedriver");
		WebDriver dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		dr.get("https://www.youtube.com/playlist?list=PLGCD3Lv-P04hIOuiaezolcVqQhFV8oHZk");
		Subsidiaries.jsExect(dr, "//span[@class='style-scope ytd-thumbnail-overlay-time-status-renderer']", "//div[@id='contents' and @class='style-scope ytd-playlist-video-list-renderer']//ytd-playlist-video-renderer[%d]//div[2]//a[1]//ytd-thumbnail[1]//a[1]//div[1]//ytd-thumbnail-overlay-time-status-renderer[1]//span[1]");
	}
}

