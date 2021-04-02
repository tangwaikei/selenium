package supplier.deliveryLimit;

import pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Pattern;

public class DeliverLimitListPage extends BasePage {
    //开始日期
    By start = By.cssSelector("div.filter-container > div:nth-child(1) > div:nth-child(2) div.el-date-editor input:nth-child(2)");
    //结束日期
    By end = By.cssSelector("div.filter-container > div:nth-child(1) > div:nth-child(2) div.el-date-editor input:nth-child(4)");
    //申请ID
    By applyId = By.cssSelector(".filter-container > div:nth-child(1)  div.el-input > input");
    //申请类型
    By type = By.cssSelector(".filter-container > div:nth-child(2) > div:nth-child(1) input");
    //搜索
    By search = By.cssSelector(".filter-container > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)");
    //添加配置
    By createButton = By.cssSelector(".filter-container > div:nth-child(2) > div:nth-child(3) > button:nth-last-child(1)");
    //总共多少条
    By total = By.cssSelector(".el-pagination__total");

    Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5]\\s*(\\d+)\\s*[\u4E00-\u9FA5]$");

    public DeliverLimitListPage(WebDriver driver) {
        super(driver);
    }

    public CreateDeliverLimitPage createDiverLimit() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(createButton);
        return new CreateDeliverLimitPage(driver);
    }

    //搜索
    public DeliverLimitListPage search( String startTime, String endTime) {
        if(startTime.length() > 0 && endTime.length() > 0) {
            sendKeys(start, startTime);
            sendKeys(end, endTime);
            click(applyId);
        }
        click(search);
        return this;
    }
}
