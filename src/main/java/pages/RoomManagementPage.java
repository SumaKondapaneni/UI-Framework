package pages;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */



import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

import base.PageBase;

public class RoomManagementPage extends PageBase{


    public RoomManagementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "roomName")
    WebElement roomNameInput;

    @FindBy(id = "type")
    WebElement typeDropdown;

    @FindBy(id = "accessible")
    WebElement accessibleCheckbox;

    @FindBy(id = "roomPrice")
    WebElement priceInput;


    @FindBy(id = "wifiCheckbox")
    WebElement wifiCheckbox;

    @FindBy(id = "tvCheckbox")
    WebElement tvCheckbox;

    @FindBy(id = "safeCheckbox")
    WebElement safeCheckbox;

    @FindBy(id = "refreshCheckbox")
    WebElement refreshCheckbox;

    @FindBy(id = "viewsCheckbox")
    WebElement viewsCheckbox;

    @FindBy(id = "radioCheckbox")
    WebElement radioCheckbox;

    @FindBy(id = "createRoom")
    WebElement submitButton;
    
  
    @FindBy(id = "frontPageLink")
    WebElement frontPageLink;

    public void createRoom(String roomName, String type, boolean accessible, int price, String description, String[] features) {
        roomNameInput.sendKeys(roomName);
        selectDropdownByVisibleText(typeDropdown, type);

        if (accessible && !accessibleCheckbox.isSelected()) {
            accessibleCheckbox.click();
        }

        priceInput.sendKeys(String.valueOf(price));


        for (String feature : features) {
            switch (feature.toLowerCase()) {
                case "wifi":
                    if (!wifiCheckbox.isSelected()) wifiCheckbox.click();
                    break;
                case "tv":
                    if (!tvCheckbox.isSelected()) tvCheckbox.click();
                    break;
                case "safe":
                    if (!safeCheckbox.isSelected()) safeCheckbox.click();
                    break;
                case "refreshments":
                    if (!refreshCheckbox.isSelected()) refreshCheckbox.click();
                    break;
                case "views":
                    if (!viewsCheckbox.isSelected()) viewsCheckbox.click();
                    break;
                case "radio":
                    if (!radioCheckbox.isSelected()) radioCheckbox.click();
                    break;
            }
        }

        submitButton.click();
    }

   public HomePage clickFrontPage() {
		
    	frontPageLink.click();
    	
    	return new HomePage(driver);
    	
    	
    	
    }

}


