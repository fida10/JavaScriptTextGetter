import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Subsidiaries {
	private static List<String> getNumOfElements(WebDriver dr, String commonXpathToElements) throws InterruptedException {

		Thread.sleep(1000);
		List<WebElement> textElements = dr.findElements(By.xpath(commonXpathToElements));
		List<String> textForm1 = new ArrayList<>();

		for (int i = 0; i < textElements.size(); i++) {
			String currentTimeStamp = textElements.get(i).getAttribute("aria-label");
			System.out.println("Adding current text to list: " + currentTimeStamp);
			textForm1.add(currentTimeStamp);
			System.out.println(textForm1);
			if ((i + 1) == textElements.size()) {
				System.out.println("Total elements found: " + textForm1.size() + " and all text stored in array: " + textForm1);
				break;
			}
		}
		return textForm1;
	}
	public static void jsExect(WebDriver dr, String commonXpathToElements, String directXpathToText) throws InterruptedException { //first xpath should be relative, second should be absolute with parent/child to allow for substitution of child number. Both should lead to the same element, though the first should point to an attribute, not text.
		List<String> elements = getNumOfElements(dr, commonXpathToElements);
		List<String> textInJS = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) dr;
		for (int i = 0; i < elements.size(); i++) {
			System.out.println("Currently on element with text: " + elements.get(i) + " and this is element number" + (i + 1));
			String dynamicDirectXpath = String.format(directXpathToText, (i + 1)); //ideally this would be a parent/child xpath with a %d inserted to change to different elements in the JS executor. It should be changeable with a single number, that way we can loop through a JS exec object and store it in there. JS exec returns one WebElement at a time.

			WebElement jsExtraction = (WebElement) js.executeScript(String.format("return document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue", dynamicDirectXpath));
			System.out.println(jsExtraction.getText()); //It works!!! now just append this to a new list, should make for easier parsing
			textInJS.add(jsExtraction.getText());
		}
		System.out.println("Total elements stored: " + textInJS.size() + " and the list contents: " + textInJS);
	}
}