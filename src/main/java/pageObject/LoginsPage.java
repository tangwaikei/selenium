package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class LoginsPage extends BasePage{
    protected String username = "";
    protected String pwd = "";
    protected String host = "";

    public LoginsPage() {
        super();
    }

    public LoginsPage(WebDriver driver) {
        super(driver);
    }

    public void loginWithAccount() {
        By button = By.cssSelector(".el-button");
        click(button);

        //点击账号密码登录
        By srcode = By.id("tab-login-text");
        click(srcode);

        By name = By.name("data[username]");
        sendKeys(name, username);


        By password = By.name("data[password]");
        sendKeys(password, pwd);

        By clickLogin = By.cssSelector(".btn-success");
        click(clickLogin);
    }

    public <T> T login(Class<T> tClass, String url) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String api = host + url;
        driver.get(api);
        loginWithAccount();
        Class[] args = new Class[1];
        args[0] = WebDriver.class;
        return tClass.getDeclaredConstructor(args).newInstance(driver);
    }
}
