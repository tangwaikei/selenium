package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    public JavascriptExecutor jsDriver;
    public WebDriverWait wait;

    public BasePage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("–incognito"); //隐身模式
        driver = new ChromeDriver();    //Chrome浏览器
        driver.manage().window().maximize();//最大化
        jsDriver = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 5);
        System.out.println("新开一个无痕窗口");
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        jsDriver = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 5);//显示等待
    }

    //清空input
    public void clearKeyBoard(By by) {
        driver.findElement(by).sendKeys(Keys.CONTROL, "a");
    }

    public void sendKeys(By by, String str) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        driver.findElement(by).clear(); //不生效
        clearKeyBoard(by);
        driver.findElement(by).sendKeys(str);
    }

    //富文本编辑
    public void sendKeysInRichText(By by, String str) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        //进入富文本编辑器：只有一个富文本编辑框
        driver.switchTo().frame(0);
        driver.findElement(by).sendKeys(str);
        //跳出富文本编辑器
        driver.switchTo().defaultContent();
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void debug(int second) {
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);//隐式等待
    }

    public void timeControlButton(By clickButton, By start1, By start2, By confirm1, By end1, By end2, By confirm2, By confirm3, String startDate, String endDate) {
        //触发点
        click(clickButton);
        debug(1);

        //结束时间1
        sendKeys(end1, endDate);

        //开始时间1
        sendKeys(start1, startDate);

        //先清除时间
        sendKeys(start2, " 00:00:00");
        //确认
        click(confirm1);

        sendKeys(end2, " 23:59:59");
        click(confirm2);
        //最后的确认按钮
        click(confirm3);
    }

    public void scrollBottom() {
        debug(2);
        jsDriver.executeScript("window.scrollBy(0,50000)");
    }

    public void scrollBottom(String byClassName) {
        debug(2);
        String js = "var bottom = document.getElementsByClassName('" + byClassName + "').scrollTop = 5000";
        jsDriver.executeScript(js);
    }

    public void scrollTop() {
        debug(2);
        jsDriver.executeScript("window.scrollBy(0,0)");
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public void switchToWindow(String api) {
                //新开一个窗口
        String js = "window.open('" + api + "');";
        jsDriver.executeScript(js);

        Set<String> handles = driver.getWindowHandles();
        for(String handle : handles) {
            //切换窗口（切换到新窗口）
            if(!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                debug(5);
                break;
            }
        }

        System.out.println(driver.getCurrentUrl());
    }

    public void quit() {
        driver.quit();
    }
}
