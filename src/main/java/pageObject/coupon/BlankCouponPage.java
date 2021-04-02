package pageObject.coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlankCouponPage extends CouponPage{
    public BlankCouponPage(WebDriver driver) {
        super(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    By blankCouponName = By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(2)");
    By blankTotalNum = By.cssSelector("form.el-form > div:nth-child(2) > div > div:nth-child(3) > div > div");

    public String getBlankErrorFromCouponName() {
        WebElement couponName = driver.findElement(blankCouponName);
        return couponName.getText();
    }

    public String getBlankErrorFromTotalNum() {
        WebElement totalNum = driver.findElement(blankTotalNum);
        return totalNum.getText();
    }

}
