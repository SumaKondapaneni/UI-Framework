package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */

public class AdminLoginPage extends PageBase {
    




	public AdminLoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}





    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "doLogin")
    WebElement loginBtn;



    public RoomManagementPage login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
        return new RoomManagementPage(driver);
    }
}

