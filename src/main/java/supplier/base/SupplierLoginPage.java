package supplier.base;

import pageObject.BasePage;
import org.openqa.selenium.By;
import supplier.deliveryLimit.DeliverLimitListPage;

public class SupplierLoginPage extends BasePage {

    protected void login() {
        String account = "";
        String pwd = "";

        By email = By.name("email");
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(account);

        By password = By.name("password");
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pwd);

        By enter = By.cssSelector("#app > div > form > button");
        driver.findElement(enter).click();
    }

    public DeliverLimitListPage driverLimitListPage() {
        String url = "";
        driver.get(url);

        login();

        return new DeliverLimitListPage(driver);
    }
}
