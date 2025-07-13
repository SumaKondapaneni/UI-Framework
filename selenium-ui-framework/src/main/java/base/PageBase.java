package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */



public class PageBase {

	protected WebDriver driver;
	
	public WebDriver getDriver() {
        return driver;
    }

	public PageBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	protected void selectDropdownByVisibleText(WebElement typeDropdown, String type) {
		// TODO Auto-generated method stub
		Select select = new Select(typeDropdown);
	    select.selectByVisibleText(type);
	}

}
