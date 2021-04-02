package supplier.deliveryLimit;

import pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateDeliverLimitPage extends BasePage {

    public CreateDeliverLimitPage(WebDriver driver) {
        super(driver);
    }

    public DeliverLimitListPage create(String goodsIdStr, String configName) {
        //商品范围
        By goodsRange = By.cssSelector(".el-dialog__body  > form > div:nth-child(1) > div  label:nth-child(2)");
        click(goodsRange);

        //商品ID
        By textarea = By.cssSelector(".el-dialog__body  > form > div:nth-child(2) textarea");
        sendKeys(textarea, goodsIdStr);

        //配置名称
        By name = By.cssSelector(".el-dialog__body  > form > div:nth-child(3) textarea");
        sendKeys(name, configName);

        //先滑到底部
        scrollBottom();

        //地区滑到底部
        scrollBottom("section-box");

        //港澳地区
        By region1 = By.cssSelector("div.section-box > div:nth-last-child(1) > label");
        click(region1);

        //台湾地区
        By region2 = By.cssSelector("div.section-box > div:nth-last-child(2) > label");
        click(region2);

        //保存
        By confirm = By.cssSelector("div.el-dialog--center  div .dialog-footer > button:nth-child(2)");
//        By confirm = By.cssSelector("div.el-dialog--center  div .dialog-footer > button:nth-child(1)");
        click(confirm);

        //刷新
        driver.navigate().refresh();

        return new DeliverLimitListPage(driver);
    }
}
