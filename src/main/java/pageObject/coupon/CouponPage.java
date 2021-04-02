package pageObject.coupon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.LoginsPage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CouponPage extends LoginsPage {

    public static String url = "/marketing-system/coupon-list";
    //保存按钮
    By confirmButton = By.cssSelector(".el-button--medium:nth-child(2)");
    //优惠券名字
    By couponName = By.cssSelector(".el-input__inner:nth-child(1)");
    //显示的开始和结束时间
    By showPeriod = By.cssSelector("");
    //有效期的开始和结束时间
    By clickButton = By.cssSelector(".el-range-input:nth-child(2)");
    //发券总量
    By totalNum = By.cssSelector("form.el-form > div:nth-child(2) > div > div:nth-child(3) input.el-input__inner");
    //优惠配置
    // 第一行
    By firstRow = By.cssSelector("form.el-form > div:nth-child(3) > div:nth-child(2) input");
    //第二行
    By secondRow = By.cssSelector("form.el-form > div:nth-child(3) > div:nth-child(3) input");
    //第三行
    By thirdRow = By.cssSelector("form.el-form > div:nth-child(3) > div:nth-child(4) input");

    public CouponPage(WebDriver driver) {
        super(driver);
    }

    protected void baseField() {
        //优惠券名字
        sendKeys(couponName, "优惠券名字");
        //优惠券属性:默认普通
        click(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(5) > div > div > label:nth-child(1)"));
        //使用有效期:固定时间&&
        click(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(6) > div > div > label:nth-child(1)")); //
        //日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        //开始时间
        By start1 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(1) > span:nth-child(1) input");
        By start2 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(1) > span:nth-child(2) input");
        //开始的确认按钮
        By confirm1 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(1) > span:nth-child(2) div.el-time-panel__footer > button.confirm");
        //结束时间
        By end1 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(3) > span:nth-child(1) input");
        By end2 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(3) > span:nth-child(2) input");
        //结束的确认按钮
        By confirm2 = By.cssSelector("div.el-date-range-picker__time-header > span:nth-child(3) > span:nth-child(2) div.el-time-panel__footer > button.confirm");
        By confirm3 = By.cssSelector(".is-plain");
        //时间有效期
        timeControlButton(clickButton, start1, start2, confirm1, end1, end2, confirm2, confirm3, dateStr, dateStr);
//        //使用有效期:领取当天起x天有效 && 使用天数:2
//        click(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(6) > div > div > label:nth-child(2)"));
//        sendKeys(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(7) > div > span > div > input"), "2");

        //使用&领用规则
        //发券总量
        sendKeys(totalNum, "2");
        //每人限领
        sendKeys(By.cssSelector("form.el-form > div:nth-child(2) > div > div:nth-child(4) input.el-input__inner"), "2");
        //可同时领多张
        click(By.cssSelector("form.el-form > div:nth-child(2) > div > div:nth-child(4) .el-checkbox__label"));
        //新人限领
        //用户渠道
        sendKeys(By.cssSelector("textarea.el-textarea__inner"), "4863");
        //规则说明
        sendKeysInRichText(By.tagName("body"), "规则说明");
    }

    //打开创建页面
    protected void openCreatePage() {
        switchToWindow(host + url);
        click(By.cssSelector(".el-button--primary:nth-child(2) > span"));
        click(By.cssSelector(".is-required > .el-form-item__content > .el-input > .el-input__inner"));
    }

    //点击按钮
    protected void saveButton() {
        click(confirmButton);
    }

    //什么都不填直接提交
    public BlankCouponPage createBlankCoupon() {
        openCreatePage();
        saveButton();
        return new BlankCouponPage(driver);
    }

    public void createACoupon() {
        openCreatePage();

        baseField();

        //优惠类型
        click(By.cssSelector(".el-radio-group:nth-child(1) > label:nth-child(2)"));
        //跳转链接
        sendKeys(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div > div > input"), "https://m.black-unique.com/new/#/hourSeckill");
        //前台显示：否
        click(By.cssSelector("form.el-form > div:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div > div > label:nth-child(2)"));


        //使用&领用规则
        //优惠券使用业务范围
        click(By.cssSelector("form.el-form > div:nth-child(2) > div > div:nth-child(1) > div > div > label:nth-child(1)"));
        //商品范围
        click(By.cssSelector(".is-checked > .el-checkbox__label"));


        //优惠金额
        sendKeys(firstRow, "2");
//        sendKeys(secondRow, "1");

        //保存按钮
//        saveButton();
    }
}
